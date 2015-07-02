package Game;

public class LivingEntity extends Entity {

	int health;
	int speed;
	int direction; // between 0-3 clockwise
	int currSpeed;
	final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, NOMOVE = 4;

	boolean checkCollisionDirection() {
		return false;
	}
	


}
