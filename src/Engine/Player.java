package Engine;

import java.util.List;

/**
 * Created by Family on 6/6/17.
 */
public class Player {
    //zones:
    private Hand hand;

    private Library library;
    private Graveyard graveyard;
    private Command command;
    private Controller controller;

    private List<Mana> pool;

    public Player(Controller controller,String deckName)
    {
        this.controller = controller;
        hand = new Hand();
        library = new Library(deckName);
        graveyard = new Graveyard();
        command = new Command();
    }

    public boolean playCard(Card card)
    {
        if(!hand.hasCard(card))
            return false;
        Card ref = hand.getCardRef(card);
        if(ref.getTypes().contains(Type.Land))
        {
            Permanent land = new Permanent(ref);
            controller.getBattlefield().enter(land);
        }
        else
        {
            //spell
        }
        hand.getCards().remove(ref);
        return true;
    }

    public void draw(int num)
    {
        for(int i=0;i<num;i++)
        {
            hand.getCards().add(library.getCards().remove(0));
        }
    }

    public void shuffle() {
        library.shuffle();
    }

    public Library getLibrary() {
        return library;
    }

    public Hand getHand() {
        return hand;
    }

    public Graveyard getGraveyard() {
        return graveyard;
    }

    public Command getCommand() {
        return command;
    }
}
