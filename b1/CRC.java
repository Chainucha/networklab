import java.io.*;

public class CRC {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // -----------------------------
        // 1. Read data bits
        // -----------------------------
        System.out.print("Enter number of data bits: ");
        int data_bits = Integer.parseInt(br.readLine());
        int[] data = new int[data_bits];

        System.out.println("Enter data bits: ");
        for (int i = 0; i < data_bits; i++)
            data[i] = Integer.parseInt(br.readLine());

        // -----------------------------
        // 2. Read generator bits
        // -----------------------------
        System.out.print("Enter number of generator bits: ");
        int gen_bits = Integer.parseInt(br.readLine());
        int[] gen = new int[gen_bits];

        System.out.println("Enter generator bits: ");
        for (int i = 0; i < gen_bits; i++)
            gen[i] = Integer.parseInt(br.readLine());

        // -----------------------------
        // 3. Append zero bits to data
        // -----------------------------
        int total_len = data_bits + gen_bits - 1;
        int[] msg = new int[total_len];

        System.arraycopy(data, 0, msg, 0, data_bits);

        System.out.print("Message after appending zeros: ");
        printArray(msg);

        // -----------------------------
        // 4. Compute remainder (CRC)
        // -----------------------------
        int[] remainder = computeCRC(msg, gen);

        System.out.print("Remainder: ");
        printArray(remainder);

        // -----------------------------
        // 5. Create final CRC code
        // -----------------------------
        int[] crc = new int[total_len];
        for (int i = 0; i < total_len; i++)
            crc[i] = msg[i] ^ remainder[i];

        System.out.print("Final CRC code: ");
        printArray(crc);

        // -----------------------------
        // 6. Check received code
        // -----------------------------
        System.out.println("\nEnter received CRC code (" + total_len + " bits):");
        int[] recv = new int[total_len];
        for (int i = 0; i < total_len; i++)
            recv[i] = Integer.parseInt(br.readLine());

        int[] check = computeCRC(recv, gen);

        boolean error = false;
        for (int r : check)
            if (r != 0)
                error = true;

        if (error)
            System.out.println("Error detected!");
        else
            System.out.println("No error detected.");
    }

    // =======================================================
    // Simple CRC division function (very easy to understand)
    // =======================================================
    static int[] computeCRC(int[] msg, int[] gen) {

        int[] rem = msg.clone();   // copy message
        int glen = gen.length;

        for (int i = 0; i <= rem.length - glen; i++) {
            if (rem[i] == 1) {
                for (int j = 0; j < glen; j++)
                    rem[i + j] ^= gen[j];   // XOR
            }
        }
        return rem;
    }

    // Utility function: print array
    static void printArray(int[] arr) {
        for (int bit : arr)
            System.out.print(bit + " ");
        System.out.println();
    }
}
