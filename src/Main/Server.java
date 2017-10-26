package Main;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Server implements Runnable {
    private ServerSocket server;
    private int connections;
    private int numReady;
    public Server()
    {}
    public void run() {
        Socket[] socket = new Socket[2];
        try
        {
            server = new ServerSocket(0);
            System.out.println("S: Listening on port: " + server.getLocalPort());
            System.out.println("S: Attempting connection 1");
            socket[0] = server.accept();
            System.out.println("S: Connected!");
            connections++;
            System.out.println("S: Attempting connection 2");
            socket[1] = server.accept();
            System.out.println("S: Connected!");
            connections++;
            BufferedReader[] input = new BufferedReader[2];
            PrintStream[] output = new PrintStream[2];
            input[0] = new BufferedReader(new InputStreamReader(socket[0].getInputStream()));
            output[0] = new PrintStream(socket[0].getOutputStream());

            input[1] = new BufferedReader(new InputStreamReader(socket[1].getInputStream()));
            output[1] = new PrintStream(socket[1].getOutputStream());
            final long ranSeed = new Random().nextLong();
            final int first = (int)(Math.random()*2);
            for(int i = 0; i < 2; i++)
            {
                final int index = i;
                new Thread( () ->
                {
                    while (true)
                    {
                        try {
                            String line = input[index].readLine();
                            System.out.println("S:" + index + " I hear: " + line);
                            if(line!=null) {
                                if(line.equals("Random?")) {
                                    output[index].println(ranSeed);
                                }
                                if(line.indexOf("Direct") == 0) {
                                    output[index ^ 1].println(line);
                                }
                                if(line.equals("Ready"))
                                {
                                    numReady++;
                                    if(numReady>=2)
                                    {
                                        output[0].println("Ready");
                                        output[1].println("Ready");
                                    }
                                }
                                if(line.equals("First?"))
                                {
                                    if(index == 1)
                                        output[index].println(first^1);
                                    else
                                        output[index].println(first);
                                }
                            }
                        } catch (IOException e) {
                        }
                    }
                }).start();
            }
        }
        catch(IOException e)
        {
            System.out.println("S: It broke.");
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
