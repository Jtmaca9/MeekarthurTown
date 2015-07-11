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
			break;
		case 1:
			// Wizard pronged attack
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
			break;
		case 3:
			// Blank
			break;
		}
	}
	
	void useAbilityTarget(int cX, int cY, int cS, int tX, int tY) {
		if (checkCooldown()) {
			spawnProjectiles(cX, cY, cS, tX, tY);
		}
	}
	
	void spawnProjectiles(int cX, int cY, int cS, int tX, int tY) {
		if (targetsEnemy) {
			Game.currLevel.playerProjectiles
					.add(new EntityAbilityProjectileVector(
							cX, cY, cS, tX, tY, speed, image, size, healthMod, range));
		} else {
			Game.currLevel.enemyProjectiles
					.add(new EntityAbilityProjectileVector(
							cX, cY, cS, tX, tY, speed, image, size, healthMod, range));
		}
	}
	
}