package Game;

public class EnemyMelee extends Enemy {

	int range;
	
	void move() {
		currSpeed = speed; // Change this later (test)
		if (direction == DOWN) {
			ypos += currSpeed;
		}

		hitBox.setY(ypos);
		hitBox.setX(xpos);
	}

	void baseBehaviour() {
		for (EntityPlayer e : Game.currLevel.players) {
			if (checkFacingCollision(e)) {
			}
		}

		for (EntityWall w : Game.currLevel.walls) {
			if (checkFacingCollision(w)) {
				attack(0);
			}
		}
	}
}
