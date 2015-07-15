package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class EntityAbilityAOE extends Entity {
	int healthMod;
	int currTickTime;
	int AOEID;
	int tickTime, tickCount, currTick;
	int radius;
	boolean targetsEnemy;
	int effectID;
	boolean hasEffect;

	EntityAbilityAOE(int x, int y, int id) {
		AOEID = id;
		xpos = x;
		ypos = y;
		create();
		
	}
	
	void create(){
		switch(AOEID){
		case 0:
			healthMod = -1;
			tickTime = 400;
			currTickTime = tickTime;
			tickCount = 10;
			radius = 50;
			width = radius*2;
			height = radius*2;
			hitBox = new Circle(xpos + (radius/2), ypos + (radius/2), radius);
			image = Game.fire;
			targetsEnemy = true;
			hasEffect = false;
			effectID = 0;
			break;
		case 1:
			healthMod = -15;
			tickTime = 400;
			currTickTime = tickTime;
			tickCount = 10;
			radius = 150;
			width = radius*2;
			height = radius*2;
			hitBox = new Circle(xpos + (radius/2), ypos + (radius/2), radius);
			image = Game.fire;
			targetsEnemy = false;
			hasEffect = false;
			effectID = 0;
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
					.add(new EntityAbilityMelee((int)xpos, (int)ypos, (float)radius, healthMod, hasEffect, effectID));
			}else{
				Game.currLevel.enemyMeleeList
					.add(new EntityAbilityMelee((int)xpos, (int)ypos,(float)radius, healthMod, hasEffect, effectID));
			}
		}else if (currTick >= tickCount ){
			destroyed = true;
		}
	}
	
	void render(GameContainer container, Graphics g) {
		image.draw((int) xpos - (radius/2), (int) ypos  - (radius/2), width, height);
		
	}

}
