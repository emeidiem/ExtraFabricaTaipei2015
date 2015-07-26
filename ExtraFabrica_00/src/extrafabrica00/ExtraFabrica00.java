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
	int numPart = 10;
	int spacing = 40;
	VerletPhysics physics;
	ArrayList<Particle> particles = new ArrayList<Particle>();

	public void settings() {
		  size(800, 600, P3D);
		}
	
	public void setup() {
		cam = new PeasyCam(this, 100);
		physics = new VerletPhysics();
		physics.addBehavior(new GravityBehavior(new Vec3D(0, 0, 9.8f)));
		createParticles();
		createSprings();
	}

	public void draw() {
		background(0);
		physics.update();
		for (int i = 0; i < particles.size(); i++) {
			Particle p = particles.get(i);
			p.display();
		}
		renderSprings();
		linkheadtoMouse();
	}

	private void createSprings() {
		for (int i = 0; i < particles.size(); i++) {
			if ((i > 0) && (i % numPart != 0)) {
				Particle p1 = particles.get(i);
				Particle p2 = particles.get(i - 1);
				VerletSpring s = new VerletSpring(p1, p2, spacing, 0.5f);
				physics.addSpring(s);
			}
		}

		for (int i = 0; i < numPart - 1; i++) {
			for (int j = 0; j < numPart; j++) {
				// if (j>numPart) {
				Particle p1 = particles.get(j + i * numPart);
				Particle p2 = particles.get(j + (i + 1) * numPart);
				VerletSpring s = new VerletSpring(p1, p2, spacing, 0.5f);
				physics.addSpring(s);
				// }
			}
		}
	}

	private void createParticles() {
		int id = 0;
		for (int i = 0; i < numPart; i++) {
			for (int j = 0; j < numPart; j++) {
				Vec3D tempos = new Vec3D(i * spacing + 100, j * spacing + 100,
						0);
				Particle p = new Particle(this, tempos, id);
				// Particle(ExtraFabrica00 _p5, Vec3D pos, int _id)
				physics.addParticle(p);
				particles.add(p);
				if (id == 0)
					p.lock();
				if (id == numPart - 1)
					p.lock();
				if (id == (numPart - 1) * numPart)
					p.lock();
				// p.lock();

				id++;
			}
		}
	}

	void renderSprings() {
		for (int i = 0; i < physics.springs.size(); i++) {
			VerletSpring sp = physics.springs.get(i);
			stroke(255);
			strokeWeight(1);
			line(sp.a.x, sp.a.y, sp.a.z, sp.b.x, sp.b.y, sp.b.z);
		}
	}

	void linkheadtoMouse() {
		Particle head = particles.get(particles.size() - 1);
		head.lock();
		head.set(mouseX, mouseY, 0);
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
