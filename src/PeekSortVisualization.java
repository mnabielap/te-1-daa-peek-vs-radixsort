public class PeekSortVisualization {
    public static void peeksort(final int[] a, final int l, final int r) {
        int n = r - l + 1;
        System.out.print("PeekSort(A["+(l)+"..."+(r)+"], "+(l)+", "+(r)+")");
        peeksort(a, l, r, l, r, new int[n], 1);
    }
    
    static final String SPACE = "|   ";
    public static void peeksort(int[] A, int left, int right, int leftRunEnd, int rightRunStart, final int[] B, int ident) {
        int next_ident = ident + 1;
        if (leftRunEnd == right || rightRunStart == left) {
            System.out.print("\n"+SPACE.repeat(ident)+"1.e == r or s == l ");
            System.out.println("["+leftRunEnd+" == "+right+" || "+rightRunStart+" == "+left+"] -> return");
            return;
        }
        int mid = left + ((right - left) >> 1);
        System.out.print(" <<l,mid,r,e,s : "+left+","+mid+","+right+","+leftRunEnd+","+rightRunStart+">>");
        if (mid <= leftRunEnd) {
            System.out.print("\n"+SPACE.repeat(ident)+"Masuk line 3 karena m <= e ");
            System.out.println("["+(mid)+" <= "+(leftRunEnd)+"]");
            System.out.print(SPACE.repeat(ident)+"4.PeekSort(A["+(leftRunEnd+1)+"..."+(right)+"], "+(leftRunEnd+1)+", "+(rightRunStart)+")");
            peeksort(A, leftRunEnd+1, right, leftRunEnd+1,rightRunStart, B, next_ident);
            System.out.print(SPACE.repeat(ident)+"5.Merge(A["+(left)+".."+(leftRunEnd)+"], A["+(leftRunEnd+1)+".."+(right)+"])");
            MergesAndRuns.mergeRuns(A, left, leftRunEnd+1, right, B);
            
        } else if (mid >= rightRunStart) {
            System.out.println("\n"+SPACE.repeat(ident)+"Masuk line 6 karena m >= s ["+mid+" >= "+rightRunStart+"]");
            System.out.print(SPACE.repeat(ident)+"7.PeekSort(A["+(left)+"..."+(rightRunStart-1)+"], "+(leftRunEnd)+", "+(rightRunStart-1)+")");
            peeksort(A, left, rightRunStart-1, leftRunEnd, rightRunStart-1, B, next_ident);
            System.out.print(SPACE.repeat(ident)+"8.Merge(A["+(left)+".."+(rightRunStart-1)+"], A["+(rightRunStart)+".."+(right)+"])");
            MergesAndRuns.mergeRuns(A, left, rightRunStart, right, B);
            
        } else {
            final int i, j;
            if (A[mid] <= A[mid+1]) {
                i = MergesAndRuns.extendWeaklyIncreasingRunLeft(A, mid, left == leftRunEnd ? left : leftRunEnd+1);
                j = (mid+1 == rightRunStart) ? mid : MergesAndRuns.extendWeaklyIncreasingRunRight(A, mid+1, right == rightRunStart ? right : rightRunStart-1);
            } else {
                i = MergesAndRuns.extendStrictlyDecreasingRunLeft(A, mid, left == leftRunEnd ? left : leftRunEnd+1);
                j = (mid+1 == rightRunStart) ? mid : MergesAndRuns.extendStrictlyDecreasingRunRight(A, mid+1,right == rightRunStart ? right : rightRunStart-1);
                MergesAndRuns.reverseRange(A, i, j);
            }
            System.out.println(" -> after extend <<i,j:"+i+","+j+">>");
            if (i == left && j == right) {
                System.out.print(SPACE.repeat(ident)+"11.i == l and j == r ");
                System.out.println(" -> ["+i+" == "+left+" && "+j+" == "+right+"] -> return");
                return;
            }
            if (mid - i < j - mid) {
                System.out.println(SPACE.repeat(ident)+"Masuk line 12 karena m - i < j - m ["+(mid-i)+" <= "+(j-mid)+"]");
                System.out.print(SPACE.repeat(ident)+"13.PeekSort(A["+(left)+"..."+(i-1)+"], "+(leftRunEnd)+", "+(i-1)+")");
                peeksort(A, left, i-1, leftRunEnd, i-1, B, next_ident);
                System.out.print(SPACE.repeat(ident)+"14.PeekSort(A["+(i)+"..."+(right)+"], "+(j)+", "+(rightRunStart)+")");
                peeksort(A, i, right, j, rightRunStart, B, next_ident);
                System.out.print(SPACE.repeat(ident)+"15.Merge(A["+(left)+".."+(i-1)+"], A["+(i)+".."+(right)+"])");
                MergesAndRuns.mergeRuns(A,left, i, right, B);
            } else {
                System.out.println(SPACE.repeat(ident)+"Masuk line 16 karena m - i >= j - m ["+(mid-i)+" >= "+(j-mid)+"]");
                System.out.print(SPACE.repeat(ident)+"17.PeekSort(A["+(left)+"..."+(j)+"], "+(leftRunEnd)+", "+(i)+")");
                peeksort(A, left, j, leftRunEnd, i, B, next_ident);
                System.out.print(SPACE.repeat(ident)+"18.PeekSort(A["+(j+1)+"..."+(right)+"], "+(j+1)+", "+(rightRunStart)+")");
                peeksort(A, j+1, right, j+1, rightRunStart, B, next_ident);
                System.out.print(SPACE.repeat(ident)+"19.Merge(A["+(left)+".."+(j)+"], A["+(j+1)+".."+(right)+"])");
                MergesAndRuns.mergeRuns(A,left, j+1, right, B);
            }
        }
    }
}