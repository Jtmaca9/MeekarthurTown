package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class MeleeEnemy extends Enemy {

	MeleeEnemy(int x, int y, int mID, boolean bM) {
		xpos = x;
		ypos = y;
		//add lanes here for spawn data
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
			health = 20;
			speed = 1;
			width = 32;
			height = 32;
			direction = DOWN;
		}
	}

	void update() {
		move();
		collision();
		checkBounds();
		checkHealth();
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
