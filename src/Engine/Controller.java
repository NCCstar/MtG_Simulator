package Engine;

import Graphics.Display;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * Created by Family on 6/6/17.
 */
public class Controller {
    private Exile exile;
    private Stack stack;
    private Battlefield battlefield;
    private List<Player> players;
    private BufferedReader reader;
    private PrintStream writer;
    private Random random;
    private Display display;

    public Controller(int playerNum, Socket socket)
    {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintStream(socket.getOutputStream());
            writer.println("Random?");
            long seed = Long.parseLong(reader.readLine());
            System.out.println(seed);
            random = new Random(seed);
            writer.println("Ready");
            while(!reader.readLine().equals("Ready"))
            { }
        } catch(IOException e){
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new PrintStream(System.out);
            random = new Random(1701);
        }


        exile = new Exile();
        stack = new Stack();
        battlefield = new Battlefield();
        players = new ArrayList<>();

        setupPlayers(playerNum);

        new Thread( ()->
        {
            while(true)
            {
                try {
                    String read = reader.readLine();
                    System.out.println("Recieved: " + read);
                    handleAction(read);
                } catch (IOException e)
                { }
            }
        }).start();
    }

    public void setDisplay(Display display)
    {
        this.display = display;
    }

    public Random getRandom()
    {
        return random;
    }

    public Exile getExile() {
        return exile;
    }

    public Stack getStack() {
        return stack;
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getOpponent(Player p)
    {
        if(p==players.get(0))
            return players.get(1);
        return players.get(0);
    }

    private void setupPlayers(int playerNum)
    {
        File browser = new File("./Decks/");
        if(!browser.isDirectory())
            throw new IllegalStateException("Somehow, you made 'Decks' a file.");
        String[] files = browser.list();
        String deckName = (String)(JOptionPane.showInputDialog(null,"How many players?","Players",JOptionPane.INFORMATION_MESSAGE, null,files, files[0]));

        //write my deck to other player
        browser = new File("./Decks/"+deckName);
        try
        {
            Scanner scanner = new Scanner(browser);
            writer.println("Direct*Deck");
            while(scanner.hasNextLine())
            {
                writer.println(scanner.nextLine());
            }
            writer.println("Direct*Deck");
        } catch(IOException e)
        {
            System.out.println("It broke. Restart pls.");
        }
        //receive other deck
        try {
            List<String> deckList = new ArrayList<>();
            String line = reader.readLine();
            if (line.equals("Direct*Deck"))
            {
                line = reader.readLine();
                while(!line.equals("Direct*Deck"))
                {
                    deckList.add(line);
                    line = reader.readLine();
                }
            }
            players.add(new Player(this, deckList,0));
        } catch(IOException e)
        {
            System.out.println("It broke. Restart pls.");
        }

        players.add(new Player(this, deckName,1));

        for(int i=0;i<playerNum;i++)
        {
            players.get(i).shuffle();
            players.get(i).draw(7);
        }
    }

    private void handleAction(String action)
    {
        System.out.println("Action Recieved: "+action);
        String[] split = action.split("\\*");
        if(split[1].equals("Play"))
        {
            int pNum = Integer.parseInt(split[2]);
            String cardName = split[3];
            System.out.println("Player "+pNum+" playing "+cardName);
            players.get(pNum).playCard(CardMapper.map(cardName));
            display.update();
        }
    }

    public void playCardHere(Player player, Card card)
    {
        if(player.playCard(card))
        {
            writer.println("Direct*Play*"+card.getOwner().getPNum()+"*"+card.getName());
            display.update();
        }
    }
}
