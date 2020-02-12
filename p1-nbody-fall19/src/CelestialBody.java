

/**
 * Celestial Body class for NBody
 * @author ola, ag520
 *
 */
public class CelestialBody 
{

	private double myXPos;
	private double myYPos;
	private double myXVel;
    private double myYVel;
	private double myMass;
	private String myFileName;

	/*The constructor invoked by passing variables data makes the object variables equal to the variables passed.*/
	
	public CelestialBody(double xp, double yp, double xv, double yv, double mass, String filename)
	{
		this.myXPos=xp;
		this.myYPos=yp;
		this.myXVel=xv;
		this.myYVel=yv;
		this.myMass=mass;
		this.myFileName=filename;
		
	}

	/*The copy constructor uses the accessor methods to obtain variable data for the other object, and assigns these values to the object variables.*/
	
	public CelestialBody(CelestialBody b){
		this.myXPos=b.getX();
		this.myYPos=b.getY();
		this.myXVel=b.getXVel();
		this.myYVel=b.getYVel();
		this.myMass=b.getMass();
		this.myFileName=b.getName();
	}

	/*This method returns the X position*/
	
	public double getX() {
		return this.myXPos;
	}
	
	/*This method returns the Y position*/
	
	public double getY() {
		return this.myYPos;
	}
	
	/*This method returns the X velocity*/
	
	public double getXVel() {
		return this.myXVel;
	}
	
	/*This method returns the Y velocity*/
	
	public double getYVel() {
		return this.myYVel;
	}
	
	/*This method returns the mass*/
	
	public double getMass() {
		return this.myMass;
	}
	
	/*This method returns the File Name.*/
	
	public String getName() {
		return this.myFileName;
	}
	
	/*This method calculates the distance to the other celestial body by using the sqrt method. The accessor methods have been used to get the data for the other object.*/
	
	public double calcDistance(CelestialBody b) {
		return (Math.sqrt(((b.getX()-this.getX())*(b.getX()-this.getX()))+((b.getY()-this.getY())*(b.getY()-this.getY()))));
	}

	/*This method uses Newton's law of gravitation to calculate the force.*/
	
	public double calcForceExertedBy(CelestialBody b) {
		return ((6.67*1e-11)*this.getMass()*b.getMass()/(calcDistance(b)*calcDistance(b)));
	}

	/*This method calculates the X component of the force.*/
	
	public double calcForceExertedByX(CelestialBody b) {
		return (calcForceExertedBy(b)*(b.getX()-this.getX())/calcDistance(b));
	}
	
	/*This method calculates the Y component of the force.*/
	
	public double calcForceExertedByY(CelestialBody b) {
		return (calcForceExertedBy(b)*(b.getY()-this.getY())/calcDistance(b));
	}

	/*This method calculates the net force on a body in the X direction.*/
	
	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double ans=0;
		for (CelestialBody b: bodies)
		{
			if (! b.equals(this))
			{
				ans=ans+calcForceExertedByX(b);
			}
		}
		return ans;
	}

	/*This method calculates the net force on a body in the Y direction. */
	
	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double ans=0;
		for (CelestialBody b: bodies)
		{
			if (! b.equals(this))
			{
				ans=ans+calcForceExertedByY(b);
			}
		}
		return ans;
	}

	/*Using equations of motion, this method updates the values of the object variables, after gravitation acts on them for some time.*/
	
	public void update(double deltaT, 
			           double xforce, double yforce) {
		double ax = xforce/(this.myMass);
		double ay = yforce/(this.myMass);
		double nvx = myXVel + deltaT*ax;
		double nvy = myYVel + deltaT*ay;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
		return;
	}

	/*This method puts the image in the link given at the positions given.*/
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}
