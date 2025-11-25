import java.io.*;
import java.net.*;

public class TCPClientTest
{
    public static void main(String args[]) throws Exception
    {
        String msgString;
        String moddedString;

        Socket socket = new Socket("localhost",1234);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println("Enter msg");
        msgString = br.readLine();

        toServer.writeBytes(msgString + "\n");
        moddedString = fromServer.readLine();

        System.out.println("from server:" + moddedString);

    }
}