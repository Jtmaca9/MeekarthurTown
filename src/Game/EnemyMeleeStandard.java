package Game;

import org.newdawn.slick.geom.Rectangle;

public class EnemyMeleeStandard extends EnemyMelee {

	final int MONSTERID = 1;
	

	EnemyMeleeStandard(Coords lane, boolean bM) {

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

		xpos = (int) (lane.x - (0.5 * width));
		ypos = (int) lane.y;

		ability = new AbilityMelee[2];
		hitBox = new Rectangle(xpos, ypos, width, height);
		image = Game.StandardMeleeImage;
		baseSpeed = speed;

		currHealth = health;

		direction = DOWN;
		ability[0] = new AbilityMelee(3);
		range = ability[0].range;

		rangeBox = new Rectangle(xpos - range, ypos - range, width + (2 * range), height + (2 * range));
	}

	void attack(int i) {
		ability[i].useAbility(direction, xpos, ypos, width);
	}

	void move() {
		currSpeed = speed; // Change this later (test)
		if (direction == DOWN) {
			ypos += currSpeed;
		}

		hitBox.setY(ypos);
		hitBox.setX(xpos);
		rangeBox.setY(ypos - range);
		rangeBox.setX(xpos - range);

	}

}
