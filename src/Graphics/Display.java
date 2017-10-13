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
    private MouseListener listener;
    private List<CardImage>[] hands = new ArrayList[2];
    private List<CardImage>[] battlefields = new ArrayList[2];
    private double halfHeight;

    public Display(Controller controller)
    {
        for(int i=0;i<hands.length;i++)
            hands[i] = new ArrayList<CardImage>();
        for(int i=0;i<battlefields.length;i++)
            battlefields[i] = new ArrayList<CardImage>();

        this.controller = controller;
        controller.setDisplay(this);

        CardImage.setHeight(CARD_HEIGHT);
        CardImage.setWidth(CARD_WIDTH);
        listener = new MouseListener(this);
    }

    public List<CardImage> getAllCards()
    {
        List<CardImage> ans = new ArrayList<>();
        ans.addAll(hands[0]);
        ans.addAll(hands[1]);
        ans.addAll(battlefields[0]);
        ans.addAll(battlefields[1]);
        return ans;
    }

    public void update()
    {
        updateBattlefield();
        updateHand(0);
        updateHand(1);
        repaint();
    }
    private void updateHand(int pNum)
    {
        List<Card> cardList = controller.getPlayers().get(pNum).getHand().getCards();
        List<CardImage> temp = new ArrayList<>();
        for(Card card:cardList)
        {
            temp.add(new CardImage(card));
        }
        hands[pNum]=temp;
        listener.updateCardImages(getAllCards());
    }

    private void updateBattlefield()
    {
        for(int pNum=0;pNum<2;pNum++)
        {
            List<Permanent> permList = new ArrayList<>(controller.getBattlefield().getPerms());
            for(int i=0;i<permList.size();i++)
            {
                if((permList.get(i).getController().getPNum()!=pNum))
                {
                    permList.remove(i);
                    i--;
                }
            }
            List<CardImage> temp = new ArrayList<>();
            for(Card card:permList)
            {
                temp.add(new CardImage(card));
            }
            battlefields[pNum]=temp;
        }
        listener.updateCardImages(getAllCards());
    }

    public Controller getController() {
        return controller;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        halfHeight = getHeight()/2.0;
        g.setColor(Color.black);
        g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
        //assuming 2 players
        for(int i=0;i<hands[0].size();i++)
        {
            CardImage drawn = hands[0].get(i);
            drawn.setX(i*(CARD_WIDTH+10)+10);
            drawn.setY(10);
            drawn.draw(g);
        }
        for(int i=0;i<battlefields[0].size();i++)
        {
            CardImage drawn = battlefields[0].get(i);
            drawn.setX(i*(CARD_WIDTH+10)+10);
            drawn.setY(halfHeight-20-CARD_HEIGHT);
            drawn.draw(g);
        }
        for(int i=0;i<battlefields[1].size();i++)
        {
            CardImage drawn = battlefields[1].get(i);
            drawn.setX(i*(CARD_WIDTH+10)+10);
            drawn.setY(halfHeight+20+CARD_HEIGHT);
            drawn.draw(g);
        }
        for(int i=0;i<hands[1].size();i++)
        {
            CardImage drawn = hands[1].get(i);
            drawn.setX(i*(CARD_WIDTH+10)+10);
            drawn.setY(getHeight()-10-CARD_HEIGHT);
            drawn.draw(g);
        }
        listener.updateCardImages(getAllCards());
    }
}
