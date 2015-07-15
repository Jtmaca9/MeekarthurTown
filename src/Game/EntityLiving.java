package Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

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
			currHealth = 0;
			destroyed = true;
		}else if(currHealth >= health){
			//currHealth = health;
		}
	}
	
	void getEffect(int id){		
		Effect incomingEffect = new Effect(id);
		
		if (incomingEffect.effectsSpeed) {
			effectIterator = effectList.iterator();
			while (effectIterator.hasNext()) {
				Effect e = effectIterator.next();
				if (e.effectsSpeed) {
					effectIterator.remove();
					speed = baseSpeed;
				}
			}
			effectList.add(incomingEffect);
		} else {
			effectIterator = effectList.iterator();
			while (effectIterator.hasNext()) {
				Effect e = effectIterator.next();
				if (e.effectID == incomingEffect.effectID && e.currTick > 1) {
					effectIterator.remove();
					effectList.add(incomingEffect);
					break;
				} else if (e.effectID == incomingEffect.effectID) {
					break;
				}
				effectList.add(incomingEffect);

			} 
			if(effectList.size() < 1){
				effectList.add(incomingEffect);
			}
		}
		
		
	}
	
	void updateEffects(){
		effectIterator = effectList.iterator();
		while (effectIterator.hasNext()) {
			//System.out.println("update");
			Effect e = effectIterator.next();
			e.currTickTime += Game.currLevel.deltaTime;
			if ((e.currTick < e.tickCount) && (e.currTickTime >= e.tickDuration)) {
				currHealth += e.healthMod;
				speed += e.speedMod;
	
				if(e.snare){
					speed = 0;
				}
				
				e.currTickTime = 0;
				e.currTick++;
				//System.out.println("tick");
				
			}else if((e.currTick >= e.tickCount) && (e.currTickTime >= e.tickDuration)){
				speed = baseSpeed;
				effectIterator.remove();
			}

		}
	}
}
