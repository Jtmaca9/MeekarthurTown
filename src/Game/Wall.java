package Game;

import org.newdawn.slick.geom.Rectangle;

public class Wall extends LivingEntity {

	int health;

	Wall(int x, int y) {
		xpos = x;
		ypos = y;
		enemy = false;
		image = Game.wallImage;

		// SET IMAGE VARIABLE HERE

		// Placeholder values
		width = 384;
		height = 64;
		health = 1;
		currHealth = health;
		hitBox = new Rectangle(xpos, ypos, width, height);

		direction = DOWN;
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

		for (Projectile p : Game.currLevel.enemyProjectiles) {
			if (checkCollision(p)) {
				currHealth += p.healthMod;
				p.destroyed = true;
			}
		}
		
		for (MeleeAbilityEntity m : Game.currLevel.enemyMeleeList) {
			if (checkCollision(m)) {
				currHealth += m.healthMod;
				m.destroyed = true;
			}
		}

		for (Projectile p : Game.currLevel.playerProjectiles) {
			if (checkCollision(p)) {
				p.destroyed = true;
			}
		}

		checkHealth();
	}

}
