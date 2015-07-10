package Game;

import org.newdawn.slick.geom.Rectangle;

public class EntityAbilityMelee extends Entity {

	int healthMod;
	int duration, currDuration;

	EntityAbilityMelee(int x, int y, int aWidth, int aHeight, int hM) {
		healthMod = hM;
		currDuration = 0;
		duration = 500;
		hitBox = new Rectangle(x, y, aWidth, aHeight);
		image = Game.meleeIndicator;
		width = aWidth;
		height = aHeight;
		xpos = x;
		ypos = y;

	}

	void update(int delta) {
		currDuration += delta;
		if (currDuration >= duration) {
			destroyed = true;
		}
	}

}
