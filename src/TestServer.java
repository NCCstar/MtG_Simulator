import java.io.*;
import java.net.*;

public class TestServer {
    public static void main(String[] args) {
        ServerSocket server;
        Socket client;
        DataInputStream input;
        PrintStream output;
        try
        {
            server = new ServerSocket(9898);
            System.out.println("Attempting connection");
            client = server.accept();
            System.out.println("Connected!");
            input = new DataInputStream(client.getInputStream());
            output = new PrintStream(client.getOutputStream());
        }
        catch(IOException e)
        {
            System.out.println(e);
            client = new Socket();
        }
        System.out.println(client.getLocalAddress());
    }
}
