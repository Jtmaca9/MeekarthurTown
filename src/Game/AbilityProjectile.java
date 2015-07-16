package Game;

public class AbilityProjectile extends Ability {

	// N, S, E and W stand for North, South, East and West
	final int N = 0, NE = 1, E = 2, SE = 3, S = 4, SW = 5, W = 6, NW = 7;

	int projDir;

	AbilityProjectile(int aID) {
		abilityID = aID;
		createAbility();
	}

	void createAbility() {
		switch (abilityID) {

		case 0:
			// Wizard Basic Range Attack
			speed = 8;
			cooldown = 300;
			currCooldown = cooldown;
			enemyHealthMod = -5;
			playerHealthMod = 5;
			size = 32;
			range = 800;
			targets = 2;
			directionMod = 0;
			numOfProjectiles = 1;
			hasEffect = true;
			effectIDEnemy = -1;
			effectIDPlayer = -1;
			spawnsAOE = false;
			AOEID = 0;
			break;
		case 1:
			// Wizard FireBall
			speed = 8;
			cooldown = 2000;
			currCooldown = cooldown;
			enemyHealthMod = -5;
			playerHealthMod = 0;
			size = 32;
			range = 1000;
			targets = 1;
			directionMod = 0;
			numOfProjectiles = 1;
			abilityIcon = Game.wizardAbility0;
			hasEffect = true;
			effectIDEnemy = 0;
			effectIDPlayer = -1;
			spawnsAOE = false;
			AOEID = 0;
			break;
		case 2:
			// Wizard IceBall
			speed = 8;
			cooldown = 2000;
			currCooldown = cooldown;
			enemyHealthMod = -5;
			playerHealthMod = 0;
			size = 32;
			range = 1000;
			targets = 2;
			directionMod = 0;
			numOfProjectiles = 1;
			abilityIcon = Game.wizardAbility1;
			hasEffect = true;
			effectIDEnemy = 1;
			effectIDPlayer = -1;
			spawnsAOE = false;
			AOEID = 0;
			break;
		case 3:
			// Archer Basic Range Attack
			speed = 10;
			cooldown = 300;
			currCooldown = cooldown;
			enemyHealthMod = -8;
			playerHealthMod = 0;
			size = 32;
			range = 800;
			targets = 1;
			directionMod = 0;
			numOfProjectiles = 1;
			hasEffect = false;
			effectIDEnemy = 0;
			effectIDPlayer = -1;
			spawnsAOE = false;
			AOEID = 0;
			break;
		case 4:
			// Archer Arrow Burst
			speed = 10;
			cooldown = 2000;
			currCooldown = cooldown;
			enemyHealthMod = -5;
			playerHealthMod = 0;
			size = 32;
			range = 500;
			targets = 1;
			directionMod = 0;
			numOfProjectiles = 3;
			abilityIcon = Game.archerAbility0;
			hasEffect = false;
			effectIDEnemy = 0;
			effectIDPlayer = -1;
			spawnsAOE = false;
			AOEID = 0;
			break;
		case 5:
			// Archer Poison
			speed = 10;
			cooldown = 2000;
			currCooldown = cooldown;
			enemyHealthMod = -5;
			playerHealthMod = 0;
			size = 32;
			range = 1000;
			targets = 1;
			directionMod = 0;
			numOfProjectiles = 1;
			abilityIcon = Game.archerAbility1;
			hasEffect = true;
			effectIDEnemy = 0;
			effectIDPlayer = -1;
			spawnsAOE = false;
			AOEID = 0;
			break;
		case 10:
			// Archer Poison
			speed = 10;
			cooldown = 2000;
			currCooldown = cooldown;
			enemyHealthMod = -5;
			playerHealthMod = 0;
			size = 32;
			range = 1000;
			targets = 1;
			directionMod = 0;
			numOfProjectiles = 8;
			abilityIcon = Game.archerAbility1;
			hasEffect = false;
			effectIDEnemy = 0;
			effectIDPlayer = -1;
			spawnsAOE = false;
			AOEID = 0;
			break;
		}
	}

	void useAbility(int dir, int pX, int pY, int cS) {
		if (checkCooldown()) {
			convertFourDirToEightDir(dir);
			spawnProjectiles(direction, pX, pY, cS);
		}
	}

