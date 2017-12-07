package Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Family on 6/6/17.
 */
public class Stack extends Zone { //TODO: Possibly use a Deque?
    private List<Spell> spells;
    public Stack()
    {
        spells = new ArrayList<>();
        setCards(new ArrayList<>());
    }
    public void add(Spell spell)
    {

    }
    public boolean isEmpty(){
        return spells.isEmpty();
    }
    public Spell top(){
        return spells.get(spells.size()-1);
    }
    public Spell pop(){
        return spells.remove(spells.size()-1);
    }
}
