package Game;

import org.newdawn.slick.geom.Circle;

public class EntityAbilityMelee extends Entity {

	int healthMod;
	int duration, currDuration;
	int effectID;
	boolean hasEffect;
	float radius;

	EntityAbilityMelee(int x, int y, float r, int hM, boolean hE, int eID) {
		healthMod = hM;
		currDuration = 0;
		duration = 500;
		radius = r;
		
		xpos = x;
		ypos = y;
		hitBox = new Circle(x, y, radius);
		image = Game.meleeIndicator;
		

		hasEffect = hE;
		effectID = eID;

	}

	void update(int delta) {
		currDuration += delta;
		if (currDuration >= duration) {
			destroyed = true;
		}
	}

}
