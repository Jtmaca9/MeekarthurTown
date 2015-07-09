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
	Rectangle rangeBox;

	Entity() {

	}

	void render(GameContainer container, Graphics g) {
		image.draw(xpos, ypos, width, height);
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
	
	Boolean checkRangeBox(Entity i) {
		//used to check if player intersects with melee rangeBox
		if  (hitBox.intersects(i.rangeBox)) {
			return true;
		} else {
			return false;
		}
	}

}
