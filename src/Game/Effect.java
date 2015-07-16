package Game;

public class Effect {
	int healthMod, tickCount, tickDuration, currTick = 0, currTickTime;
	int effectID;
	float speedMod;
	boolean snare, effectsSpeed;
	
	Effect(int eID){
		effectID = eID;
		
		createEffect();
	}
	
	void createEffect(){
		switch(effectID){
		case 0:
			// DOT Fire
			healthMod = -3;
			speedMod = 0;
			tickCount = 8;
			tickDuration = 750;
			currTickTime = tickDuration;
			effectsSpeed = false;
			snare = false;
			break;
		case 1:
			// Slow Ice
			healthMod = 0;
			speedMod = -0.5f;
			tickCount = 1;
			tickDuration = 3000;
			currTickTime = tickDuration;
			snare = false;
			effectsSpeed = true;
			break;
		case 2:
			// Snare
			healthMod = 0;
			speedMod = 0;
			tickCount = 1;
			tickDuration = 2000;
			currTickTime = tickDuration;
			snare = true;
			effectsSpeed = true;
			break;
		case 3:	
			// Dot test
			healthMod = -1;
			speedMod = 0;
			tickCount = 2;
			tickDuration = 750;
			currTickTime = tickDuration;
			effectsSpeed = false;
			snare = false;
			break;
		case 4:
			//Heal
			healthMod = 100;
			speedMod = 0;
			tickCount = 2;
			tickDuration = 750;
			currTickTime = tickDuration;
			effectsSpeed = false;
			snare = false;
			break;
		default:
			healthMod = 0;
			speedMod = 0;
			tickCount = 0;
			tickDuration = 0;
			currTickTime = tickDuration;
			snare = false;
			effectsSpeed = false;
			break;
		}
	}
}
