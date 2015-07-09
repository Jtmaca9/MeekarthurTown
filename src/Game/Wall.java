package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Wall extends LivingEntity {

	int health;
	Image halfImage;

	Wall(int x, int y) {
		xpos = x;
		ypos = y;
		enemy = false;
		image = Game.wallFullImage;
		

		// SET IMAGE VARIABLE HERE

		// Placeholder values
		width = 384;
		height = 64;
		health = 100;
		currHealth = health;
		hitBox = new Rectangle(xpos, ypos, width, height);

		direction = DOWN;
		///////////////////
	}

	void update() {
		if (currHealth < ((health / 100) * 50)) {
			image = Game.wallHalfImage;
		}
		if (currHealth <= 0) {
			destroyed = true;
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
