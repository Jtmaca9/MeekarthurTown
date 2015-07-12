package Game;

import org.newdawn.slick.geom.Rectangle;

public class AbilityAOE extends Ability {

	Rectangle hitBox;
	int duration;
	int tickTime;
	int currTime;

	AbilityAOE(int aID) {
		abilityID = aID;
		createAbility();
	}

	void createAbility() {
		switch (abilityID) {
		case 0:
			// Wizard Basic Melee Attack
			cooldown = 0;
			currCooldown = cooldown;
			healthMod = -10;
			range = 10;
			targetsEnemy = true;
			image = Game.projectile;
			hasEffect = false;
			break;
		}
	}

	void useAbility(int dir, int pX, int pY, int cS) {
		if (checkCooldown()) {

		}
	}

	void spawnHitBoxCentre(int x, int y, int cS) {
		// Spawns the 'Melee attack' hitbox in centre of caster
		if (!targetsEnemy) {
			Game.currLevel.enemyMeleeList
					.add(new EntityAbilityMelee(x - range, y - range, cS + (2 * range), cS + (2 * range), healthMod));
		}
	}
}
