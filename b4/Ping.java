
import java.io.*;

public class Ping {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String ipaddr;
            System.out.print("Enter the IP address : ");
            ipaddr = br.readLine();
            boolean reachable = (java.lang.Runtime.getRuntime().exec("ping -n 1 "
                    + ipaddr).waitFor() == 0);
            if (reachable) {
                System.out.println("IP is reachable:: " + ipaddr); 
            }else {
                System.out.println("IP is not reachable: " + ipaddr);
            }
        } catch (Exception e) {
        }
    }
}
