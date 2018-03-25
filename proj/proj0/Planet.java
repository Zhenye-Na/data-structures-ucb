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


    /** Calculate the distance between two Planets.
        Args: 
            Planet Object p2.
        Return:
            Distance between two planets.
    */
    public double calcDistance(Planet p2) {
        double sqrtdist =  (this.xxPos - p2.xxPos) * (this.xxPos - p2.xxPos) + (this.yyPos - p2.yyPos) * (this.yyPos - p2.yyPos);
        return Math.sqrt(sqrtdist);
    }


    /** Calculate force exerted on this planet by the given planet.
        Args(Planet):
            Planet Object p.
        Returns(double):
            Force exerted on this planet by the given planet.
    */
    public double calcForceExertedBy(Planet p) {
        double G = 6.67E-11;
        return (G * this.mass * p.mass) / (this.calcDistance(p) * this.calcDistance(p));
    }


    /** Describe the force exerted in the X directions
        Args(Planet):
            Planet Object p.
        Returns(double):
            Force exerted in the X direction
    */
    public double calcForceExertedByX(Planet p) {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        return (F * dx) / r;
    }


    /** Describe the force exerted in the Y directions
        Args(Planet):
            Planet Object p.
        Returns(double):
            Force exerted in the Y direction.
    */
    public double calcForceExertedByY(Planet p) {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        return (F * dy) / r;
    }


    /** Calculate the net X force exerted by all planets 
        in that array upon the current Planet.
        Args(Array):
            Array of Planets.
        Returns(double):
            Force net X
    */
    public double calcNetForceExertedByX(Planet[] Ps) {
        int i = 0;
        double Fx = 0;
        while (i < Ps.length) {
            if (!this.equals(Ps[i])) {
                Fx += this.calcForceExertedByX(Ps[i]);
            }
            i = i + 1;
        }
        return Fx;
    }


    /** Calculate the net Y force exerted by all planets 
        in that array upon the current Planet.
        Args(Array):
            Array of Planets.
        Returns(double):
            Force net Y
    */
    public double calcNetForceExertedByY(Planet[] Ps) {
        int i = 0;
        double Fy = 0;
        while (i < Ps.length) {
            if (!this.equals(Ps[i])) {
                Fy += this.calcForceExertedByY(Ps[i]);
            }
            i = i + 1;
        }
        return Fy;
    }



    /** Determine how much the forces exerted on the planet will cause 
        that planet to accelerate, and the resulting change in the 
        planet’s velocity and position in a small period of time dt.
        Args:
            dt(double): small period of time.
            fX(double): X-force.
            fY(double): Y-force.

    */
    public void update(double dt, double fX, double fY) {
        // Calculate the acceleration using the provided x- and y-forces.
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        // Calculate the new velocity by using the acceleration and current velocity.
        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;

        // Calculate the new position by using the velocity computed in step 2 and the current position.
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }













}