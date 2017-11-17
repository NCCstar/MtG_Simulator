package Engine;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Family on 6/6/17.
 */
public class Permanent extends Card {
    private Card origin;
    private Player controller;
    private boolean tapped;
    private int damage;

    private List<Counter> counters;

    public Permanent(String entryText,Player controller)
    {
        super(entryText);
        this.controller = controller;
    }
    public Permanent(String entryText)
    {
        super(entryText);
    }
    public Permanent(Card card,Player controller)
    {
        super(card);
        origin = card;
        this.controller = controller;
    }

    public void untap() {
        tapped = false;
    }

    public void addDamage(int d) {
        damage += d;
    }

    public void clearDamage(){
        damage = 0;
    }

    public int getDamage(){
        return damage;
    }

    public List<Counter> getCounters() {
        return counters;
    }

    public void setController(Player player){
        controller = player;
    }

    public Player getController() {
        if(controller==null)
            return getController();
        return controller;
    }
}
