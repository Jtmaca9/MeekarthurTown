package Game;

public class LivingEntity extends Entity {

	final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, NOMOVE = 4;
	float health, currHealth;
	int speed;
	int direction; // between 0-3 clockwise
	int currSpeed;
	Ability ability[];

	boolean checkCollisionDirection() {
		return false;
	}

	void cooldowns() {
		ability[0].currCooldown--;

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
