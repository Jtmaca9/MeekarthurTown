package Game;

public class Effect {
	int healthMod, tickCount, tickDuration, currTick = 0, currTickTime;
	int effectID;
	float speedMod;
	boolean snare;
	
	Effect(int eID){
		effectID = eID;
		
		createEffect();
	}
	
	void createEffect(){
		switch(effectID){
		case 0:
			healthMod = -5;
			speedMod = 0;
			tickCount = 6;
			tickDuration = 500;
			currTickTime = tickDuration;
			snare = false;
			break;
		case 1:
			healthMod = 0;
			speedMod = -0.5f;
			tickCount = 1;
			tickDuration = 500;
			currTickTime = tickDuration;
			snare = false;
			break;
		case 2:
			healthMod = 0;
			speedMod = 0;
			tickCount = 1;
			tickDuration = 2000;
			currTickTime = tickDuration;
			snare = true;
			break;
		}
	}
	
	
	

}
