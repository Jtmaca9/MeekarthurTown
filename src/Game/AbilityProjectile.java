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
			healthMod = 0;
			size = 8;
			range = 1000;
			targetsEnemy = true;
			directionMod = 0;
			numOfProjectiles = 1;
			image = Game.projectile;
			hasEffect = true;
			effectID = 1;
			break;
		case 1:
			// Wizard pronged attack
			speed = 8;
			cooldown = 1000;
			currCooldown = cooldown;
			healthMod = -10;
			size = 8;
			range = 1000;
			targetsEnemy = true;
			directionMod = 0;
			numOfProjectiles = 3;
			image = Game.projectile;
			hasEffect = false;
			effectID = 0;
			break;
		case 2:
			// StandardRangedEnemy Basic
			speed = 8;
			cooldown = 300;
			currCooldown = cooldown;
			healthMod = -10;
			size = 8;
			range = 1000;
			targetsEnemy = false;
			directionMod = 0;
			numOfProjectiles = 1;
			image = Game.projectile;
			hasEffect = false;
			effectID = 0;
			break;
		case 3:
			// Blank
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
			if (targetsEnemy) {
				Game.currLevel.playerProjectiles
						.add(new EntityAbilityProjectile(speed, healthMod, size, dir, image, pX, pY, cS, range, hasEffect, effectID));
			} else {
				Game.currLevel.enemyProjectiles
						.add(new EntityAbilityProjectile(speed, healthMod, size, dir, image, pX, pY, cS, range, hasEffect, effectID));
			}
		} else if (numOfProjectiles == 3) {
			if (targetsEnemy) {
				for (int i = -1; i < 2; i++) {
					projDir = getProjectileDirection(direction + i);
					Game.currLevel.playerProjectiles
							.add(new EntityAbilityProjectile(speed, healthMod, size, dir, image, pX, pY, cS, range, hasEffect, effectID));
				}
			} else {
				for (int i = -1; i < 2; i++) {
					projDir = getProjectileDirection(direction + i);
					Game.currLevel.enemyProjectiles
							.add(new EntityAbilityProjectile(speed, healthMod, size, dir, image, pX, pY, cS, range, hasEffect, effectID));
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
