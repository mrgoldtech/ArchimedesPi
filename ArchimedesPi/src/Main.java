import java.util.ArrayList;
import java.util.Arrays;

import processing.core.*;
public class Main extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Main");
	}
	
	float radius;
	Polygon p;
	Polygon b;
	
	public void settings() {
		size(600,600);
	}
	
	public void setup() {
		radius = 200;
		p = new Polygon(this,800,radius,true);
		b = new Polygon(this,800,radius,false);
		ellipseMode(RADIUS);
	}
	
	public void draw() {
		fill(255);
		background(255);
		pushMatrix();
		translate(width/2,height/2);
		
		stroke(0);
		ellipse(0,0,radius,radius);
		
		stroke(0,255,0);
		p.draw();
		
		stroke(255,0,0);
		b.draw();
		popMatrix();
		
		fill(0);
		textSize(20);
		text("Inscribed Circumference = "+p.getCircumference()+" ("+p.num_sides+" sides)",5,20);
		text("Circumscribed Circumference = "+b.getCircumference()+" ("+b.num_sides+" sides)",5,45);
		text((p.getCircumference()/(2*radius))+" < PI < "+(b.getCircumference()/(2*radius)),5,70);
	
		if(frameCount % 100 == 0) {
//			p = new Polygon(this,(int)(p.num_sides*1.4), radius, true);
//			b = new Polygon(this,(int)(b.num_sides*1.4), radius, false);
		}
		
		
	}

}
