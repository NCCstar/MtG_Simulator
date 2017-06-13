package Engine;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Family on 6/12/17.
 */
public class CardMapper {
    private static Map<String,Card> cardMap;
    static {
        try {
            Scanner input = new Scanner(new FileReader("Decks/Index.txt"));
            cardMap = new HashMap<>();
            while(input.hasNextLine())
            {
                Card temp = new Card(input.nextLine());
                cardMap.put(temp.getName(),temp);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static Card map(String name)
    {
        return cardMap.get(name);
    }
}
