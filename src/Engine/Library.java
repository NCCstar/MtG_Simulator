package Engine;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Family on 6/6/17.
 */
public class Library extends Zone{
    private Player owner;
    public Library(String deckName,Player owner)
    {
        this.owner = owner;
        List<Card> cards = new ArrayList<>();
        try {
            Scanner input = new Scanner(new FileReader("./Decks/"+deckName));
            while (input.hasNextLine()) {
                String[] line = input.nextLine().split("\\*");
                for(int i=0;i<Integer.parseInt(line[1]);i++)
                {
                    Card in = CardMapper.map(line[0]);
                    in.setOwner(owner);
                    cards.add(in);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        setCards(cards);
    }
    public Library(List<String> cardsIn, Player owner)
    {
        this.owner = owner;
        List<Card> cards = new ArrayList<>();

        for(String input:cardsIn)
        {
            String[] line = input.split("\\*");
            for (int i = 0; i < Integer.parseInt(line[1]); i++) {
                Card in = CardMapper.map(line[0]);
                in.setOwner(owner);
                cards.add(in);
            }
        }
    }



    public void shuffle()
    {
        Collections.shuffle(getCards(),owner.getController().getRandom());
    }
}