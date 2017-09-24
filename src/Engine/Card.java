package Engine;

import Graphics.CardImage;

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
    private String name;
    private List<Color> colors;
    private String manaString;
    private List<Mana> manaCost;
    private String spellAbility;
    private String activatedAbility;
    private String triggeredAbility;
    private String staticAbility;
    private int power;
    private int toughness;

    private CardImage image;

    private Zone location;

    public String getActivatedAbility() {
        return activatedAbility;
    }

    public String getTriggeredAbility() {
        return triggeredAbility;
    }

    public String getStaticAbility() {
        return staticAbility;
    }

    public int getPower() {
        return power;
    }

    public int getToughness() {
        return toughness;
    }

    public Card(Card base)
    {
        name = base.getName();
        manaCost = base.getManaCost();

        colors = base.getColors();
        supertypes = base.getSupertypes();
        types = base.getTypes();
        subtypes = base.getSubtypes();
        spellAbility = base.getSpellAbility();
        activatedAbility = base.getActivatedAbility();
        triggeredAbility = base.getTriggeredAbility();
        staticAbility = base.getStaticAbility();
        power = base.getPower();
        toughness = base.getToughness();
        owner = base.getOwner();
        image = new CardImage(this);
    }

    public Card(String entryText)
    {
        this(entryText,null);
    }

    public Card(String entryText,Player owner)
    {//full name*mana cost(3UGR)*supertypes*types*subtype1,subtype2*Spell Ability*Activator:Ability*Trigger:Ability*Static Ability(if creature - *power*toughness)
        this.owner = owner;
        String[] lines = entryText.split("\\*");
        int i=0;
        name = lines[i++];
        manaString = lines[i++];//mana cost
        manaCost = new ArrayList();
        ArrayList<Character> costList = new ArrayList<>();
        for(Character c:manaString.toCharArray())
        {
            costList.add(c);
        }
        if(Character.isDigit(costList.get(0)))
        {
            int generic = Character.getNumericValue(costList.remove(0));
            for(int j=0;j<generic;j++)
            {
                manaCost.add(new Mana(Mana.Type.X));
            }
        }
        while(costList.size()>0)
        {
            char next = costList.remove(0);
            switch(next)
            {
                case 'W':
                    manaCost.add(0,new Mana(Mana.Type.W));
                    break;
                case 'U':
                    manaCost.add(0,new Mana(Mana.Type.U));
                    break;
                case 'B':
                    manaCost.add(0,new Mana(Mana.Type.B));
                    break;
                case 'R':
                    manaCost.add(0,new Mana(Mana.Type.R));
                    break;
                case 'G':
                    manaCost.add(0,new Mana(Mana.Type.G));
                    break;
                case 'C':
                    manaCost.add(0,new Mana(Mana.Type.C));
                    break;
            }
        }
        colors = new ArrayList<>();
        if(costList.contains('W')) {
            colors.add(Color.White);
        }
        if(costList.contains('U')) {
            colors.add(Color.Blue);
        }
        if(costList.contains('B')) {
            colors.add(Color.Black);
        }
        if(costList.contains('R')) {
            colors.add(Color.Red);
        }
        if(costList.contains('G')) {
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
        image = new CardImage(this);
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

    public List<Mana> getManaCost() {
        return manaCost;
    }

    public String getManaString()
    {
        return manaString;
    }

    public String getSpellAbility() {
        return spellAbility;
    }

    public void setOwner(Player player){
        owner = player;
    }

    public Player getOwner() {
        return owner;
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
