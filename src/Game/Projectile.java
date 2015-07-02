package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Projectile extends Entity {
	
	int speed, direction, healthMod, casterXpos, casterYpos, casterSize, size, range, currRange = 0;
	Coords spawn;

	Projectile(int s, int hM, int sz, int dir, Image im, int pX, int pY, int cS, int r) {
		speed = s;
		image = im;
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
		xpos = spawn.x;
		ypos = spawn.y;
		hitBox = new Rectangle(xpos, ypos, width, height);
	}
	
	void getSpawnCoords() {
		if (direction == 0) {
			spawn.x = (int) (casterXpos + (0.5 * casterSize) - (0.5 * size));
			spawn.y = (int) (casterYpos - 1 - size);
		} else if (direction == 1) {
			spawn.x = (int) (casterXpos + casterSize + (0.5 * size) +1);
			spawn.y = (int) (casterYpos + (0.5 * casterSize) - (0.5 * size));
		} else if (direction == 2) {
			spawn.x = (int) (casterXpos + (0.5 * casterSize) - (0.5 * size));
			spawn.y = (int) (casterYpos + casterSize + 1 + (0.5 * size));
		} else if (direction == 3) {
			spawn.x = (int) (casterXpos - size - 1);
			spawn.y = (int) (casterYpos + (0.5 * casterSize) - (0.5 * size));
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
			xpos += speed;
		} else if (direction == 2) {
			ypos += speed;
		} else if (direction == 3) {
			xpos -= speed;
		}
		hitBox.setX(xpos);
		hitBox.setY(ypos);
	}
	
	void checkBounds(){
		if(xpos > 1920 || xpos < -32 || ypos > 1080 || ypos < -32){
			destroyed = true;
		}else if (currRange >= range){
			destroyed = true;
		}
	}
	
}
