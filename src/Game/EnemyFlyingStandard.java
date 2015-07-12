package Game;

import org.newdawn.slick.geom.Rectangle;

public class EnemyFlyingStandard extends EnemyFlying{
	
	final int MONSTERID = 7;
	int attackCD = 30, currAttackCD;
	

	EnemyFlyingStandard(Coords lane, boolean bM) {

		destroyed = false;
		bigMonster = bM;

		if (bigMonster) {
			// Test values
			health = 30;
			speed = 1.5f;
			width = 64;
			height = 64;
		} else {
			health = 20;
			speed = 1.5f;
			width = 32;
			height = 32;
		}

		xpos = (int) (lane.x - (0.5 * width));
		ypos = (int) lane.y;

		ability = new Ability[2];
		hitBox = new Rectangle(xpos, ypos, width, height);
		image = Game.StandardFlyingImage;
		
		currHealth = health;
		currAttackCD = attackCD;
		baseSpeed = speed;

		direction = DOWN;


		
		
	}

	void attack(int i) {
		
	}
	
	void cooldowns(){
		
	}
	
	void attackTarget() {
	}
	
	void behaviour() {
		
	}

}
