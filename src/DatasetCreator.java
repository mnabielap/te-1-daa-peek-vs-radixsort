import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DatasetCreator {
    public static void main(String[] args) {
        long seed = 45124562;
        int len_arr = 5_000_000;
        int option = 3;

        int[] A;
        String order_name;
        if (option == 1) {
            System.out.println("### RANDOM ARRAY ###");
            A = Inputs.randomPermutation(len_arr, new Random(seed));
            order_name = "1_Randomized";
        } else if (option == 2) {
            System.out.println("### SORTED ARRAY ###");
            A = Inputs.sortedIncreasingArray(len_arr);
            order_name = "2_Sorted";
        } else {
            System.out.println("### REVERSED ARRAY ###");
            A = Inputs.sortedDecreasingArray(len_arr);
            order_name = "3_Reversed";
        }

        // Print array to console
//        System.out.println(Arrays.toString(A));

        // Write array to a text file
        String fileName = order_name+ "_" + len_arr + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int num : A) {
                writer.write(Integer.toString(num));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
