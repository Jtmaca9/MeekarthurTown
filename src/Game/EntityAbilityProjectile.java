package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class EntityAbilityProjectile extends EntityProjectile {

	// N, S, E and W stand for North, South, East and West
	final int N = 0, NE = 1, E = 2, SE = 3, S = 4, SW = 5, W = 6, NW = 7;

	int direction, casterXpos, casterYpos, casterSize, size, range, currRange = 0;
	int ii;
	float speed;
	
	Coords spawn;

	EntityAbilityProjectile(float s, int hM, int sz, int dir, int im, int pX, int pY, int cS, int r, boolean hE, int eID, boolean sA, int AOID, boolean tE ) {
		spawnsAOE = sA;
		targetsEnemy = tE;
		AOEID = AOID;
		speed = s;
		ii = im;
		try{
			if(ii== 0){
				image = new Image("Images/Projectile_Water.png");
			}else if(ii ==1){
				image = new Image("Images/Projectile_Fire.png");
			}else if( ii == 2){
				image = new Image("Images/Projectile_Ice.png");
			}else if( ii == 3){
				image = new Image("Images/Projectile_Arrows.png");
			}else if( ii == 4){
				image = new Image("Images/Projectile_Arrows.png");
			}else if( ii == 5){
				image = new Image("Images/Projectile_PoisonArrow.png");
			}else if( ii == 10){
				image = new Image("Images/RedOrb.png");
			}
		}catch(SlickException e){
			e.printStackTrace();
		}
		
		hasEffect = hE;
		effectID = eID;
		
		size = sz;
		width = size;
		height = size;

		direction = dir;
		healthMod = hM;
		range = r;

		casterXpos = pX;
		casterYpos = pY;
		casterSize = cS;

		spawn = new Coords(0, 0);

		getSpawnCoords();

		xpos = (int) spawn.x;
		ypos = (int) spawn.y;

		hitBox = new Rectangle(xpos, ypos, width, height);
	}

	void getSpawnCoords() {
		// The '1' in this method is an arbitrary number to space the projectile
		// from the caster.
		if (direction == N) {
			spawn.x = (int) (casterXpos + (0.5 * casterSize) - (0.5 * size));
			spawn.y = (int) (casterYpos - 1 - size);
		} else if (direction == NE) {
			spawn.x = (int) (casterXpos + casterSize + size + 1);
			spawn.y = (int) (casterYpos - size - 1);
			image.rotate(45);
		} else if (direction == E) {
			spawn.x = (int) (casterXpos + casterSize + 1);
			spawn.y = (int) (casterYpos + (0.5 * casterSize) - (0.5 * size));
			image.rotate(90);
		} else if (direction == SE) {
			spawn.x = (int) (casterXpos + casterSize + 1);
			spawn.y = (int) (casterYpos + casterSize + 1);
			image.rotate(135);
		} else if (direction == S) {
			spawn.x = (int) (casterXpos + (0.5 * casterSize) - (0.5 * size));
			spawn.y = (int) (casterYpos + casterSize + 1);
			image.rotate(180);
		} else if (direction == SW) {
			spawn.x = (int) (casterXpos - size - 1);
			spawn.y = (int) (casterYpos + casterSize + 1);
			image.rotate(225);
		} else if (direction == W) {
			spawn.x = (int) (casterXpos - size - 1);
			spawn.y = (int) (casterYpos + (0.5 * casterSize) - (0.5 * size));
			image.rotate(270);
		} else if (direction == NW) { // CHANGE
			spawn.x = (int) (casterXpos - size - 1);
			spawn.y = (int) (casterYpos - size - 1);
			image.rotate(315);
		}
	}

	void update() {
		move();
		checkBounds();
		currRange += speed;
	}

	void move() {
		if (direction == 0) {
			ypos -= speed;
		} else if (direction == 1) {
			xpos += (0.7 * speed);
			ypos -= (0.7 * speed);
		} else if (direction == 2) {
			xpos += speed;
		} else if (direction == 3) {
			xpos += (0.7 * speed);
			ypos += (0.7 * speed);
		} else if (direction == 4) {
			ypos += speed;
		} else if (direction == 5) {
			xpos -= (0.7 * speed);
			ypos += (0.7 * speed);
		} else if (direction == 6) {
			xpos -= speed;
		} else if (direction == 7) {
			xpos -= (0.7 * speed);
			ypos -= (0.7 * speed);
		}
		hitBox.setX(xpos);
		hitBox.setY(ypos);
	}

	void checkBounds() {
		if (xpos > 1920 || xpos < -32 || ypos > 1080 || ypos < -32) {
			destroyed = true;
		} else if (currRange >= range) {
			
			
			destroyed = true;
		}
	}

}
