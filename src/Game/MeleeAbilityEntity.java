package Game;

import org.newdawn.slick.geom.Rectangle;

public class MeleeAbilityEntity extends Entity {
	
	int healthMod;
	int duration, currDuration;
	
	MeleeAbilityEntity(int x, int y, int aWidth, int aHeight, int hM) {
		healthMod = hM;
		currDuration = 0;
		duration = 1000;
		hitBox = new Rectangle(x, y, aWidth, aHeight);
		
	}
	
	void update(int delta) {
		currDuration++;
		if (currDuration >= duration) {
			destroyed = true;
		}
	}
	
}
