package Graphics;

import Engine.Card;
import Engine.Controller;
import Engine.Zone;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Family on 7/22/17.
 */
public class Display extends JPanel{
    private Controller controller;
    private List<CardImage>[] hands = new ArrayList[2];
    public Display(Controller controller)
    {
        for(int i=0;i<hands.length;i++)
            hands[i] = new ArrayList<CardImage>();
        this.controller = controller;
        CardImage.setHeight(20);
        CardImage.setWidth(15);
    }
    public void updateHand(int pNum,List<Card> cardList)
    {
        List<CardImage> temp = new ArrayList<CardImage>();
        for(Card card:cardList)
        {
            temp.add(new CardImage(card));
        }
        hands[pNum]=temp;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
        //assuming 2 players
        for(int i=0;i<hands[0].size();i++)
        {
            hands[0].get(i).draw(g,i*20,10);
        }
    }
}
