package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class EntityAbilityAOE extends Entity {
	int playerHealthMod, enemyHealthMod;
	int currTickTime;
	int AOEID;
	int tickTime, tickCount, currTick;
	int radius;
	int targets;
	int effectIDPlayer, effectIDEnemy;
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
			playerHealthMod = 0;
			enemyHealthMod = -1;
			tickTime = 400;
			currTickTime = tickTime;
			tickCount = 10;
			radius = 50;
			width = radius*2;
			height = radius*2;
			hitBox = new Circle(xpos + (radius/2), ypos + (radius/2), radius);
			image = Game.fire;
			targets = 1;
			hasEffect = false;
			effectIDPlayer = -1;
			effectIDEnemy = -1;
			break;
		case 1:
			playerHealthMod = -15;
			enemyHealthMod = 0;
			tickTime = 400;
			currTickTime = tickTime;
			tickCount = 10;
			radius = 150;
			width = radius*2;
			height = radius*2;
			hitBox = new Circle(xpos + (radius/2), ypos + (radius/2), radius);
			image = Game.fire;
			targets = 0;
			hasEffect = false;
			effectIDPlayer = -1;
			effectIDEnemy = -1;
			break;
		}
	}

	void update(int delta) {
		currTickTime += delta;
		if (currTickTime >= tickTime) {
			currTickTime = 0;
			currTick++;
			if (targets == 1) {
				Game.currLevel.playerMeleeList
					.add(new EntityAbilityMelee((int)xpos, (int)ypos, (float)radius, playerHealthMod, enemyHealthMod, hasEffect, effectIDPlayer, effectIDEnemy));
			}else if (targets == 0) {
				Game.currLevel.enemyMeleeList
					.add(new EntityAbilityMelee((int)xpos, (int)ypos,(float)radius, playerHealthMod, enemyHealthMod, hasEffect, effectIDPlayer, effectIDEnemy));
			}
		}else if (currTick >= tickCount ){
			destroyed = true;
		}
	}
	
	void render(GameContainer container, Graphics g) {
		image.draw((int) xpos - (radius/2), (int) ypos  - (radius/2), width, height);
		
	}

}
