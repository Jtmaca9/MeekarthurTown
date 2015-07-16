package Game;

import org.newdawn.slick.geom.Circle;

public class EntityAbilityMelee extends Entity {

	int playerHealthMod, enemyHealthMod;
	int duration, currDuration;
	int effectIDPlayer, effectIDEnemy;
	boolean hasEffect;
	float radius;
	Entity owner;

	EntityAbilityMelee(int x, int y, float r, int pHM, int eHM, boolean hE, int eIDp, int eIDe, Entity o) {
		playerHealthMod = pHM;
		enemyHealthMod = eHM;
		currDuration = 0;
		duration = 500;
		radius = r;
		
		owner = o;
		
		xpos = x;
		ypos = y;
		hitBox = new Circle(x, y, radius);
		image = Game.meleeIndicator;
		

		hasEffect = hE;
		effectIDPlayer = eIDp;
		effectIDEnemy = eIDe;

	}

	void update(int delta) {
		currDuration += delta;
		if (currDuration >= duration) {
			destroyed = true;
		}
	}

}
