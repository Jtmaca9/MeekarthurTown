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
			image = Game.blueProjectile;
			break;
		}
	}

	void useAbility(int dir, int cX, int cY, int cS) {
		if (checkCooldown()) {
			if (!targetsEnemy) {
				Game.currLevel.enemyAOEList
						.add(new EntityAbilityAOE((int) (cX + (0.5 * cS)), (int) (cY + (0.5 * cS)), abilityID));
			}
		}
	}
}
