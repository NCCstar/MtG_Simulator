package Engine;

import java.util.List;

/**
 * Created by Family on 6/6/17.
 */
public class Spell extends Card {
    private boolean isToken;
    private Card origin;
    private Player controller;
    public Spell(String entryText,boolean isToken,Player controller)
    {
        super(entryText);
        this.isToken = isToken;
        this.controller = controller;
    }
    public Spell(String entryText)
    {
        super(entryText);
    }
    public Spell(Card card,Player controller)
    {
        super(card);
        origin = card;
        this.controller = controller;
    }

    public void resolve()
    {
        List<Type> types = origin.getTypes();
        if(types.contains(Type.Creature)||types.contains(Type.Artifact)||types.contains(Type.Enchantment)||types.contains(Type.Planeswalker))
        {
            Permanent permanent = this.toPermanent();
            controller.getController().getBattlefield().enter(permanent);
        }
    }

    public void setController(Player player){
        controller = player;
    }

    public Player getController() {
        return controller;
    }
    public Permanent toPermanent()
    {
        return new Permanent(origin,controller);
    }
}
