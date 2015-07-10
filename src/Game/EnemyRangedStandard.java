package Game;

import org.newdawn.slick.geom.Rectangle;

public class EnemyRangedStandard extends EnemyRanged {
	
	final int MONSTERID = 4;
	int attackCD = 30, currAttackCD;
	

	EnemyRangedStandard(Coords lane, boolean bM) {

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
		ypos = lane.y;

		ability = new Ability[2];
		hitBox = new Rectangle(xpos, ypos, width, height);
		image = Game.StandardRangedImage;
		
		currHealth = health;
		currAttackCD = attackCD;

		direction = DOWN;
		ability[0] = new AbilityMelee(3);
		range = ability[0].range;
		ability[1] = new AbilityProjectileVector(0);
		
		targetPos = new Coords(0,0);
		findTargetHighestHP();
	}

	void attack(int i) {
		ability[i].useAbility(direction, xpos, ypos, width);
	}
	
	void attackTarget() {
		ability[1].useAbilityTarget(xpos, ypos, width, targetPos.x, targetPos.y);
	}
	
	void behaviour() {
		currAttackCD -= Game.currLevel.deltaTime;
		if (currAttackCD < 0) {
			attackTarget();
			currAttackCD = attackCD;
		} 
	}

}
