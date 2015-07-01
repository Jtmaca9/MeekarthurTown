package Game;

import org.newdawn.slick.geom.Rectangle;

public class MeleeEnemy extends Enemy {

	final int DOWN = 2;

	MeleeEnemy(Coords lane, int mID, boolean bM) {
		xpos = 320;// test values
		ypos = 320;// test values
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
			speed = 0;
			width = 32;
			height = 32;
			direction = DOWN;
		}
	}

	void update() {
		move();
		checkBounds();
	}

	void move() {

		if (direction == DOWN) {
			ypos += speed;
		}

		hitBox.setY(ypos);
		hitBox.setX(xpos);

	}
}
