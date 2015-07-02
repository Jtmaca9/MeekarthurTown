package Game;

public class Enemy extends LivingEntity {

	int monsterID;
	boolean bigMonster;

	void checkBounds() {
		if (ypos > Setup.GAMEHEIGHT) {
			destroyed = true;
		}
	}
	
	void collision () {
		for (Player e : Game.currLevel.players) {
			checkFacingCollision(e);
		}
	}
	
	void checkFacingCollision(LivingEntity e) {
		if ((xpos + (width - speed)) >= (e.xpos)
				&& (xpos) <= (e.xpos) + (width - speed)
				&& (ypos + (height - speed) >= (e.ypos)
				&& (ypos) <= (e.ypos) + (height))) {
			if (direction == RIGHT) {// right
				xpos -= speed;

			} else if (direction == LEFT) {// left
				xpos += speed;

			} else if (direction == UP) {// up
				ypos += speed;

			} else if (direction == DOWN) {// down
				ypos -= speed;

			}
		}
	}
}
