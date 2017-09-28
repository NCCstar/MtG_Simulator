import java.io.*;
import java.net.*;

public class TestClient {
    public static void main(String[] args)
    {
        try(Socket socket = new Socket("lcars.local", 9898);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));)
        {
            out.println("Present and accounted for");
            while(true)
            {}
        }
        catch(IOException e)
        {
            System.out.println("Whoops!");
        }
    }
}
