package Game;

import org.newdawn.slick.Image;

public class Ability {

	int abilityID;
	int direction, directionMod;
	int range;
	int numOfProjectiles;
	boolean hasEffect;
	int effectID;
	Image image;

	final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;

	// negative healthMod value means damage, positive means heal
	int speed, cooldown, currCooldown, healthMod, size;
	// if targetsEnemy is false, it hits players
	boolean targetsEnemy;

	Image abilityIcon, projectileImage;

	void useAbility(int dir, int pX, int pY, int cS) {

	}
	
	void useAbilityTarget(int cX, int cY, int cS, int x, int y) {
		
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
