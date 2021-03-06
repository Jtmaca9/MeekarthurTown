package Game;

import org.newdawn.slick.geom.Rectangle;

public class EntityItem extends Entity{
	
	int itemID;
	int healthMod, effectID, duration, currDuration = 0;
	boolean hasEffect;
	
	EntityItem(int id, int x, int y){
		itemID = id;
		xpos = x;
		ypos = y;
		create();
	}
	
	void create(){
		switch(itemID){
		case 0: //health pack
			image = Game.healthImage;
			width = 26;
			height = 26;
			xpos -= width/2;
			ypos -= height/2;
			healthMod = 50;
			hasEffect = false;
			effectID = 0;
			duration = 10000;
			hitBox = new Rectangle(xpos, ypos, width, height);
			break;
		case 1: //lil blood splat
			image = Game.blood;
			width = 48;
			height = 48;
			xpos -= width/2;
			ypos -= height/2;
			healthMod = 0;
			hasEffect = false;
			effectID = 0;
			duration = 20000;
			hitBox = new Rectangle(xpos, ypos, width, height);
			break;
		case 2: //big blood splat
			image = Game.blood;
			width = 96;
			height = 96;
			xpos -= width/2;
			ypos -= height/2;
			healthMod = 0;
			hasEffect = false;
			effectID = 0;
			duration = 20000;
			hitBox = new Rectangle(xpos, ypos, width, height);
			break;
		}
	}
	
	void update(int delta){
		currDuration += delta;
		if(currDuration >= duration){
			destroyed = true;
		}
	}
}
