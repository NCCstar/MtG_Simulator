package Graphics;

import Engine.Card;
import Engine.Controller;
import Engine.Permanent;
import Engine.Zone;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Family on 7/22/17.
 */
public class Display extends JPanel{
    private static final double CARD_WIDTH = 25;
    private static final double CARD_HEIGHT = 35;

    private Controller controller;
    private List<CardImage>[] hands = new ArrayList[2];
    private List<CardImage>[] battlefields = new ArrayList[2];
    private double halfHeight;
    public Display(Controller controller)
    {
        for(List<CardImage> list:hands)
            list = new ArrayList<CardImage>();
        for(List<CardImage> list:battlefields)
            list = new ArrayList<CardImage>();

        this.controller = controller;

        CardImage.setHeight(CARD_HEIGHT);
        CardImage.setWidth(CARD_WIDTH);
    }
    public void updateHand(int pNum)
    {
        List<Card> cardList = controller.getPlayers().get(pNum).getHand().getCards();
        List<CardImage> temp = new ArrayList<CardImage>();
        for(Card card:cardList)
        {
            temp.add(new CardImage(card));
        }
        hands[pNum]=temp;
    }
    public void updateBattlefield()
    {
        for(int pNum=0;pNum<2;pNum++)
        {
            List<Permanent> permList = controller.getBattlefield().getPerms();
            for(int i=0;i<permList.size();i++)
            {
                if((permList.get(i).getController().getPNum()!=pNum))
                {
                    permList.remove(i);
                    i--;
                }
            }
            List<CardImage> temp = new ArrayList<CardImage>();
            for(Card card:permList)
            {
                temp.add(new CardImage(card));
            }
            battlefields[pNum]=temp;
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        halfHeight = getHeight()/2.0;
        g.setColor(Color.black);
        g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
        //assuming 2 players
        for(int i=0;i<hands[0].size();i++)
        {
            hands[0].get(i).draw(g,i*(CARD_WIDTH+10)+10,10);
        }
        for(int i=0;i<battlefields[0].size();i++)
        {
            battlefields[0].get(i).draw(g,i*(CARD_WIDTH+10)+10,halfHeight-20-CARD_HEIGHT);
        }
        for(int i=0;i<battlefields[1].size();i++)
        {
            battlefields[1].get(i).draw(g,i*(CARD_WIDTH+10)+10,halfHeight+20+CARD_HEIGHT);
        }
        for(int i=0;i<hands[1].size();i++)
        {
            hands[1].get(i).draw(g,i*(CARD_WIDTH+10)+10,getHeight()-10-CARD_HEIGHT);
        }
    }
}
