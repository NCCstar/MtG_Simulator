package Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Family on 6/6/17.
 */
public class Battlefield extends Zone{
    private List<Permanent> perms;

    public Battlefield()
    {
        perms = new ArrayList<>();
        setCards(new ArrayList<>());
    }
    public void enter(Permanent in)
    {
        perms.add(in);
        getCards().add(in);
        //TODO: Actually check entering effects
    }
    public List<Permanent> getPerms()
    {
        return perms;
    }
}
