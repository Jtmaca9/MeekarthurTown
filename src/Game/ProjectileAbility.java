package Game;

public class ProjectileAbility extends Ability {

	ProjectileAbility(int aID) {
		abilityID = aID;
		createAbility();
	}
	
	void createAbility() {
		switch (abilityID) {
		
		case 0:
			// Wizard Basic Range Attack
			speed = 8;
			cooldown = 30;
			currCooldown = cooldown;
			healthMod = -10;
			size = 8;
			range = 1000;
			targetsEnemy = true;
			directionMod = 0;
			numOfProjectiles = 1;
			image = Game.projectile;
			break;
		case 1:
			// Wizard Basic Melee Attack
			speed = 8;
			cooldown = 30;
			currCooldown = cooldown;
			healthMod = -10;
			size = 8;
			range = 100;
			targetsEnemy = true;
			directionMod = 0;
			numOfProjectiles = 1;
			image = Game.projectile;
			break;
		case 2:
			// Monster Basic Range Attack
			speed = 8;
			cooldown = 50;
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
			// Monster Basic Melee Attack
			speed = 8;
			cooldown = 50;
			currCooldown = cooldown;
			healthMod = -10;
			size = 8;
			range = 20;
			targetsEnemy = false;
			directionMod = 0;
			numOfProjectiles = 1;
			image = Game.projectile;
			break;
		}
	}
}
