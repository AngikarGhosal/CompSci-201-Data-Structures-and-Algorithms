	

/**
 * @author Angikar Ghosal, 0962413
 * 
 * Simulation program for the NBody assignment
 */

import java.io.*;
import java.util.*;

public class NBody {
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	
	/*This method reads the radius of the universe by taking in consecutive values from the data file specified.*/
	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));
	
		int nump = s.nextInt();
		double radi = s.nextDouble();
		s.close();
		return radi;	
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	 
	 /*This method takes in the variable data by using a scanner, and assigns it to a new Object. This object gets pushed to a new array of bodies.*/
	public static CelestialBody[] readBodies(String fname) throws FileNotFoundException {
		
			Scanner s = new Scanner(new File(fname));
			int nb = s.nextInt();
			double rad = s.nextDouble();
			ArrayList <CelestialBody> a = new ArrayList<>();
			for(int k=0; k < nb; k++) 
			{
				double xpos = s.nextDouble();
				double ypos = s.nextDouble();
				double xvel = s.nextDouble();
				double yvel = s.nextDouble();
				double mas = s.nextDouble();
				String fil = s.next();
				CelestialBody m = new CelestialBody(xpos, ypos, xvel, yvel, mas, fil);
				a.add(m);
			}
			CelestialBody[] ans = a.toArray(new CelestialBody[nb]);			
			s.close();
			return ans;
	}
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = 39447000.0;
		double dt = 25000.0;
		
		String fname= "./data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		CelestialBody[] bodies = readBodies(fname);
		double radius = readRadius(fname);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
		//StdAudio.play("images/2001.wav");
	
		// run simulation until time up

		for(double t = 0.0; t < totalTime; t += dt) {
			
			/*This code stores the forces in the x and y directions for each of the objects in an array.*/
			
			double[] xforces = new double[bodies.length];
			double[] yforces = new double[bodies.length];
			
			
			/*This code computes the values of forces in x-direction and y-direction and stores them for all the planets.*/
			
			for (int i=0; i<bodies.length; i++)
			{
				xforces[i]=bodies[i].calcNetForceExertedByX(bodies);
				yforces[i]=bodies[i].calcNetForceExertedByY(bodies);
			}
			
			/*This code updates the values of the object variables for each planet for the given forces computed earlier.*/
			
			for (int i=0; i<bodies.length; i++)
			{
				bodies[i].update(dt, xforces[i], yforces[i]);
			StdDraw.picture(0,0,"images/starfield.jpg");
			}
			
			/*This code draws all the planets in their current positions and moving.*/
			for (CelestialBody b: bodies)
			{
				b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		
		// This code prints final values after simulation.
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}
