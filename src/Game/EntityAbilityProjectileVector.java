package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class EntityAbilityProjectileVector extends EntityProjectile {
	
	int direction, casterXpos, casterYpos, casterSize, size, range, targetXpos, targetYpos, currRange = 0;
	float speed;
	Coords vector;
	
	EntityAbilityProjectileVector(int cX, int cY, int cS, int tX, int tY, int s, Image im, int sz, int hM, int r, boolean sA, int AOID) {
		speed = s;
		image = im;
		spawnsAOE = sA;
		AOEID = AOID;
		size = sz;
		width = size;
		height = size;

		healthMod = hM;
		range = r;

		casterXpos = cX;
		casterYpos = cY;
		casterSize = cS;
		
		xpos = (int) (cX + (0.5 * cS));
		ypos = (int) (cY + (0.5 * cS));
		
		targetXpos = tX;
		targetYpos = tY;

		vector = getVector();
		

		hitBox = new Rectangle(xpos, ypos, width, height);
	}
	
	Coords getVector() {
		Coords vector = new Coords(0,0);
		float x = (targetXpos - casterXpos);
		float y = (targetYpos - casterYpos);
		double trueDistance = Math.sqrt((x*x) + (y*y));
		
		vector.x = (float) ((x / trueDistance) * speed);
		vector.y = (float) ((y / trueDistance) * speed);

		return vector;
	}
	
	void update() {
		move();
		checkBounds();
		currRange += speed;
	}
	
	void checkBounds() {
		if (xpos > 1920 || xpos < -32 || ypos > 1080 || ypos < -32) {
			destroyed = true;
		} else if (currRange >= range) {
			destroyed = true;
		}
	}
	
	void move(){
		xpos += (vector.x);
		ypos += (vector.y);
		hitBox.setX(xpos);
		hitBox.setY(ypos);
	}
}
