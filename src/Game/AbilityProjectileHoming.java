package Game;

public class AbilityProjectileHoming extends Ability {

	AbilityProjectileHoming(int aID) {
		abilityID = aID;
		createAbility();
	}

	void createAbility() {
		switch (abilityID) {

		case 0:
			// StandardRangedEnemy Basic
			speed = 3;
			cooldown = 1500;
			currCooldown = cooldown;
			playerHealthMod = -10;
			enemyHealthMod = 0;
			size = 24;
			range = 800;
			targets = 0;
			directionMod = 0;
			numOfProjectiles = 1;
			spawnsAOE = false;
			AOEID = 1;
			hasEffect = false;
			break;
		case 1:
			// EnemyRangedHomingSupport Heal
			speed = 5;
			cooldown = 1500;
			currCooldown = cooldown;
			playerHealthMod = 0;
			enemyHealthMod = 5;
			size = 24;
			range = 8000;
			targets = 2;
			directionMod = 0;
			numOfProjectiles = 1;
			spawnsAOE = false;
			AOEID = 1;
			hasEffect = false;
			effectIDPlayer = -1;
			effectIDEnemy = -1;
			break;
		case 2:
			// StandardRangedEnemy Basic
			speed = 2;
			cooldown = 300;
			currCooldown = cooldown;
			playerHealthMod = -10;
			enemyHealthMod = 0;
			size = 8;
			range = 1000;
			targets = 0;
			directionMod = 0;
			numOfProjectiles = 1;
			spawnsAOE = false;
			AOEID = 1;
			hasEffect = false;
			break;
		case 3:
			// Blank
			break;
		}
	}

	void useAbilityTarget(int cX, int cY, int cS, Entity t, Entity o) {
		if (checkCooldown()) {
			spawnProjectiles(cX, cY, cS, t, o);
		}
	}

	void spawnProjectiles(int cX, int cY, int cS, Entity t, Entity o) {
		if (targets == 1) {
			Game.currLevel.playerProjectiles
					.add(new EntityAbilityProjectileHoming(cX, cY, cS, t, (int) speed, size, playerHealthMod,
							enemyHealthMod, effectIDPlayer, effectIDEnemy, range, spawnsAOE, hasEffect, AOEID, o));
		} else if (targets == 0) {
			Game.currLevel.enemyProjectiles
					.add(new EntityAbilityProjectileHoming(cX, cY, cS, t, (int) speed, size, playerHealthMod,
							enemyHealthMod, effectIDPlayer, effectIDEnemy, range, spawnsAOE, hasEffect, AOEID, o));
		} else if (targets == 2) {
			Game.currLevel.bothProjectiles
					.add(new EntityAbilityProjectileHoming(cX, cY, cS, t, (int) speed, size, playerHealthMod,
							enemyHealthMod, effectIDPlayer, effectIDEnemy, range, spawnsAOE, hasEffect, AOEID, o));
		}
	}

}
