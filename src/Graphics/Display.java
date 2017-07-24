package Graphics;

import Engine.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Family on 7/22/17.
 */
public class Display extends JPanel{
    public Display(Controller controller)
    {

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
        
    }
}
