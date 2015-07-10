package Game;

public class EntityLiving extends Entity {

	final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, NOMOVE = 4;
	float health, currHealth;
	float speed, currSpeed;
	int direction; // between 0-3 clockwise
	Ability ability[];

	boolean checkCollisionDirection() {
		return false;
	}

	void cooldowns() {
		ability[0].currCooldown -= Game.currLevel.deltaTime;

		if (ability[0].currCooldown < 0) {
			ability[0].currCooldown = 0;
		}
		
	}

	void checkHealth() {
		if (currHealth <= 0) {
			destroyed = true;
		}
	}
}
