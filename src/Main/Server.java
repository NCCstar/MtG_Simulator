package Main;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Server implements Runnable {
    private ServerSocket server;
    private int connections;
    public Server()
    {}
    public void run() {
        Socket[] socket = new Socket[2];
        try
        {
            server = new ServerSocket(0);
            System.out.println("Listening on port: " + server.getLocalPort());
            System.out.println("Attempting connection 1");
            socket[0] = server.accept();
            System.out.println("Connected!");
            connections++;
            System.out.println("Attempting connection 2");
            socket[1] = server.accept();
            System.out.println("Connected!");
            connections++;
            BufferedReader[] input = new BufferedReader[2];
            PrintStream[] output = new PrintStream[2];
            input[0] = new BufferedReader(new InputStreamReader(socket[0].getInputStream()));
            output[0] = new PrintStream(socket[0].getOutputStream());

            input[1] = new BufferedReader(new InputStreamReader(socket[1].getInputStream()));
            output[1] = new PrintStream(socket[1].getOutputStream());
            int i=0;
            long ranSeed = new Random().nextLong();
            while(true)
            {
                String line = input[i].readLine();
                if(line!=null) {
                    System.out.println("I hear: " + line);
                    if(line.equals("Random?"))
                    {
                        output[i].println(ranSeed);
                    }
                }
                if(i==0)
                    i=1;
                else
                    i=0;
            }
        }
        catch(IOException e)
        {
            System.out.println("It broke.");
        }
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
