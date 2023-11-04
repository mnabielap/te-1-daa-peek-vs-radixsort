import java.util.Arrays;
import java.util.Random;

public class Main {
    static StringBuilder outStrBldr = new StringBuilder();
    
    public static void main(String[] args) {
        int[] A;
        long beforeUsedMem, afterUsedMem, actualMemUsed;
        // ####### RUNNER OPTION ########
        long seed = 45124562;
        int len_arr = 1_000;
        // RANDOM = 1
        // SORTED = 2
        // REVERSED = 3
        int option = 3;
        // PEEK = 1
        // RADIX = 2
        int sort_num = 2;
        // ##############################
        if (option == 1) {
            System.out.println("### RANDOM ARRAY ###");
            A = Inputs.randomPermutation(len_arr, new Random(seed));
        } else if (option == 2) {
            System.out.println("### SORTED ARRAY ###");
            A = Inputs.sortedIncreasingArray(len_arr);
        } else {
            System.out.println("### REVERSED ARRAY ###");
            A = Inputs.sortedDecreasingArray(len_arr);
        }
        System.out.println("array A: "+Arrays.toString(Arrays.copyOf(A, 10)));
        System.out.println("panjang array: "+len_arr+"\n");
        // #############################################################################################
        // https://stackoverflow.com/questions/37916136/how-to-calculate-memory-usage-of-a-java-program
        beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (sort_num == 1) {
            testPeek(A);
        } else if (sort_num == 2) {
            testRadix(A);
        } else if (sort_num == 3) {
            testOriginalMerge(A); // TAMBAHAN: hanya untuk membandingkan dengan Peek-Sort
        } else {
            Arrays.sort(A); // TAMBAHAN: hanya untuk tes 0 bytes memory used [QUICK-SORT]
        }
        afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        actualMemUsed = afterUsedMem - beforeUsedMem;
        // #############################################################################################
        System.out.println(outStrBldr.toString());
        System.out.println("used memory "+actualMemUsed/1024L+" KB");
    }
    
    public static void testPeek(int[] A) {
        long startTime, endTime, elapsedTime;
        double elapsedTimeInMS, elapsedTimeInSec;
        // ###################### PeekSort ######################
        outStrBldr.append("###################### PeekSort ######################\n");
        
        startTime = System.nanoTime();
        PeekSort.peeksort(A, 0, A.length - 1);
        endTime = System.nanoTime();
        
        outStrBldr.append("array terurut? "+Util.isSorted(A)+"\n");
        
        elapsedTime = endTime - startTime;
        outStrBldr.append("\n"+elapsedTime +" ns");
        elapsedTimeInMS = (double) elapsedTime / 1_000_000;
        outStrBldr.append("\n"+elapsedTimeInMS + " ms");
        elapsedTimeInSec = (double) elapsedTime / 1_000_000_000;
        outStrBldr.append("\n"+elapsedTimeInSec + " s\n");
    }
    
    
    
    public static void testRadix(int[] B) {
        long startTime, endTime, elapsedTime;
        double elapsedTimeInMS, elapsedTimeInSec;
        // ###################### RadixSort ######################
        outStrBldr.append("###################### RadixSort ######################\n");
        
        startTime = System.nanoTime();
        RadixSort.radixsort(B, B.length);
        endTime = System.nanoTime();
      
        outStrBldr.append("array terurut? "+Util.isSorted(B)+"\n");
      
        elapsedTime = endTime - startTime;
        outStrBldr.append("\n"+elapsedTime +" ns");
        elapsedTimeInMS = (double) elapsedTime / 1_000_000;
        outStrBldr.append("\n"+elapsedTimeInMS + " ms");
        elapsedTimeInSec = (double) elapsedTime / 1_000_000_000;
        outStrBldr.append("\n"+elapsedTimeInSec + " s\n");
    }
    
    public static void testOriginalMerge(int[] B) {
        long startTime, endTime, elapsedTime;
        double elapsedTimeInMS, elapsedTimeInSec;
        // ###################### MergeSort ######################
        outStrBldr.append("###################### MergeSort ######################\n");
        
        startTime = System.nanoTime();
        OriginalMergeSort.sort(B, 0, B.length-1);
        endTime = System.nanoTime();
      
        outStrBldr.append("array terurut? "+Util.isSorted(B)+"\n");
      
        elapsedTime = endTime - startTime;
        outStrBldr.append("\n"+elapsedTime +" ns");
        elapsedTimeInMS = (double) elapsedTime / 1_000_000;
        outStrBldr.append("\n"+elapsedTimeInMS + " ms");
        elapsedTimeInSec = (double) elapsedTime / 1_000_000_000;
        outStrBldr.append("\n"+elapsedTimeInSec + " s\n");
    }
    
}
