package Game;

import org.newdawn.slick.geom.Rectangle;

public class EnemyBossStandard extends EnemyBoss {
	
	final int MONSTERID = 4;
	int attackCD = 10, currAttackCD;
	

	EnemyBossStandard(Coords lane, boolean bM) {

		destroyed = false;
		bigMonster = bM;

		if (bigMonster) {
			// Test values
			health = 200;
			speed = 1;
			width = 256;
			height = 256;
		} else {
			health = 100;
			speed = 1;
			width = 128;
			height = 128;
		}

		xpos = (int) (lane.x - (0.5 * width));
		ypos = (int) lane.y;
		
		targetEntity = new Entity();

		ability = new Ability[2];
		hitBox = new Rectangle(xpos, ypos, width, height);
		image = Game.boss2;
		
		currHealth = health;
		currAttackCD = attackCD;
		baseSpeed = speed;

		direction = DOWN;
		ability[0] = new AbilityMelee(3);
		range = ability[0].range;
		targetPos = new Coords(0,0);
		ability[1] = new AbilityProjectileVector(0);
		
		
	}

	void attack(int i) {
		ability[i].useAbility(direction, (int) xpos, (int) ypos, width, this);
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
