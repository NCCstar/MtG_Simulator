package Engine;

import java.util.ArrayList;

/**
 * Created by Family on 6/6/17.
 */
public class Hand extends Zone{
    public Hand()
    {
        setCards(new ArrayList<>());
    }
    public boolean hasCard(Card card)
    {
        for(Card c:getCards())
        {
            if(card.equals(c))
                return true;
        }
        return false;
    }
    public Card getCardRef(Card card)
    {
        for(Card c:getCards())
        {
            if(card.equals(c))
                return c;
        }
        return null;
    }
}
