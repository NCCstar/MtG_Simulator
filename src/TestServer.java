import java.io.*;
import java.net.*;

public class TestServer {
    public static void main(String[] args) {
        int portNumber = 1701;

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            int i=0;
            String inputLine;
            System.out.println(clientSocket.getInetAddress());
            while ((inputLine = in.readLine()) != null)
            {
                System.out.println(i);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
