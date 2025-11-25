//Write a Java Program to demonstrate Simple UDP Datagram Communication.
// store in the file UDPClient.java

import java.net.*;

public class UDPClient {

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            String message = "Hello, server!";
            byte[] sendData = message.getBytes();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress,
                    9876);
            clientSocket.send(sendPacket);
            System.out.println("Sent: " + message);
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
// store in the file UDPServer.java
