package Game;

public class Effect {
	int healthMod, speedMod, tickCount, tickDuration, currTick = 0, currTickTime = 0;
	int effectID;
	
	Effect(int eID){
		effectID = eID;
		
		createEffect();
	}
	
	void createEffect(){
		switch(effectID){
		case 0:
			healthMod = -3;
			speedMod = 0;
			tickCount = 5;
			tickDuration = 500;
			break;
		}
	}
	
	
	

}
