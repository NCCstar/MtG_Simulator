package Main;

import java.io.*;
import java.net.*;

public class Server implements Runnable {
    private ServerSocket server;
    private int connections;
    public Server()
    {}
    public void run() {
        Socket socket1;
        Socket socket2;
        try
        {
            server = new ServerSocket(0);
            System.out.println("Listening on port: " + server.getLocalPort());
            System.out.println("Attempting connection 1");
            socket1 = server.accept();
            System.out.println("Connected!");
            connections++;
            System.out.println("Attempting connection 2");
            socket2 = server.accept();
            System.out.println("Connected!");
            connections++;
            BufferedReader input1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
            PrintStream output1 = new PrintStream(socket1.getOutputStream());

            BufferedReader input2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
            PrintStream output2 = new PrintStream(socket2.getOutputStream());
            while(true)
            {
                String line = input1.readLine();
                if(line!=null)
                    System.out.println("I hear from 1: "+line);
                line = input2.readLine();
                if(line!=null)
                    System.out.println("I hear from 2: "+line);
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
            socket1 = new Socket();
        }
        System.out.println(socket1.getLocalAddress());
    }
    public int getPort()
    {
        return server.getLocalPort();
    }
    public InetAddress getAddress()
    {
        return server.getInetAddress();
    }
    public int getConnections()
    {
        return connections;
    }
}
