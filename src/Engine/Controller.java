package Engine;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public Controller(int playerNum,Socket socket)
    {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintStream(socket.getOutputStream());
            writer.println("Random?");
            long seed = Long.parseLong(reader.readLine());
            System.out.println(seed);
            random = new Random(seed);
        } catch(IOException e){
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new PrintStream(System.out);
            random = new Random(1701);
        }
        
        exile = new Exile();
        stack = new Stack();
        battlefield = new Battlefield();
        players = new ArrayList<>();
        for(int i=0;i<playerNum;i++)
        {
            if(i==0)
                players.add(new Player(this,"MyDeck.txt",i));
            else
                players.add(new Player(this,"MyOtherDeck.txt",i));
            players.get(i).shuffle();
            players.get(i).draw(7);
        }

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
    public void cardPlayed()
    {

    }
}
