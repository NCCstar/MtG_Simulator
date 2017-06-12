package Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Family on 6/6/17.
 */
public class Exile extends Zone {
    private List<Card> inExile;
    public Exile()
    {
        inExile = new ArrayList<>();
    }
    public void addDiv(List<Card> newDiv)
    {
        inExile.addAll(newDiv);
    }
}
