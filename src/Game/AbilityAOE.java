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
			enemyHealthMod = -10;
			playerHealthMod = 0;
			range = 10;
			targets = 1;
			image = Game.blueProjectile;
			break;
		}
	}

	void useAbility(int dir, int cX, int cY, int cS) {
		if (checkCooldown()) {
			if (targets == 0) {
				Game.currLevel.enemyAOEList
						.add(new EntityAbilityAOE((int) (cX + (0.5 * cS)), (int) (cY + (0.5 * cS)), abilityID));
			} else if (targets == 1) {
				Game.currLevel.playerAOEList
						.add(new EntityAbilityAOE((int) (cX + (0.5 * cS)), (int) (cY + (0.5 * cS)), abilityID));
			} else if (targets == 2) {
				Game.currLevel.bothAOEList
						.add(new EntityAbilityAOE((int) (cX + (0.5 * cS)), (int) (cY + (0.5 * cS)), abilityID));
			}
		}
	}
}
