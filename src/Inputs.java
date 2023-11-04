import java.util.Random;

public class Inputs {
    public static int[] randomPermutation(final int len, Random random) {
        int[] res = new int[len];
        for (int i = 1; i <= len; ++i) 
            res[i - 1] = i;
        for (int i = len; i > 1; i--)
            Util.swap(res, i - 1, random.nextInt(i));
        return res;
    }
    
    public static int[] sortedIncreasingArray(final int len) {
        int[] res = new int[len];
        for (int i = 1; i <= len; ++i) 
            res[i - 1] = i;
        return res;
    }
    
    public static int[] sortedDecreasingArray(final int len) {
        int[] res = new int[len];
        for (int i = 1; i <= len; ++i) 
            res[i - 1] = i;
        reverse(res, res.length);
        return res;
    }
    
    private static void reverse(int a[], int n) { 
        int i, t; 
        for (i = 0; i < n / 2; i++) { 
            t = a[i]; 
            a[i] = a[n - i - 1]; 
            a[n - i - 1] = t; 
        }
    } 
}