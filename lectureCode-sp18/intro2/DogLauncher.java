public class DogLauncher {
	public static void main(String[] args) {
		
		// The Dog class has its own variables
		// Then instantiate a Dog using the `new` keyword
		Dog d = new Dog(15);

		Dog d2 = new Dog(100);

		//Dog bigger = Dog.maxDog(d, d2);
		Dog bigger = d.maxDog(d2);
		bigger.makeNoise();

		//System.out.println(d.binomen);
		//System.out.println(d2.binomen);
		//System.out.println(Dog.binomen);
//		d.makeNoise();
	}
} 