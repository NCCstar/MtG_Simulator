package Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Family on 6/6/17.
 */
public class Card {
    private List<Supertype> supertypes;
    private List<Type> types;
    private List<LandType> landTypes;
    private List<String> subtypes;
    private Player owner;
    private Player controller;
    private String name;
    private List<Color> colors;
    private String cost;//temporary?
    private String spellAbility;
    private String activatedAbility;
    private String triggeredAbility;
    private String staticAbility;
    private int power;
    private int toughness;

    private Zone location;

    public Card()
    {

    }

    public Card(String entryText)
    {//full name*mana cost(3UGR)*supertypes*types*subtype1,subtype2*Spell Ability*Activator:Ability*Trigger:Ability*Static Ability(if creature - *power*toughness)
        String[] lines = entryText.split("\\*");
        int i=0;
        name = lines[i++];
        cost = lines[i++];//mana cost
        colors = new ArrayList<>();
        if(cost.contains("W")) {
            colors.add(Color.White);
        }
        if(cost.contains("U")) {
            colors.add(Color.Blue);
        }
        if(cost.contains("B")) {
            colors.add(Color.Black);
        }
        if(cost.contains("R")) {
            colors.add(Color.Red);
        }
        if(cost.contains("G")) {
            colors.add(Color.Green);
        }

        supertypes = new ArrayList<>();
        String[] supertypeStrings = lines[i++].split(",");
        for(String s:supertypeStrings)
        {
            if(s.equals(" "))
                break;
            supertypes.add(Supertype.valueOf(s));
        }

        types = new ArrayList<>();
        String[] typeStrings = lines[i++].split(",");
        for(String s:typeStrings)
        {
            if(s.equals(" "))
                break;
            types.add(Type.valueOf(s));
        }

        subtypes = new ArrayList<>();
        subtypes.addAll(Arrays.asList(lines[i++].split(",")));

        spellAbility = lines[i++];
        activatedAbility = lines[i++];
        triggeredAbility = lines[i++];
        staticAbility = lines[i++];

        if(types.contains(Type.Creature))
        {
            power = Integer.parseInt(lines[i++]);
            toughness = Integer.parseInt(lines[i++]);
        }
    }

    public String getName()
    {
        return name;
    }

    public List<Supertype> getSupertypes() {
        return supertypes;
    }

    public List<Type> getTypes() {
        return types;
    }

    public List<String> getSubtypes() {
        return subtypes;
    }

    public List<Color> getColors() {
        return colors;
    }

    public String getCost() {
        return cost;
    }

    public String getSpellAbility() {
        return spellAbility;
    }

    public String toString()
    {
        String ans = " - ";
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
