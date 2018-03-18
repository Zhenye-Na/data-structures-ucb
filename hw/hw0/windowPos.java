public class windowPos{
	/* 

	windowPosSum(int[] a, int n) that replaces each element a[i] with 
	the sum of a[i] through a[i + n], but only if a[i] is positive valued. 
	If there are not enough values because we reach the end of the array, 
	we sum only as many values as we have.

	*/
	public static int[] windowPosSum(int[] a, int n) {

		for (int idx = 0; idx < a.length; idx++) {
			// We replace the a[i] iff a[i] is positive
			if (a[idx] > 0) {
				if (idx + n < a.length) {
					for (int i = idx + 1; i <= idx + n; i++) {
						a[idx] += a[i];
					}
				} else {
					for (int i = idx + 1; i < a.length; i++) {
						a[idx] += a[i];
					}
				}
			}
		}
		return a;
	}

 	public static void main(String[] args) {
 		int[] a = {1, 2, -3, 4, 5, 4};
 		int n = 3;

 		// int[] a = {1, -1, -1, 10, 5, -1};
 		// int n = 2;
 		windowPosSum(a, n);

    	// Should print 4, 8, -3, 13, 9, 4
    	System.out.println(java.util.Arrays.toString(a));
    }

}





/*

Hint 1: Use two for loops.

Hint 2: Use continue to skip negative values.

Hint 3: Use break to avoid going over the end of the array.

*/
