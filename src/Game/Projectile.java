package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Projectile extends Entity {

	// N, S, E and W stand for North, South, East and West
	final int N = 0, NE = 1, E = 2, SE = 3, S = 4, SW = 5, W = 6, NW = 7;

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
		// The '1' in this method is an arbitrary number to space the projectile
		// from the caster.
		if (direction == N) {
			spawn.x = (int) (casterXpos + (0.5 * casterSize) - (0.5 * size));
			spawn.y = (int) (casterYpos - 1 - size);
		} else if (direction == NE) {
			spawn.x = (int) (casterXpos + casterSize + size + 1);
			spawn.y = (int) (casterYpos - size - 1);
		} else if (direction == E) {
			spawn.x = (int) (casterXpos + casterSize + 1);
			spawn.y = (int) (casterYpos + (0.5 * casterSize) - (0.5 * size));
		} else if (direction == SE) {
			spawn.x = (int) (casterXpos + casterSize + 1);
			spawn.y = (int) (casterYpos + casterSize + 1);
		} else if (direction == S) {
			spawn.x = (int) (casterXpos + (0.5 * casterSize) - (0.5 * size));
			spawn.y = (int) (casterYpos + casterSize + 1);
		} else if (direction == SW) {
			spawn.x = (int) (casterXpos - size - 1);
			spawn.y = (int) (casterYpos + casterSize + 1);
		} else if (direction == W) {
			spawn.x = (int) (casterXpos - size - 1);
			spawn.y = (int) (casterYpos + (0.5 * casterSize) - (0.5 * size));
		} else if (direction == NW) { // CHANGE
			spawn.x = (int) (casterXpos - size - 1);
			spawn.y = (int) (casterYpos - size - 1);
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
			ypos -= speed;
		} else if (direction == 2) {
			xpos += speed;
		} else if (direction == 3) {
			xpos += speed;
			ypos += speed;
		} else if (direction == 4) {
			ypos += speed;
		} else if (direction == 5) {
			xpos -= speed;
			ypos += speed;
		} else if (direction == 6) {
			xpos -= speed;
		} else if (direction == 7) {
			xpos -= speed;
			ypos -= speed;
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
