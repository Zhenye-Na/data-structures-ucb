public class StarGenerator {
	/** This function will be used for generating stars with specific patterns like this. 
	
	*
	**
	***
	****
	*****

	*/
	public static void star(int size) {
		int row = 1;
		while (row <= size) {
			int col = 1;
			while (col < row) {
				System.out.print("*");
				col += 1;
			}
			System.out.println("*");
			row += 1;
		}
	}

	public static void main(String[] args) {
		star(6);
	}

}