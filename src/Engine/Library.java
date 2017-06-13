package Engine;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Family on 6/6/17.
 */
public class Library extends Zone{
    public Library(String deckName)
    {
        List<Card> cards = new ArrayList<>();
        try {
            Scanner input = new Scanner(new FileReader("/Users/Family/IdeaProjects/MtG_Simulator/Decks/"+deckName));
            while (input.hasNextLine()) {
                String[] line = input.nextLine().split("\\*");
                for(int i=0;i<Integer.parseInt(line[1]);i++)
                {
                    cards.add(CardMapper.map(line[0]));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        setCards(cards);
    }
    public void shuffle()
    {
        Collections.shuffle(getCards());
    }
}
