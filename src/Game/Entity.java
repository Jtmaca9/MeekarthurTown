package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Entity {

	Image image;
	int xpos, ypos;
	int width, height;
	Rectangle hitBox;
	boolean destroyed;
	boolean enemy;

	Entity() {

	}

	void render(GameContainer container, Graphics g) {
		image.draw(xpos, ypos);
	}

	void update() {

	}

	Boolean checkCollision(Entity i) {
		if (hitBox.intersects(i.hitBox)) {
			return true;
		} else {
			return false;
		}
	}

}
