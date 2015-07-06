package Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Image;

public class Ability {

	int abilityID;
	int direction, directionMod;
	int range;
	int numOfProjectiles;
	Image image;

	final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;

	// negative healthMod value means damage, positive means heal
	int speed, cooldown, currCooldown, healthMod, size;
	// if targetsEnemy is false, it hits players
	boolean targetsEnemy;

	Image abilityIcon, projectileImage;

	void useAbility(int dir, int pX, int pY, int cS) {

	}

	// move to AbilityMelee
	void useMB(int dir, int pX, int pY, int cS) {
		if (checkCooldown()) {
			Game.currLevel.enemyProjectiles.add(new Projectile(speed, healthMod, size, 0, image, pX, pY, cS, range));
			Game.currLevel.enemyProjectiles.add(new Projectile(speed, healthMod, size, 2, image, pX, pY, cS, range));
			Game.currLevel.enemyProjectiles.add(new Projectile(speed, healthMod, size, 4, image, pX, pY, cS, range));
			Game.currLevel.enemyProjectiles.add(new Projectile(speed, healthMod, size, 6, image, pX, pY, cS, range));
		}
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
