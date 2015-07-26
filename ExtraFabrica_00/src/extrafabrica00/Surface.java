package extrafabrica00;

import java.util.ArrayList;

import toxi.geom.Vec3D;
import toxi.physics.VerletSpring;

public class Surface {
	
	ExtraFabrica00 p5;
	int numPart = 10;
	int spacing = 40;
	ArrayList<Particle> particles = new ArrayList<Particle>();

	
	Surface(ExtraFabrica00 _p5){
		p5 = _p5;
		createParticles();
		createSprings();
	}
	
	void run(){
		renderSprings();
		//linkheadtoMouse();
	}
	
	private void createSprings() {
		for (int i = 0; i < particles.size(); i++) {
			if ((i > 0) && (i % numPart != 0)) {
				Particle p1 = particles.get(i);
				Particle p2 = particles.get(i - 1);
				VerletSpring s = new VerletSpring(p1, p2, spacing, 0.5f);
				p5.physics.addSpring(s);
			}
		}

		for (int i = 0; i < numPart - 1; i++) {
			for (int j = 0; j < numPart; j++) {
				// if (j>numPart) {
				Particle p1 = particles.get(j + i * numPart);
				Particle p2 = particles.get(j + (i + 1) * numPart);
				VerletSpring s = new VerletSpring(p1, p2, spacing, 0.5f);
				p5.physics.addSpring(s);
				// }
			}
		}
	}

	private void createParticles() {
		int id = 0;
		for (int i = 0; i < numPart; i++) {
			for (int j = 0; j < numPart; j++) {
				Vec3D tempos = new Vec3D(i * spacing - numPart * spacing/2+spacing/2, j * spacing - numPart * spacing/2+spacing/2,
						0);
				Particle p = new Particle(p5, tempos, id);
				// Particle(ExtraFabrica00 _p5, Vec3D pos, int _id)
				p5.physics.addParticle(p);
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
	
	void renderParticles(){
		for (int i = 0; i < particles.size(); i++) {
			Particle p = particles.get(i);
			p.display();
		}
	}

	void renderSprings() {
		for (int i = 0; i < p5.physics.springs.size(); i++) {
			VerletSpring sp = p5.physics.springs.get(i);
			p5.stroke(255);
			p5.strokeWeight(1);
			p5.line(sp.a.x, sp.a.y, sp.a.z, sp.b.x, sp.b.y, sp.b.z);
		}
	}

	void linkheadtoMouse() {
		Particle head = particles.get(particles.size() - 1);
		head.lock();
		head.set(p5.mouseX, p5.mouseY, 0);
	}

}
