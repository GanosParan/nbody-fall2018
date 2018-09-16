public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	/**
	 * 
	 * @param xp sets x position
	 * @param yp sets y position
	 * @param xv sets x velocity
	 * @param yv sets y velocity
	 * @param mass sets body mass
	 * @param filename sets file name
	 */
	public Body(double xp, double yp, double xv, 
			double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	/**
	 * 
	 * @param b copies Body data into b
	 */
	public Body(Body b) {
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName = b.myFileName;
	}
	/**
	 * 
	 * @returns myXPos
	 */
	public double getX() {
		return myXPos;
	}
	/**
	 * 
	 * @returns myYPos
	 */
	public double getY() {
		return myYPos;
	}
	/**
	 * 
	 * @returns X velocity
	 */
	public double getXVel() {
		return myXVel;
	}
	/**
	 * 
	 * @returns y velocity
	 */
	public double getYVel() {
		return myYVel;
	}
	/**
	 * 
	 * @returns body mass
	 */
	public double getMass() {
		return myMass;
	}
	/**
	 * 
	 * @returns file name
	 */
	public String getName() {
		return myFileName;
	}
	/**
	 * 
	 * @param b uses copied data
	 * @returns calculated distance
	 */
	public double calcDistance(Body b) {
		double dx = myXPos - b.myXPos;
		double dy = myYPos - b.myYPos;
		double dist = Math.sqrt(dx*dx + dy*dy);
		return dist;
	}
	/**
	 * 
	 * @param b uses body data
	 * @return calculated gravitational force
	 */
	public double calcForceExertedBy(Body b) {
		double G = 6.67 * 1e-11;
		double force = G * myMass * b.myMass / Math.pow(calcDistance(b), 2);
		return force;
	}
	
	/**
	 * 
	 * @param b uses body data
	 * @returns force in x direction 
	 */
	public double calcForceExertedByX(Body b) {
		double Fx =  calcForceExertedBy(b)*(b.myXPos - myXPos) / calcDistance(b);
		return Fx;
	}
	/**
	 * 
	 * @param b uses body data
	 * @returns force in y direction 
	 */

	public double calcForceExertedByY(Body b) {
		double Fy =  calcForceExertedBy(b)*(b.myYPos - myYPos) / calcDistance(b);
		return Fy;
	}
	/**
	 * 
	 * @param b uses body data
	 * @returns total force in x direction 
	 */

	public double calcNetForceExertedByX(Body[] bodies) {
		double FxNet = 0.0;
		for(Body b:bodies) {
			if (! b.equals(this)) {
				FxNet +=  calcForceExertedBy(b)*(b.myXPos - myXPos) / calcDistance(b);
			}
		}
		return FxNet;
	}
	/**
	 * 
	 * @param b uses body data
	 * @returns total force in y direction 
	 */

	public double calcNetForceExertedByY(Body[] bodies) {
		double FyNet = 0.0;
		for(Body b:bodies) {
			if (! b.equals(this)) {
				FyNet += calcForceExertedBy(b)*(b.myYPos - myYPos) / calcDistance(b);
			}
		}			
		return FyNet;
	}
	/**
	 * 
	 * @param deltaT change in time per unit
	 * @param xforce input x force
	 * @param yforce input y force
	 */
	public void update(double deltaT, double xforce,
			double yforce ) {
		double ax = xforce/myMass;
		double ay = yforce/myMass;
		double nvx = myXVel + deltaT*ax;
		double nvy = myYVel + deltaT*ay;
		double nx = myXPos + nvx * deltaT;
		double ny = myYPos + nvy * deltaT;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}
	/**
	 * Runs draw function
	 */
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}
