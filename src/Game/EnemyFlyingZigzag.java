package Game;

import org.newdawn.slick.geom.Rectangle;

public class EnemyFlyingZigzag extends EnemyFlying{
	
	final int MONSTERID = 8;
	int attackCD = 30, currAttackCD;
	int xDir;
	

	EnemyFlyingZigzag(Coords lane, boolean bM) {

		destroyed = false;
		bigMonster = bM;

		if (bigMonster) {
			// Test values
			health = 30;
			speed = 1.5f;
			width = 96;
			height = 96;
		} else {
			health = 20;
			speed = 1.5f;
			width = 48;
			height = 48;
		}

		xpos = (int) (lane.x - (0.5 * width));
		ypos = (int) lane.y;
		
		if (lane.x <= 950){
			xDir = 1;
		}else{
			xDir = -1;
		}

		ability = new Ability[2];
		hitBox = new Rectangle(xpos, ypos, width, height);
		image = Game.StandardFlyingImage;
		
		currHealth = health;
		currAttackCD = attackCD;
		baseSpeed = speed;

		direction = DOWN;


		
		
	}

	void attack(int i) {
		
	}
	
	void cooldowns(){
		
	}
	
	void attackTarget() {
	}
	
	void behaviour() {
		
		if(xpos > (1920 - 100)){
			xDir = -1;
		}else if(xpos < (100)){
			xDir = 1;
		}
		
	}
	
	void move(){
		currSpeed = speed; // Change this later (test)
		if (direction == DOWN) {
			ypos += (currSpeed * 0.4 );
		}
		
		xpos += (((currSpeed)*3.5) * xDir);

		hitBox.setY(ypos);
		hitBox.setX(xpos);
	}

}
