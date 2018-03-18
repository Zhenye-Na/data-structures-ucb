public class HelloNumbersModified{
	public static void main(String[] args) {
		int x = 1, sum = 0;
		while (x <= 10) {
			System.out.print(sum + " ");
			sum = sum + x;
			x += 1;
		}
		System.out.println(" ");

	}
}