package Game;

import org.newdawn.slick.geom.Rectangle;

public class AbilityMelee extends Ability {

	// If false, melee attack is an AOE from centre of caster
	// If true, is in direction caster facing OR ability direction
	boolean directional;

	Rectangle hitBox;

	AbilityMelee(int aID) {
		abilityID = aID;
		createAbility();
	}

	void createAbility() {
		switch (abilityID) {

		case 0:
			// Blank
			break;
		case 1:
			// Wizard Basic Melee Attack
			speed = 8;
			cooldown = 50;
			currCooldown = cooldown;
			enemyHealthMod = -6;
			range = 75;
			targets = 1;
			directionMod = 0;
			numOfProjectiles = 1;
			image = Game.blueProjectile;
			hasEffect = false;
			effectIDEnemy = 0;
			break;
		case 2:
			// Blank
			break;
		case 3:
			// Monster Basic Melee Attack
			speed = 8;
			cooldown = 500;
			currCooldown = cooldown;
			playerHealthMod = -10;
			range = 48;
			targets = 0;
			directional = false;
			numOfProjectiles = 1;
			image = Game.redProjectile;
			hasEffect = true;
			effectIDPlayer = 1;
			break;
		}
	}

	void useAbility(int dir, int pX, int pY, int cS) {
		if (checkCooldown()) {
			if (!directional) {
				spawnHitBoxCentre(pX, pY, cS);
			}

		}
	}

	void spawnHitBoxCentre(int x, int y, int cS) {
		// Spawns the 'Melee attack' hitbox in centre of caster
		if (targets == 0) {
			Game.currLevel.enemyMeleeList.add(
					new EntityAbilityMelee((int) ((0.5 * cS) + x), (int) ((0.5 * cS) + y), (float) ((0.5 * cS) + range),
							playerHealthMod, enemyHealthMod, hasEffect, effectIDPlayer, effectIDEnemy));
		} else if (targets == 1) {
			Game.currLevel.playerMeleeList.add(
					new EntityAbilityMelee((int) ((0.5 * cS) + x), (int) ((0.5 * cS) + y), (float) ((0.5 * cS) + range),
							playerHealthMod, enemyHealthMod, hasEffect, effectIDPlayer, effectIDEnemy));
		} else if (targets == 2) {
			Game.currLevel.bothMeleeList.add(
					new EntityAbilityMelee((int) ((0.5 * cS) + x), (int) ((0.5 * cS) + y), (float) ((0.5 * cS) + range),
							playerHealthMod, enemyHealthMod, hasEffect, effectIDPlayer, effectIDEnemy));
		}
	}
}
