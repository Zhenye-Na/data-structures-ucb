public class Dog {
	public int weightInPounds;
	public static String binomen = "Canis familiaris";

	/** One integer constructor for dogs. */
	public Dog(int w) {
		weightInPounds = w;
	}

	public void makeNoise() {
		if (weightInPounds < 10) {
			System.out.println("yip!");
		} else if (weightInPounds < 30) {
			System.out.println("bark.");
		} else {
			System.out.println("woooof!");
		}
	}

	// public static Dog maxDog(Dog d1, Dog d2) {
	// 	if (d1.weightInPounds > d2.weightInPounds) {
	// 		return d1;
	// 	}
	// 	return d2;
	// }	

    // keyword `this` to refer to the current object
    // Dog bigger = d.maxDog(d2);  `this` refers to `d`
	public Dog maxDog(Dog d2) {
		if (this.weightInPounds > d2.weightInPounds) {
			return this;
		}
		return d2;
	}

    // public static Dog maxDog(Dog d1, Dog d2) {
    // if (weightInPounds > d2.weightInPounds) {
    //     return this;
    // }
    // return d2;
    // }
} 