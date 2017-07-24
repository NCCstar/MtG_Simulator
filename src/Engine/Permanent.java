package Engine;

/**
 * Created by Family on 6/6/17.
 */
public class Permanent extends Card {
    private boolean isToken;
    private Card origin;
    public Permanent(String entryText,boolean isToken)
    {
        super(entryText);
        this.isToken = isToken;
    }
    public Permanent(String entryText)
    {
        super(entryText);
    }
    public Permanent(Card card)
    {
        super(card);
        origin = card;
    }
}
