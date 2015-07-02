package Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Image;

public class Ability {
	
	int abilityID;
	int direction, directionMod;
	int numOfProjectiles;
	Image image;
	
	final int UP = 0, RIGHT = 2, DOWN = 4, LEFT = 6;
	
	// negative healthMod value means damage, positive means heal
	int speed, cooldown, healthMod, size;
	// if targetsEnemy is false, it hits players
	boolean targetsEnemy;
	
	Image abilityIcon, projectileImage;
	
	Ability(int aID) {
		abilityID = aID;
		createAbility();
	}
	
	void createAbility() {
		switch (abilityID) {
		
		case 0:
			// Wizard Basic Attack
			speed = 8;
			cooldown = 1;
			healthMod = -10;
			size = 8;
			targetsEnemy = true;
			directionMod = 0;
			numOfProjectiles = 1;
			image = Game.projectile;
		}
	}
	
	void useAbility(int dir, int pX, int pY, int cS) {
		getCastDirection(dir);
		if (targetsEnemy) {
			Game.currLevel.playerProjectiles.add(new Projectile(speed, healthMod, size, dir, image, pX, pY, cS));
		} else {
			Game.currLevel.enemyProjectiles.add(new Projectile(speed, healthMod, size, dir, image, pX, pY, cS));
		}
	}
	
	void getCastDirection(int dir) {
		// The direction the ability will be cast in (player direction + modifier)
		if (dir == 0) {
			direction = UP;
		} else if (dir == 1) {
			direction = RIGHT;
		} else if (dir == 2) {
			direction = DOWN;
		} else if (dir == 3) {
			direction = LEFT;
		}
		direction += directionMod;
		
	}
}
