package Game;

import org.newdawn.slick.geom.Rectangle;

public class EnemyMeleeTarget extends EnemyMelee {

	final int MONSTERID = 3;
	private int targetXpos;
	private int targetYpos;
	private Coords v;
	

	EnemyMeleeTarget(Coords lane, boolean bM) {

		destroyed = false;
		bigMonster = bM;

		if (bigMonster) {
			// Test values
			health = 30;
			speed = 1;
			width = 96;
			height = 96;
		} else {
			health = 20;
			speed = 1;
			width = 48;
			height = 48;
		}

		xpos = (int) (lane.x - (0.5 * width));
		ypos = (int) lane.y;

		ability = new AbilityMelee[2];
		hitBox = new Rectangle(xpos, ypos, width, height);
		image = Game.StandardMeleeImage;
		baseSpeed = speed;
		v = new Coords(0,0);
		
		targetEntity = new Entity();

		currHealth = health;

		direction = DOWN;
		ability[0] = new AbilityMelee(3);
		range = ability[0].range;
		findTargetHighestHP();
		rangeBox = new Rectangle(xpos - range, ypos - range, width + (2 * range), height + (2 * range));
	}

	void attack(int i) {
		meleeAttack = true;
		ability[i].useAbility(direction, (int) xpos, (int) ypos, width, this);
		
	}

	void move() {
		currSpeed = speed; // Change this later (test)
		v = getVector();
		
		xpos += v.x;
		ypos += v.y;

		hitBox.setY(ypos);
		hitBox.setX(xpos);
		rangeBox.setY(ypos - range);
		rangeBox.setX(xpos - range);

	}
	
	Coords getVector() {
		targetXpos = (int) targetEntity.xpos;
		targetYpos = (int) targetEntity.ypos;
		Coords vector = new Coords(0,0);
		float x = (targetXpos - xpos);
		float y = (targetYpos - ypos);
		double trueDistance = Math.sqrt((x*x) + (y*y));
		
		vector.x = (float) ((x / trueDistance) * speed);
		vector.y = (float) ((y / trueDistance) * speed);
		

		return vector;
	}

}
