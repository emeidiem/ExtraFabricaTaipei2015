package extrafabrica00;

import toxi.geom.ReadonlyVec3D;
import toxi.geom.Vec3D;
import toxi.physics.VerletParticle;

public class Particle extends VerletParticle {

	ExtraFabrica00 p5;
	float rad = 10;
	int id;	

	Particle(ExtraFabrica00 _p5, Vec3D pos, int _id) {//CONSTRUCTOR
		super(pos);
		p5=_p5;
		id = _id;
		// TODO Auto-generated constructor stub
	}

	void display() {
		p5.fill(175);
		p5.stroke(255);
		p5.strokeWeight(10);
		p5.point(x, y, z);
		// text (id,x+10,y+10);

	}

}
