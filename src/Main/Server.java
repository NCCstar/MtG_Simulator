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

                new Thread(() ->
                {
                        try
                        {
                            String line = input[1].readLine();
                            if (line != null) {
                                System.out.println("S:"+1+": I hear: " + line);
                                if (line.equals("Random?")) {
                                    output[1].println(ranSeed);
                                }
                                if (line.indexOf("Direct") == 0) {
                                    output[1 ^ 1].println(line);
                                }
                            }
                        } catch (IOException e)
                        { }
                }).start();

            new Thread(() ->
            {
                try
                {
                    String line = input[0].readLine();
                    if (line != null) {
                        System.out.println("S:"+0+": I hear: " + line);
                        if (line.equals("Random?")) {
                            output[0].println(ranSeed);
                        }
                        if (line.indexOf("Direct") == 0) {
                            output[0 ^ 1].println(line);
                        }
                    }
                } catch (IOException e)
                { }
            }).start();

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
