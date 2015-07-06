package Game;

import org.newdawn.slick.geom.Rectangle;

public class MeleeAbility extends Ability {

	// If false, melee attack is an AOE from centre of caster
	// If true, is in direction caster facing OR ability direction
	boolean directional;
	
	Rectangle hitBox;

	MeleeAbility(int aID) {
		abilityID = aID;
		createAbility();
	}

	void createAbility() {
		switch (abilityID) {

		case 0:
			// Blank
		case 1:
			// Wizard Basic Melee Attack
			speed = 8;
			cooldown = 30;
			currCooldown = cooldown;
			healthMod = -10;
			range = 10;
			targetsEnemy = true;
			directionMod = 0;
			numOfProjectiles = 1;
			image = Game.projectile;
			break;
		case 2:
			// Blank
		case 3:
			// Monster Basic Melee Attack
			speed = 8;
			cooldown = 50;
			currCooldown = cooldown;
			healthMod = -10;
			range = 10;
			targetsEnemy = false;
			directional = false;
			numOfProjectiles = 1;
			image = Game.projectile;
			break;
		}
	}

	void useAbility(int dir, int pX, int pY, int cS) {
		if (checkCooldown()) {
			if (!directional) {
//				spawnHitBoxCentre(pX, pY, cS);
			}
		}
	}
	
	void spawnHitBoxCentre(int x, int y, int cS) {
		// Spawns the 'Melee attack' hitbox in centre of caster
		if (targetsEnemy) {
			new Rectangle(x - range, y - range, cS + (2 * range), cS + (2 * range));
		}
	}
}
