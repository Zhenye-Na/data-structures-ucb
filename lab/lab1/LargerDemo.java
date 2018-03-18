public class LargerDemo {
	/** Return the larger number of x and y. */
	public static int larger(int x, int y) {
		if (x > y) {
			return x;
		}
		return y;
	}

	public static void main(String[] args) {
        System.out.println(larger(3, 31));
        // String x = larger(-5, 10);  This will turn error cuz return value type is not a String
	}
}

/*
    1. All functions in Java should be declared in class.
       All functions are called 'methods'.
    2. Define function -> use "public static".
    3. All parameters of a function must have a declared type, and
       the return value of the function should have a declared type.
    4. Funtions in Java only return only one value!
*/