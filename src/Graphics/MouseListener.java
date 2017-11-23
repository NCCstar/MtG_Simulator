package Graphics;

import Engine.Card;

import java.awt.event.MouseEvent;
import java.util.List;

public class MouseListener implements java.awt.event.MouseListener {

    private Display display;
    private List<CardImage> cardsList;
    public MouseListener(Display display) {
        display.addMouseListener(this);

        this.display = display;
    }

    public void updateCardImages(List<CardImage> images)
    {
        cardsList = images;
    }

    private CardImage findClicked(MouseEvent e)
    {
        double xHit = e.getX();
        double yHit = e.getY();
        for (CardImage c : cardsList)
        {
            if(xHit>c.getX() && xHit<c.getX()+CardImage.getWidth()
                    && yHit>c.getY() && yHit<c.getY()+CardImage.getHeight())
                return c;
        }
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(display.pass.contains(e.getX(), e.getY())) {
            System.out.println("D: Passing Priority");
            //pass priority
            return;
        }
        CardImage clicked = findClicked(e);
        if(clicked!=null) {
            System.out.println("Card clicked not null");
            Card found = clicked.getOrigin();
            if(e.getButton()==MouseEvent.BUTTON3) {
                System.out.println(found.toString());
            }
            if(e.getButton()==MouseEvent.BUTTON1) {
                System.out.println("Playing card");
                display.getController().playCardHere(found.getOwner(),found);
            }
        }
        else {
            System.out.println("Card clicked null");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
