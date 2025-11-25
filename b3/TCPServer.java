import java.io.*;
import java.net.*;

public class TCPServer
{
    public static void main(String[] args) throws Exception
    {
        String clientString;
        String CapString;
        ServerSocket socket = new ServerSocket(1234);
        while(true)
        {
            Socket conn = socket.accept();
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            DataOutputStream toClient = new DataOutputStream(conn.getOutputStream());

            clientString = fromClient.readLine();
            CapString = clientString.toUpperCase();
            toClient.writeBytes(CapString);

        }
    }
}