import Engine.Card;
import Engine.Controller;

import java.util.List;

/**
 * Created by Family on 6/6/17.
 */
public class Client {
    private static Controller controller;
    public static void main(String[] args)
    {
        controller = new Controller(2);

        tick();
    }
    public static void tick()
    {
        System.out.println("Stack Cards:");
        for(Card card:controller.getStack().getCards())
        {
            System.out.println(card.toString());
        }
        System.out.println("Battefield Cards:");
        for(Card card:controller.getBattlefield().getCards())
        {
            System.out.println(card.toString());
        }
        System.out.println("Exile Cards:");
        for(Card card:controller.getExile().getCards())
        {
            System.out.println(card.toString());
        }
        for(int i=0;i<controller.getPlayers().size();i++)
        {
            System.out.println("Player "+(i+1));
            System.out.println("Hand Cards:");
            for(Card card:controller.getPlayers().get(i).getHand().getCards())
            {
                System.out.println(card.toString());
            }
            System.out.println("Graveyard Cards:");
            for(Card card:controller.getPlayers().get(i).getGraveyard().getCards())
            {
                System.out.println(card.toString());
            }
            System.out.println("Command Cards:");
            for(Card card:controller.getPlayers().get(i).getCommand().getCards())
            {
                System.out.println(card.toString());
            }
        }
    }
}
