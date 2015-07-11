package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class EntityAbilityProjectileVector extends EntityProjectile {
	
	int direction, casterXpos, casterYpos, casterSize, size, range, targetXpos, targetYpos, currRange = 0;
	float speed;
	Coords vector;
	
	EntityAbilityProjectileVector(int cX, int cY, int cS, int tX, int tY, int s, Image im, int sz, int hM, int r) {
		speed = s;
		image = im;

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
		Coords tvector = new Coords(0,0);
		float x = (targetXpos- casterXpos);
		float y = (targetYpos - casterYpos);
		float total =((x*x) + (y*y));
		tvector.x = (float) (((x*x) / total)) * speed;
		tvector.y = (float) (((y*y) / total)) * speed;	
		return tvector;
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
