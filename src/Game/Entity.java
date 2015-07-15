package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Entity {

	Image image;
	float xpos, ypos;
	int width, height;
	Shape hitBox;
	boolean destroyed;
	boolean enemy, meleeAttack = false;
	Rectangle rangeBox;

	Entity() {

	}

	void render(GameContainer container, Graphics g) {
		image.draw((int) xpos, (int) ypos, width, height);
		
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
		// used to check if player intersects with melee rangeBox
		if (hitBox.intersects(i.rangeBox)) {
			return true;
		} else {
			return false;
		}
	}

}
