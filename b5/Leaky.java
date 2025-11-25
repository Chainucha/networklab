import java.util.Scanner;

public class Leaky {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        // 1. Get basic parameters
        System.out.print("Enter bucket capacity (in packets): ");
        int capacity = sc.nextInt();

        System.out.print("Enter output rate (packets per second): ");
        int outRate = sc.nextInt();

        System.out.print("Enter number of incoming packets: ");
        int n = sc.nextInt();

        int[] packets = new int[n];
        System.out.println("Enter size of each packet (in packets):");
        for (int i = 0; i < n; i++) {
            System.out.print("Packet " + (i + 1) + ": ");
            packets[i] = sc.nextInt();
        }

        int current = 0; // current packets in bucket

        System.out.println("\n--- Leaky Bucket Simulation ---");

        for (int i = 0; i < n; i++) {
            System.out.println("\nIncoming packet " + (i + 1) + " of size " + packets[i]);

            // Check if packet can be added
            if (packets[i] > capacity) {
                System.out.println(">> Packet size exceeds bucket capacity. Packet dropped.");
            } else if (current + packets[i] > capacity) {
                System.out.println(">> Bucket overflow. Packet dropped.");
            } else {
                current += packets[i];
                System.out.println(">> Packet accepted. Current bucket content: " + current);
            }

            // Leak out at fixed rate
            Thread.sleep(1000); // 1 second
            int sent = Math.min(outRate, current);
            current -= sent;
            System.out.println(">> Leaked out: " + sent + " packets. Remaining in bucket: " + current);
        }

        // After all arrivals, empty remaining bucket
        while (current > 0) {
            Thread.sleep(1000);
            int sent = Math.min(outRate, current);
            current -= sent;
            System.out.println(">> Leaked out: " + sent + " packets. Remaining in bucket: " + current);
        }

        System.out.println("\nAll packets processed. Bucket empty.");
        sc.close();
    }
}
