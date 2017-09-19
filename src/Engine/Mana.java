package Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Family on 6/6/17.
 */
public class Mana {
    public enum Type {W,U,B,R,G,C,X}//W,U,B,R,G,C,X(generic, costs only)

    private Mana.Type type;

    public Mana(Mana.Type type)
    {
        this.type = type;
    }
    public Mana.Type getType()
    {
        return type;
    }
    public static List<Mana> subtract(List<Mana> big,List<Mana> lil) throws ManaException
    {
        List<Mana> ans = new ArrayList<>();

        for(Mana i:lil)
        {
            if(i.getType() == Mana.Type.X)
            {
                if(big.remove(0)!=null)
                {
                    lil.remove(i);
                }
                else
                {
                    throw new ManaException();
                }

            }
            else
            {
                if(big.remove(i))
                {
                    lil.remove(i);
                }
                else
                {
                    throw new ManaException();
                }
            }
        }
        return ans;
    }
}
