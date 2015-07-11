package Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityLiving extends Entity {

	final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, NOMOVE = 4;
	float health, currHealth;
	float speed, currSpeed, baseSpeed;
	int direction; // between 0-3 clockwise
	Ability ability[];
	List<Effect> effectList = new ArrayList<Effect>();
	Iterator<Effect> effectIterator;

	boolean checkCollisionDirection() {
		return false;
	}

	void cooldowns() {
		ability[0].currCooldown -= Game.currLevel.deltaTime;

		if (ability[0].currCooldown < 0) {
			ability[0].currCooldown = 0;
		}
		
	}

	void checkHealth() {
		if (currHealth <= 0) {
			destroyed = true;
		}
	}
	
	void getEffect(int id){		
		effectList.add(new Effect(id));
	}
	
	void updateEffects(){
		effectIterator = effectList.iterator();
		while (effectIterator.hasNext()) {
			System.out.println("update");
			Effect e = effectIterator.next();
			e.currTickTime += Game.currLevel.deltaTime;
			if ((e.currTick < e.tickCount) && (e.currTickTime >= e.tickDuration)) {
				currHealth += e.healthMod;
				speed += e.speedMod;
				e.currTickTime = 0;
				e.currTick++;
				System.out.println("tick");
				
			}else if(e.currTick > e.tickCount){
				speed = baseSpeed;
				effectIterator.remove();
			}

		}
	}
}
