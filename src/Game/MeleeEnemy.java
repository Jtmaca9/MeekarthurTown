package Game;

public class MeleeEnemy extends Enemy {

	void move() {
		currSpeed = speed; // Change this later (test)
		if (direction == DOWN) {
			ypos += currSpeed;
		}

		hitBox.setY(ypos);
		hitBox.setX(xpos);
	}

	void baseBehaviour() {
		for (Player e : Game.currLevel.players) {
			if (checkFacingCollision(e)) {
			}
		}

		for (Wall w : Game.currLevel.walls) {
			if (checkFacingCollision(w)) {
				attack(0);
			}
		}
	}
}
