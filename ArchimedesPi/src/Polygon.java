import processing.core.*;
public class Polygon {

	PApplet p;
	int num_sides; //how many sides a polygon has
	float rad; //the radius of the polygon
	
	PVector[] points; //a list of the polygon's vertices
	
	public Polygon(PApplet parent, int s, float r, boolean isInscribed) {	
		//set object vars
		p = parent;
		num_sides = s;
		points = new PVector[num_sides+1];
		rad = r;	
		//calculate points with the given radius
		calcPoints();		
		
		//change the radius if it's a circumscribed polgyon (not inscribed)
		if(!isInscribed) {		
			//find the difference between the apothem vector and the radius vector
			float apothem_distance = p.dist(rad*p.cos(p.TWO_PI/num_sides/2), -rad*p.sin(p.TWO_PI/num_sides/2), 
									(points[0].x+points[points.length-2].x)/2, (points[0].y+points[points.length-2].y)/2);
			/* correct the apothem-to-radius distance for each possible polygon
			via a function that uses an infinite series of sqrt(2)
			this was fun to derive... not really */
			float apothem_distance_corrected = apothem_distance*p.pow(2,(float)1/(num_sides-2));
			rad += apothem_distance_corrected;
			//recalculate points due to new radius
			calcPoints();
		}
	}
	
	//construct a list of vertices based on the data
	public void calcPoints() {	
		for(float index = 0, theta = 0; index < points.length; index++, theta+=p.TWO_PI/num_sides) {
			//theta is the angle for each vertex of the polgyon, such that 0 <= theta < 2pi
			//index helps us store (x,y) coordinates into the list of points
			float x = rad*p.cos(theta);
			float y = rad*p.sin(theta);
			points[(int)index] = new PVector(x,y);
		}
		
	}
	
	//draws the polygon at (0,0)
	public void draw() {
		for(int x=0; x<points.length-1; x++) {
			p.strokeWeight(3);
			p.line(points[x].x,points[x].y,points[x+1].x,points[x+1].y);
		}
	}
	
	//get the circumference of the polgyon by adding all side lengths
	public float getCircumference() {
		float circumference = 0;
		for(int x=0; x<points.length-1; x++) {
			circumference += points[x].dist(points[x+1]);
		}
		return circumference;
	}
	
	public void changeNumSides(int n_sides) {
		num_sides = n_sides;
		calcPoints();
	}
	
}
