package Engine;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Family on 6/6/17.
 */
public class Card {
    private List<Supertype> supertypes;
    private List<Type> types;
    private List<String> subtypes;
    private Player owner;
    private Player controller;
    private String name;
    private List<Color> colors;

    private Zone location;

    public Card(String entryText)
    {//full name*mana cost(3UGR)*supertypes*types*subtype1,subtype2*Spell Ability*Activator:Ability*Trigger:Ability*Static Ability(if creature - *power*toughness)

        String[] lines = entryText.split("\\*");
        int i=0;
        name = lines[i++];
        i++;//mana cost
        
    }

    public String getName()
    {
        return name;
    }

    public String toString()
    {
        String ans = "";
        ans += name+"\n";
        String supertypeString = "";
        for(Supertype s:supertypes)
        {
            supertypeString += s.name()+" ";
        }
        String typeString = "";
        for(Type s:types)
        {
            typeString += s.name()+" ";
        }
        String subtypeString = "";
        for(String s:subtypes)
        {
            subtypeString += s +" ";
        }
        ans += supertypeString + typeString + "- "+ subtypeString;
        return ans;
    }
}
