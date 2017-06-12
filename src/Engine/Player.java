package Engine;

import java.util.List;

/**
 * Created by Family on 6/6/17.
 */
public class Player {
    //zones:
    private Hand hand;
    private Deck deck;
    private Graveyard graveyard;
    private Command command;
    private Controller controller;

    private List<Mana> pool;

    public Player(Controller controller)
    {
        this.controller = controller;
        hand = new Hand();
        deck = new Deck();
        graveyard = new Graveyard();
        command = new Command();
    }
}
