package Game;

import org.newdawn.slick.geom.Rectangle;

public class EnemyRangedHoming extends EnemyRanged {
	
	final int MONSTERID = 5;
	int attackCD = 30, currAttackCD;
	

	EnemyRangedHoming(Coords lane, boolean bM) {

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

		ability = new Ability[2];
		hitBox = new Rectangle(xpos, ypos, width, height);
		image = Game.StandardRangedImage;
		
		currHealth = health;
		currAttackCD = attackCD;
		baseSpeed = speed;
		
		targetEntity = new Entity();

		direction = DOWN;
		ability[0] = new AbilityMelee(3);
		range = ability[0].range;
		targetPos = new Coords(0,0);
		ability[1] = new AbilityProjectileHoming(0);
		
		
	}

	void attack(int i) {
		ability[i].useAbility(direction, (int) xpos, (int) ypos, width, this);
		meleeAttack = true;
	}
	
	void attackTarget() {
		ability[1].useAbilityTarget((int) xpos, (int) ypos, width, targetEntity, this);
	}
	
	void behaviour() {
		currAttackCD -= Game.currLevel.deltaTime;
		int alive = 0;
		for(EntityPlayer p : Game.currLevel.players){
			if(!p.destroyed){
				alive++;
			}
		}
		if (currAttackCD < 0 && (alive > 0)) {
			findTargetHighestHP();
			attackTarget();
			currAttackCD = attackCD;
		} 
	}

}
