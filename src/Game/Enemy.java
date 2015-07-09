package Game;

import org.newdawn.slick.geom.Rectangle;

public class Enemy extends LivingEntity {

	int monsterID;
	boolean bigMonster;
	
	void update() {
		move();
		cooldowns();
		collision();
		baseBehaviour();
		behaviour();
		checkBounds();
		checkHealth();
	}
	
	void move() {

	}
	
	void baseBehaviour() {
		
	}
	
	void behaviour(){
		
	}

	void checkBounds() {
		if (ypos > Setup.GAMEHEIGHT) {
			destroyed = true;
			Game.currLevel.lives--;
		}
	}
	
	
	
	void collision () {
		for (Projectile p : Game.currLevel.playerProjectiles) {
			if(checkCollision(p)){
				p.destroyed = true;
				currHealth += p.healthMod;
			}
		}
	}
	
	//fix ability then change this
	void attack(int i){

	}
	
	boolean checkFacingCollision(LivingEntity e) {
		if ((xpos + width - speed) >= e.xpos
				&& xpos + speed <= (e.xpos + e.width)
				&& (ypos + height - speed) >= e.ypos - e.speed
				&& ypos + e.speed <= (e.ypos + e.height)) {
			if (direction == RIGHT) {// right
				xpos -= speed;
				return true;

			} else if (direction == LEFT) {// left
				xpos += speed;
				return true;

			} else if (direction == UP) {// up
				ypos += speed;
				return true;

			} else if (direction == DOWN) {// down
				ypos -= speed;
				return true;
			}
		}
		return false;
	}
}
