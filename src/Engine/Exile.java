package Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Family on 6/6/17.
 */
public class Exile extends Zone {
    public Exile()
    {
        setCards(new ArrayList<>());
    }
    public void addDiv(List<Card> newDiv)
    {
        getCards().addAll(newDiv);
    }
}
