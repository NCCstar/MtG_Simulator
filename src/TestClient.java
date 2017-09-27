import java.io.IOException;
import java.net.Socket;

public class TestClient {
    public static void main(String[] args)
    {
        try {
            Socket connection = new Socket("lcars.local", 9898);
        }
        catch(IOException e)
        {
            System.out.println("Whoops!");
        }
    }
}
