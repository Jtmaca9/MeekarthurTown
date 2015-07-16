package Game;

import org.newdawn.slick.Image;

public class Ability {

	int abilityID;
	int direction, directionMod;
	int range;
	int numOfProjectiles;
	boolean hasEffect, spawnsAOE;
	int effectIDPlayer, effectIDEnemy, AOEID;
	Image image;
	float speed;

	final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;

	// negative healthMod value means damage, positive means heal
	int cooldown, currCooldown, playerHealthMod, enemyHealthMod, size;
	// if targetsEnemy is false, it hits players
	int targets;

	Image abilityIcon;

	void useAbility(int dir, int pX, int pY, int cS, Entity o) {

	}
	
	void useAbilityTarget(int cX, int cY, int cS, Entity t, Entity o) {
		
	}

	boolean checkCooldown() {
		if (currCooldown == 0) {
			currCooldown = cooldown;
			return true;
		} else {
			return false;
		}

	}
}
