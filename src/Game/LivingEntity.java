package Game;

public class LivingEntity extends Entity {

	int health;
	int speed;
	int direction; // between 0-3 clockwise
	boolean enemy;

	boolean checkCollisionDirection() {
		return false;
	}

}
