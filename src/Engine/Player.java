package Engine;

import java.util.ArrayList;
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

    private int pNum;
    private List<Mana> pool;

    public Player(Controller controller,String deckName,int pNum)
    {
        this.controller = controller;
        hand = new Hand();
        library = new Library(deckName,this);
        graveyard = new Graveyard();
        command = new Command();
        this.pNum = pNum;
    }

    public boolean playCard(Card card)
    {
        if(!hand.hasCard(card)) {
            System.out.println("That card is not in hand");
            return false;
        }
        //Card is in hand
        Card ref = hand.getCardRef(card);
        if(ref.getTypes().contains(Type.Land))
        {
            Permanent land = new Permanent(ref,this);
            controller.getBattlefield().enter(land);
        }
        else
        {
            List<Mana> cost = card.getManaCost();
            //pool
            try
            {
                List<Mana> tempPool = new ArrayList<>(pool);
                Mana.subtract(tempPool,cost);
                pool = tempPool;

                //cast spell
            }
            catch(ManaException e)
            {
                System.out.println("Can't cast.");
            }
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

    public int getPNum(){return pNum;}
}
