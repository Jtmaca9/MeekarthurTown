package Game;

public class AbilityProjectileVector extends Ability {
	
	AbilityProjectileVector(int aID) {
		abilityID = aID;
		createAbility();
	}
	
	void createAbility() {
		switch (abilityID) {

		case 0:
			// StandardRangedEnemy Basic
			speed = 7;
			cooldown = 1500;
			currCooldown = cooldown;
			playerHealthMod = -10;
			enemyHealthMod = 0;
			size = 24;
			range = 2000;
			targets = 1;
			directionMod = 0;
			numOfProjectiles = 1;
			image = Game.projectileFire;
			hasEffect = false;
			spawnsAOE = false;
			AOEID = 1;
			break;
		case 1:
			// Wizard pronged attack
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
			targets = 1;
			directionMod = 0;
			numOfProjectiles = 1;
			image = Game.projectileFire;
			hasEffect = false;
			spawnsAOE = false;
			AOEID = 1;
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
					.add(new EntityAbilityProjectileVector(
							cX, cY, cS, t, (int) speed, size, playerHealthMod, enemyHealthMod, effectIDPlayer, effectIDEnemy, range, spawnsAOE, hasEffect, AOEID, o));
		} else if (targets == 0) {
			Game.currLevel.enemyProjectiles
					.add(new EntityAbilityProjectileVector(
							cX, cY, cS, t, (int) speed, size, playerHealthMod, enemyHealthMod, effectIDPlayer, effectIDEnemy, range, spawnsAOE, hasEffect, AOEID, o));
		} else if (targets == 2) {
			Game.currLevel.bothProjectiles
			.add(new EntityAbilityProjectileVector(
					cX, cY, cS, t, (int) speed, size, playerHealthMod, enemyHealthMod, effectIDPlayer, effectIDEnemy, range, spawnsAOE, hasEffect, AOEID, o));
		}
	}
	
}
