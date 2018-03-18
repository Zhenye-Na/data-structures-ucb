public class MaxOfArray {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int temp = m[0];
        for (int idx = 1; idx < m.length; idx++) {
        	if (m[idx] > temp) {
        		temp = m[idx];
        	}
        }
        return temp;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 42, 15, 2, 22, 10, 6};
       System.out.println("The largest number of the array is: " + max(numbers));
    }
}
