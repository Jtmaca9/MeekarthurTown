package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class EntityAbilityProjectileVector extends EntityProjectile {
	
	int direction, casterXpos, casterYpos, casterSize, size, range, targetXpos, targetYpos, currRange = 0;
	float speed;
	Coords vector;
	Vector2f v;
	Entity targetEntity;
	
	EntityAbilityProjectileVector(int cX, int cY, int cS, Entity t, int s, int sz, int pHM, int eHM, int eIDp, int eIDe, int r, boolean sA, boolean hE, int AOID, Entity o) {
		speed = s;
		try {
			image = new Image("Images/Projectile_Fire.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spawnsAOE = sA;
		AOEID = AOID;
		size = sz;
		width = size;
		height = size;
		
		hasEffect = hE;
		effectIDPlayer = eIDp;
		effectIDEnemy = eIDe;

		playerHealthMod = pHM;
		enemyHealthMod = eHM;
		range = r;

		casterXpos = cX;
		casterYpos = cY;
		casterSize = cS;
		
		xpos = (int) (cX);
		ypos = (int) (cY);
		
		targetEntity = t;
		targetXpos = (int) targetEntity.xpos;
		targetYpos = (int) targetEntity.ypos;
		v = new Vector2f(0, 0);
		vector = getVector();
		
		owner = o;
		

		hitBox = new Rectangle(xpos, ypos, width, height);
	}
	
	Coords getVector() {
		Coords vector = new Coords(0,0);
		float x = (targetXpos - xpos);
		float y = (targetYpos - ypos);
		double trueDistance = Math.sqrt((x*x) + (y*y));
		
		vector.x = (float) ((x / trueDistance) * speed);
		vector.y = (float) ((y / trueDistance) * speed);
		
		v.x = vector.x;
		v.y = vector.y;
		
		image.setCenterOfRotation(xpos + (width/2), ypos + (height/2));
		image.rotate((float) v.getTheta() + 90);

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
