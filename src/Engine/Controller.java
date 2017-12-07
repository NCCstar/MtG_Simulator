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
    private Player active;
    private Player turn;
    private BufferedReader reader;
    private PrintStream writer;
    private Random random;
    private Display display;
    public Step step;
    private int turnCount;
    private int passed;

    public Controller(int playerNum, Socket socket) {
        int first = 0;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintStream(socket.getOutputStream());
            writer.println("Random?");
            long seed = Long.parseLong(reader.readLine());
            System.out.println(seed);
            random = new Random(seed);
            writer.println("First?");
            first = Integer.parseInt(reader.readLine());
            writer.println("Ready");
            while (!reader.readLine().equals("Ready")) {
            }
        } catch (IOException e) {
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new PrintStream(System.out);
            random = new Random(1701);
        }


        exile = new Exile();
        stack = new Stack();
        battlefield = new Battlefield();
        players = new ArrayList<>();

        setupPlayers(playerNum);

        new Thread( () -> {
            while (true) {
                try {
                    String read = reader.readLine();
                    System.out.println("Recieved: " + read);
                    handleAction(read);
                } catch (IOException e) { }
            }
        }).start();

        turnCount = 0;
        step = Step.Cleanup;


        active = players.get(first);
        turn = active;

        checkStateActions();
        active.getPriority();
    }

    public void setNextActive(){
        active.passPriority();
        active = players.get((active.getPNum() + 1) % players.size());
        checkStateActions();
        active.getPriority();
    }

    public boolean shownPlayerActive(){
        return players.get(1) == active;
    }

    public boolean shownPlayerTurn() {
        return players.get(1) == turn;
    }

    public void startPassPriority() {
        writer.println("Direct*PassPriority");
        passPriority();
    }

    public void passPriority(){
        display.repaint();
        if(stack.isEmpty()) {
            if(passed >= players.size()-1) {
                nextStep();
                passed = 0;
                active = turn;
            } else {
                passed++;
                setNextActive();
            }
        } else {
            if (passed >= players.size()-1) {
                stack.pop().resolve();
                active = turn;
                passed = 0;
            } else {
                passed++;
                setNextActive();
            }
        }
    }

    public void actionTaken(){
        passed = 0;
    }

    public void checkStateActions() {
        for (Player player : players) {
            if (player.getLife() <= 0) {
                //TODO: lose the game
            }
        }
        List<Permanent> legendaries = new ArrayList<>();
        battlefield.getPerms().stream()
                .filter( p -> p.getController() == active)
                .forEach( perm -> {
                    if (perm.getTypes().contains(Type.Creature)) {
                        if (perm.getToughness() - perm.getDamage() <= 0) {
                            //TODO: kill creature
                        }
                    }
                    if (perm.getTypes().contains(Type.Planeswalker)) {
                        if(!perm.getCounters().contains(Counter.Loyalty)){
                            //TODO: kill planeswalker
                        }
                    }
                    if(perm.getSupertypes().contains(Supertype.Legendary)) {
                        if(legendaries.contains(perm)) {
                            //TODO: sac a legend
                        } else {
                            legendaries.add(perm);
                        }
                    }
                });
    }

    public void nextStep() {
        step = step.nextStep();
        if (step == Step.Untap) {
            turnCount++;
            setNextActive();
        }
        switch (step) {
            case Untap:
                for (Card card : battlefield) {
                    Permanent perm = (Permanent) (card);
                    if (perm.getController() == active)
                        perm.untap();
                }
                nextStep();
                break;
            case Upkeep:
                //TODO: check upkeep effects
                break;
            case Draw:
                if (turnCount != 1) {
                    active.draw(1);
                }
                nextStep();
                break;
            case Main1:
                break;
            case BeginCombat:
                break;
            case DeclareAttackers:
                break;
            case DeclareBlockers:
                break;
            case Damage:
                break;
            case EndCombat:
                break;
            case Main2:
                break;
            case End:
                break;
            case Cleanup:
                break;
        }
        display.repaint();
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Random getRandom() {
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

    public Player getOpponent(Player p) {
        if (p == players.get(0))
            return players.get(1);
        return players.get(0);
    }

    private void setupPlayers(int playerNum) {
        File browser = new File("./Decks/");
        if (!browser.isDirectory())
            throw new IllegalStateException("Somehow, you made 'Decks' a file.");
        String[] files = browser.list();
        String deckName = (String) (JOptionPane.showInputDialog(null, "Which deck?",
                "Deck?", JOptionPane.INFORMATION_MESSAGE, null, files, files[0]));

        //write my deck to other player
        browser = new File("./Decks/" + deckName);
        try {
            Scanner scanner = new Scanner(browser);
            writer.println("Direct*Deck");
            while (scanner.hasNextLine()) {
                writer.println("Direct*" + scanner.nextLine());
            }
            writer.println("Direct*Deck");
        } catch (IOException e) {
            System.out.println("It broke. Restart pls.");
        }
        //receive other deck
        try {
            List<String> deckList = new ArrayList<>();
            String line = reader.readLine();
            if (line.equals("Direct*Deck")) {
                line = reader.readLine();
                while (!line.equals("Direct*Deck")) {
                    deckList.add(line.substring(7));
                    line = reader.readLine();
                }
            }
            players.add(new Player(this, deckList, 0));
        } catch (IOException e) {
            System.out.println("It broke. Restart pls.");
        }

        players.add(new Player(this, deckName, 1));

        for (int i = 0; i < playerNum; i++) {
            players.get(i).shuffle();
            players.get(i).draw(7);
        }
    }

    private void handleAction(String action) {
        System.out.println("Action Received: " + action);
        String[] split = action.split("\\*");
        if (split[0].equals("Direct")) {
            if(split[1].equals("Play")) {
                int pNum = Integer.parseInt(split[2]) ^ 1;
                if (players.get(pNum) == active) {
                    String cardName = split[3];
                    System.out.println("Player " + pNum + " playing " + cardName);
                    players.get(pNum).playCard(CardMapper.map(cardName));
                    display.update();
                }
            }
            if(split[1].equals("PassPriority")) {
                passPriority();
            }
        }
        if(split[0].equals("Repaint")) {
            display.repaint();
        }
    }

    public void playCardHere(Player player, Card card) {
        if (player == active) {
            if (player.playCard(card)) {
                writer.println("Direct*Play*" + card.getOwner().getPNum() + "*" + card.getName());
                display.update();
            }
        }
    }
}
