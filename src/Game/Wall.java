package Game;

public class Wall extends Entity {

	int health;

	Wall(int x, int y) {
		xpos = x;
		ypos = y;
		enemy = false;

		// SET IMAGE VARIABLE HERE

		// Placeholder values
		width = 50;
		height = 10;
		health = 100;
		///////////////////
	}

	void update() {
		if (health <= 0) {
			destroyed = true;
		} else if (health < (health / 100 * 20)) {
			// change image
		} else if (health < (health / 100 * 40)) {
			// change image
		} else if (health < (health / 100 * 60)) {
			// change image
		} else if (health < (health / 100 * 80)) {
			// change image
		}
	}

}
