package Engine;

import java.util.List;

/**
 * Created by Family on 6/6/17.
 */
public abstract class Zone {
    private List<Card> cards;
    boolean isPublic;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

}
