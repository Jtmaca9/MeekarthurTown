package Game;

public class Enemy extends LivingEntity {

	int monsterID;
	boolean bigMonster;

	void checkBounds() {
		if (ypos > Setup.GAMEHEIGHT) {
			destroyed = true;
			Game.currLevel.lives--;
		}
	}
	
	
	
	void collision () {
		for (Player e : Game.currLevel.players) {
			if(checkFacingCollision(e)){
				attack(0);
			}
		}
		
		for (Wall w : Game.currLevel.walls) {
			if(checkFacingCollision(w)){
				attack(0);
			}
		}
		
		for (Projectile p : Game.currLevel.playerProjectiles) {
			if(checkCollision(p)){
				p.destroyed = true;
				currHealth += p.healthMod;
			}
		}
	}
	
	void attack(int i){
		ability[i].useMB(direction, xpos, ypos, width);
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
