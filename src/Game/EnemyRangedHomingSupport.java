package Game;

import org.newdawn.slick.geom.Rectangle;

public class EnemyRangedHomingSupport extends EnemyRanged {
	
	final int MONSTERID = 11;
	int attackCD = 30, currAttackCD;
	Enemy t;
	

	EnemyRangedHomingSupport(Coords lane, boolean bM) {

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
		ability[1] = new AbilityProjectileHoming(1);
		
		
	}

	void attack(int i) {
		ability[i].useAbility(direction, (int) xpos, (int) ypos, width);
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
			findTargetLowestHP();
			attackTarget();
			currAttackCD = attackCD;
		} 
	}
	
	void findTargetLowestHP() {
		// sets target to index of player with highest hp percentage
		float lowest = 1000;
		for (Enemy e : Game.currLevel.enemyList) {
			if (e.currHealth <= lowest && !(e == this)){
				targetEntity = e;
				lowest = e.currHealth;
			}
		}
			
	}

}
