package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class EntityWall extends EntityLiving {

	int health;
	Image halfImage;

	EntityWall(int x, int y) {
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

		for (EntityProjectile p : Game.currLevel.enemyProjectiles) {
			if (checkCollision(p)) {
				p.destroyed = true;
			}
		}

		for (EntityAbilityMelee m : Game.currLevel.enemyMeleeList) {
			if (checkCollision(m)) {
				currHealth += m.playerHealthMod;
				m.destroyed = true;
			}
		}

		for (EntityProjectile p : Game.currLevel.playerProjectiles) {
			if (checkCollision(p)) {
				p.destroyed = true;
			}
		}
		
		for (EntityProjectile p : Game.currLevel.bothProjectiles) {
			if (checkCollision(p)) {
				p.destroyed = true;
			}
		}

		checkHealth();
	}

}
