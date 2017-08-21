package Graphics;

import Engine.Card;

import java.awt.*;
import java.util.List;

/**
 * Created by Rhys on 7/23/17.
 */
public class CardImage {
    private static double width;
    private static double height;
    public static void setWidth(double w)
    {
        width = w;
    }
    public static void setHeight(double h)
    {
        height = h;
    }
    private Card card;
    public CardImage(Card card)
    {
        this.card = card;
    }
    public void draw(Graphics g,double x,double y)
    {
        List<Engine.Color> colors = card.getColors();
        if(colors.size()>1) {
            g.setColor(Color.yellow.darker());
        }
        else {
            if (colors.size() < 1) {
                g.setColor(Color.pink);
            } else {
                switch (colors.get(0)) {
                    case White:
                        g.setColor(Color.white);
                        break;
                    case Blue:
                        g.setColor(Color.blue);
                        break;
                    case Black:
                        g.setColor(Color.black);
                        break;
                    case Red:
                        g.setColor(Color.red);
                        break;
                    case Green:
                        g.setColor(Color.green);
                        break;
                    default:
                        g.setColor(Color.pink);
                        break;
                }
            }
        }
        g.fillRect((int)x,(int)y,(int)width,(int)height);
    }
}
