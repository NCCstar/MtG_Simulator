import Engine.Card;
import Engine.CardMapper;
import Engine.Controller;
import Graphics.Display;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Family on 6/6/17.
 */
public class Client {
    private static Controller controller;
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        controller = new Controller(2);

        JFrame frame = new JFrame("MtG Simulator");
        Display display = new Display(controller);
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(display);
        frame.setVisible(true);
        frame.setResizable(true);//may be false

        tick();
        input.nextLine();
        controller.getPlayers().get(0).playCard(CardMapper.map("Island"));
        controller.getPlayers().get(1).playCard(CardMapper.map("Swamp"));
        tick();
        input.nextLine();
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
