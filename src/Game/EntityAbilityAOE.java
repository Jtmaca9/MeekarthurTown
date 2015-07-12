package Game;

import org.newdawn.slick.geom.Rectangle;

public class EntityAbilityAOE extends Entity {
	int healthMod;
	int currTickTime;
	int AOEID;
	int tickTime, tickCount, currTick;
	int range;
	boolean targetsEnemy;

	EntityAbilityAOE(int x, int y, int id) {
		AOEID = id;
		xpos = x;
		ypos = y;
		create();
		hitBox = new Rectangle(xpos, ypos, range, range);

		

	}
	
	void create(){
		switch(AOEID){
		case 0:
			healthMod = -5;
			tickTime = 400;
			tickCount = 10;
			range = 250;
			width = range;
			height = range;
			xpos -= range/2;
			ypos -= range/2;
			image = Game.meleeIndicator;
			targetsEnemy = true;
			break;
		case 1:
			healthMod = -5;
			tickTime = 400;
			tickCount = 10;
			range = 250;
			width = range;
			height = range;
			xpos -= range/2;
			ypos -= range/2;
			image = Game.meleeIndicator;
			targetsEnemy = false;
			break;
		}
	}

	void update(int delta) {
		currTickTime += delta;
		if (currTickTime >= tickTime) {
			currTickTime = 0;
			currTick++;
			if(targetsEnemy){
				Game.currLevel.playerMeleeList
					.add(new EntityAbilityMelee((int)xpos, (int)ypos,(int)range, (int)range, healthMod));
			}else{
				Game.currLevel.enemyMeleeList
					.add(new EntityAbilityMelee((int)xpos, (int)ypos,(int)range, (int)range, healthMod));
			}
		}else if (currTick >= tickCount ){
			destroyed = true;
		}
	}

}
