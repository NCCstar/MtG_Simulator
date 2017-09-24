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
    private static Display display;
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        controller = new Controller(2);

        JFrame frame = new JFrame("MtG Simulator");
        display = new Display(controller);
        frame.setSize(1000,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(display);
        frame.setVisible(true);
        frame.setResizable(true);//may be false

        tick();
    }
    public static void tick()
    {
        display.updateHand(0);
        display.updateHand(1);
        display.updateBattlefield();
        display.repaint();
    }
}