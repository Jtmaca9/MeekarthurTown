package Game;

import org.newdawn.slick.geom.Rectangle;

public class StandardMeleeEnemy extends MeleeEnemy {

	final int MONSTERID = 1;

	StandardMeleeEnemy(Coords lane, boolean bM) {

		xpos = lane.x;
		ypos = lane.y;

		destroyed = false;
		bigMonster = bM;

		if (bigMonster) {
			// Test values
			health = 30;
			speed = 1;
			width = 64;
			height = 64;
		} else {
			health = 20;
			speed = 1;
			width = 32;
			height = 32;
		}

		ability = new MeleeAbility[2];
		hitBox = new Rectangle(xpos, ypos, width, height);
		image = Game.enemyImage;

		currHealth = health;

		direction = DOWN;
		ability[0] = new MeleeAbility(3);
	}

	void attack(int i) {
		// change me pls
		ability[i].useAbility(direction, xpos, ypos, width);
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
