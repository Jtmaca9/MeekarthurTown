package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class DummyEnemy extends Enemy {
	
	DummyEnemy() {
		xpos = -10;//test values
		ypos = -10;//test values
		monsterID = 0;
		bigMonster = false;
		health = 0;
		speed = 0;
		width = 0;
		height = 0;
		direction = 0;
		hitBox = new Rectangle(xpos, ypos, width, height);
		
	}
	
	void render(GameContainer container, Graphics g) {
		
	}
	
	void update() {
		
	}
}
