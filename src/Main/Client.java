package Main;

import Engine.Card;
import Engine.CardMapper;
import Engine.Controller;
import Graphics.Display;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Family on 6/6/17.
 */
public class Client {
    private static Controller controller;
    private static Display display;
    private static Server server;
    private static Socket connection;
    public static void main(String[] args) {
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Start a server?", "Main.Server?", JOptionPane.YES_NO_OPTION))
        {
            server = new Server();
            new Thread(server).start();
            try {
                Thread.sleep(100);
            }catch(InterruptedException e){}
            try {
                connection = new Socket(server.getAddress(), server.getPort());
            }
            catch(IOException e)
            {
                connection = new Socket();
                System.out.println("Connection failed. Restart Please");
            }
            JOptionPane.showMessageDialog(null,"Port: "+server.getPort(),"Port Number",JOptionPane.INFORMATION_MESSAGE);
            while(server.getConnections()<2)
            {}
        }
        else
        {
            while(true)
            {
                Scanner scanner = new Scanner(System.in);
                String host = JOptionPane.showInputDialog("Name of host?");
                int port = 0;
                while (true) {
                    try {
                        port = Integer.parseInt(JOptionPane.showInputDialog("Port number?"));
                        break;
                    } catch (NumberFormatException e) {
                    }
                }
                try {
                    connection = new Socket(host, port);
                    break;
                } catch (IOException e) {
                    connection = new Socket();
                    System.out.println("Connection failed.");
                }
            }
        }
        controller = new Controller(2,connection);

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
        display.update();
    }
}