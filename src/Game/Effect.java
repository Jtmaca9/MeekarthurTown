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
			// DOT
			healthMod = -5;
			speedMod = 0;
			tickCount = 2;
			tickDuration = 1000;
			currTickTime = tickDuration;
			effectsSpeed = false;
			snare = false;
			break;
		case 1:
			// Slow
			healthMod = 0;
			speedMod = -0.5f;
			tickCount = 1;
			tickDuration = 1000;
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
		}
	}
	
	
	

}
