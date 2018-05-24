/** Project 0: NBody Simulation
 *
 *  src = "https://sp18.datastructur.es/materials/proj/proj0/proj0"
 *
 *  @author Zhenye Na 03/25/2018
 */

import java.util.List;
import java.util.ArrayList;

public class NBody {
    
    /** Query a double corresponding to the radius of the universe in that file.
        Args:
            dir(String): Directory path to txt file.
        Returns:
            R(double): Radius of the universe in that file
    */
    public static double readRadius(String dir) {
        In in = new In(dir);

        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }


    /** Query an array of Planets corresponding to the planets in the file.
        Args:
            dir(String): Directory path to txt file.
        Returns:
            planet[](Array): array which contains all of the planets.
    */
    public static Planet[] readPlanets(String dir) {
        In in = new In(dir);
        int N = in.readInt();
        double R = in.readDouble();
        int row = 0;

        List<Planet> planets = new ArrayList<Planet>();

        while(!in.isEmpty()) {
            String xxPs = in.readString();
            try{
                double xxP = Double.parseDouble(xxPs);
            } catch (java.lang.NumberFormatException e) {
            	break;
            }
            double xxP = Double.parseDouble(xxPs);
            double yyP = Double.parseDouble(in.readString());
            double xxV = Double.parseDouble(in.readString());
            double yyV = Double.parseDouble(in.readString());
            double mass = Double.parseDouble(in.readString());
            String imgfile = in.readString();
            String planetName = imgfile.split("\\.")[0];

            Planet p = new Planet(xxP, yyP, xxV, yyV, mass, imgfile);
            planets.add(p);
        }

        Planet planet[] = new Planet[planets.size()];
        planets.toArray(planet);

        return planet;
    }


    // public static Planet[] readPlanets(String dir){
    //     In in = new In(dir);
    //     int num = in.readInt();
    //     in.readDouble();
    //     Planet[] planets = new Planet[num];
    //     for (int row = 0; row < num; row++){
    //         planets[row] = new Planet(in.readDouble(),
    //         	                      in.readDouble(),
    //         	                      in.readDouble(),
    //         	                      in.readDouble(),
    //         	                      in.readDouble(),
    //         	                      in.readString());
    //     }
    //     return planets;
    // }


    /** Main function
        Args:
            args[0](double): 0th command line arguments as T. 
            args[1](double): 1st command line arguments as dt.
            args[2](String): txt filename.
    */
    public static void main(String[] args) {
        // Collect All Needed Input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        // Draw the Background
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        // StdDraw.picture(0, 0, "images/starfield.jpg");

        // // Draw all of the Planets
        // for (int i = 0; i < planets.length; i++) {
        //     planets[i].draw();
        // }

        for (int t = 0; t <= T; t += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for (int p = 0; p < planets.length; p++) {
                xForces[p] = planets[p].calcNetForceExertedByX(planets);
                yForces[p] = planets[p].calcNetForceExertedByY(planets);
            }

            for (int p = 0; p < planets.length; p++) {
                planets[p].update(dt, xForces[p], yForces[p]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            // Draw all of the Planets
            for (int i = 0; i < planets.length; i++) {
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            	planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            	planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }

    }

}

