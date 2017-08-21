package Engine;

/**
 * Created by Family on 6/6/17.
 */
public class Permanent extends Card {
    private boolean isToken;
    private Card origin;
    private Player controller;
    public Permanent(String entryText,boolean isToken,Player controller)
    {
        super(entryText);
        this.isToken = isToken;
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
    public void setController(Player player){
        controller = player;
    }

    public Player getController() {
        if(controller==null)
            return getController();
        return controller;
    }
}