	void spawnProjectiles(int dir, int pX, int pY, int cS) {
		if (numOfProjectiles == 1) {
			if (targets == 1) {
				Game.currLevel.playerProjectiles.add(
						new EntityAbilityProjectile(speed, playerHealthMod, enemyHealthMod, size, dir, abilityID, pX,
								pY, cS, range, hasEffect, effectIDPlayer, effectIDEnemy, spawnsAOE, AOEID, targets));
			} else if (targets == 0) {
				Game.currLevel.enemyProjectiles.add(
						new EntityAbilityProjectile(speed, playerHealthMod, enemyHealthMod, size, dir, abilityID, pX,
								pY, cS, range, hasEffect, effectIDPlayer, effectIDEnemy, spawnsAOE, AOEID, targets));
			} else if (targets == 2) {
				Game.currLevel.bothProjectiles.add(
						new EntityAbilityProjectile(speed, playerHealthMod, enemyHealthMod, size, dir, abilityID, pX,
								pY, cS, range, hasEffect, effectIDPlayer, effectIDEnemy, spawnsAOE, AOEID, targets));
			}
		} else if (numOfProjectiles == 3) {
			if (targets == 1) {
				for (int i = -1; i < 2; i++) {
					projDir = getProjectileDirection(direction + i);
					Game.currLevel.playerProjectiles.add(new EntityAbilityProjectile(speed, playerHealthMod,
							enemyHealthMod, size, projDir, abilityID, pX, pY, cS, range, hasEffect, effectIDPlayer,
							effectIDEnemy, spawnsAOE, AOEID, targets));
				}
			} else if (targets == 0) {
				for (int i = -1; i < 2; i++) {
					projDir = getProjectileDirection(direction + i);
					Game.currLevel.enemyProjectiles.add(new EntityAbilityProjectile(speed, playerHealthMod,
							enemyHealthMod, size, projDir, abilityID, pX, pY, cS, range, hasEffect, effectIDPlayer,
							effectIDEnemy, spawnsAOE, AOEID, targets));
				}
			} else if (targets == 2) {
				for (int i = -1; i < 2; i++) {
					projDir = getProjectileDirection(direction + i);
					Game.currLevel.bothProjectiles.add(new EntityAbilityProjectile(speed, playerHealthMod,
							enemyHealthMod, size, projDir, abilityID, pX, pY, cS, range, hasEffect, effectIDPlayer,
							effectIDEnemy, spawnsAOE, AOEID, targets));
				}
			}
		} else if (numOfProjectiles == 8) {
			if (targets == 1) {
				for (int i = -4; i < 3; i++) {
					projDir = getProjectileDirection(direction + i);
					Game.currLevel.playerProjectiles.add(new EntityAbilityProjectile(speed, playerHealthMod,
							enemyHealthMod, size, projDir, abilityID, pX, pY, cS, range, hasEffect, effectIDPlayer,
							effectIDEnemy, spawnsAOE, AOEID, targets));
				}
			} else if (targets == 0) {
				for (int i = -4; i < 3; i++) {
					projDir = getProjectileDirection(direction + i);
					Game.currLevel.enemyProjectiles.add(new EntityAbilityProjectile(speed, playerHealthMod,
							enemyHealthMod, size, projDir, abilityID, pX, pY, cS, range, hasEffect, effectIDPlayer,
							effectIDEnemy, spawnsAOE, AOEID, targets));
				}
			} else if (targets == 2) {
				for (int i = -4; i < 3; i++) {
					projDir = getProjectileDirection(direction + i);
					Game.currLevel.bothProjectiles.add(new EntityAbilityProjectile(speed, playerHealthMod,
							enemyHealthMod, size, projDir, abilityID, pX, pY, cS, range, hasEffect, effectIDPlayer,
							effectIDEnemy, spawnsAOE, AOEID, targets));
				}
			}
		}
	}

	void convertFourDirToEightDir(int dir) {
		// converts the 4 directional movement system to 8 directional for
		// projectiles.
		if (dir == 0) {
			direction = N;
		} else if (dir == 1) {
			direction = E;
		} else if (dir == 2) {
			direction = S;
		} else if (dir == 3) {
			direction = W;
		}
	}

	int getProjectileDirection(int dir) {
		// ensures dir is within bounds of 0-7
		if (dir == -1) {
			return NW;
		}
		return dir;
	}
}
