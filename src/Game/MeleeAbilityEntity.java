package Game;

import org.newdawn.slick.geom.Rectangle;

public class MeleeAbilityEntity extends Entity {
	
	MeleeAbilityEntity(int x, int y, int aWidth, int aHeight) {
		hitBox = new Rectangle(x, y, aWidth, aHeight);
	}
	
	void update(int delta) {
		
	}
	
}
