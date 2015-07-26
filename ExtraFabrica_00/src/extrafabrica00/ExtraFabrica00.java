package extrafabrica00;

import java.util.ArrayList;

import peasy.PeasyCam;
import processing.core.PApplet;
import toxi.geom.Vec3D;
import toxi.physics.VerletPhysics;
import toxi.physics.VerletSpring;
import toxi.physics.behaviors.GravityBehavior;

public class ExtraFabrica00 extends PApplet {

	PeasyCam cam;
	VerletPhysics physics;
	Surface surface;

	public void settings() {
		  size(800, 600, P3D);
		}
	
	public void setup() {
		cam = new PeasyCam(this, 500);
		physics = new VerletPhysics();
		physics.addBehavior(new GravityBehavior(new Vec3D(0, 0, 9.8f)));
surface = new Surface(this);
	}

	public void draw() {
		background(0);
		physics.update();
		surface.run();
	}


	public void keyPressed() {
		// if (key == 'l') {
		// if (!p1.isLocked())p1.lock(); // !p1.isLocked() = p1.isLocked==false
		// else p1.unlock(); //if (p1.isLocked())
		// println("'l' is pressed");
		// }
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { extrafabrica00.ExtraFabrica00.class
				.getName() });
	}
}
