package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class MeleeEnemy extends Enemy {

	MeleeEnemy(Coords lane, int mID, boolean bM) {
		xpos = lane.x;
		ypos = lane.y;
		monsterID = mID;
		bigMonster = bM;
		destroyed = false;
		createMonster();
		hitBox = new Rectangle(xpos, ypos, width, height);
	}

	void createMonster() {

		switch (monsterID) {

		case 0:
			image = Game.enemyImage;
			health = 10;
			speed = 1;
			width = 64;
			height = 128;
			direction = DOWN;
		}
	}

	void update() {
		move();
		collision();
		checkBounds();
	}

	void move() {
		currSpeed = speed; // Change this later (test)
		if (direction == DOWN) {
			ypos += currSpeed;
		}

		hitBox.setY(ypos);
		hitBox.setX(xpos);

	}
}
