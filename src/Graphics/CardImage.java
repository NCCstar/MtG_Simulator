package Graphics;

import Engine.Card;

import java.awt.*;
import java.util.List;

/**
 * Created by Rhys on 7/23/17.
 */
public class CardImage {
    //width and height same for all card images - thus static
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
    public static double getWidth() {
        return width;
    }

    public static double getHeight() {
        return height;
    }

    private double x,y;

    private Card origin;

    public CardImage(Card card)
    {
        origin = card;
    }


    public void draw(Graphics g)
    {
        List<Engine.Color> colors = origin.getColors();
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
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Card getOrigin() {
        return origin;
    }
}
