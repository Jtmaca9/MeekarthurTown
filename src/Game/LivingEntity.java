package Game;

public class LivingEntity extends Entity {

	
	final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, NOMOVE = 4;
	int health;
	int speed;
	int direction; // between 0-3 clockwise
	int currSpeed;


	boolean checkCollisionDirection() {
		return false;
	}
	


}
