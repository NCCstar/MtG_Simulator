package Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Family on 6/6/17.
 */
public class Command extends Zone{
    private List<Permanent> permanents;
    public Command()
    {
        setCards(new ArrayList<>());
        permanents = new ArrayList<>();
    }

    public void add(Permanent p)
    {
        permanents.add(p);
    }
    public List<Permanent> getPermanents()
    {
        return permanents;
    }
}
