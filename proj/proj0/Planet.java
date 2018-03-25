public class Planet {
	
	public double xxPos;       // Its current x position
	public double yyPos;       // Its current y position
	public double xxVel;       // Its current velocity in the x direction
    public double yyVel;       // Its current velocity in the y direction
    public double mass;        // Its mass
    public String imgFileName; // The name of the file that corresponds to the image that depicts the planet 

    // Initialize a Planet object
	public Planet(double xP, double yP, double xV, 
		          double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/** Initialize an identical Planet object as Planet p
		Args: 
			Planet Object p.
		Returns: 
			None.

	*/
	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}


	/** This function calculates the distance between two Planets.
		Args: 
			Planet Object p2.
		Return:
			Distance between two planets.

	*/
	public double calcDistance(Planet p2) {
		double sqrtdist =  (this.xxPos - p2.xxPos) * (this.xxPos - p2.xxPos) + (this.yyPos - p2.yyPos) * (this.yyPos - p2.yyPos);
		return Math.sqrt(sqrtdist);

	}



}