package Game;

public class EnemyRanged extends Enemy {
	
	int range;
	
	Coords targetPos;
	
	void move() {
		currSpeed = speed; // Change this later (test)
		if (direction == DOWN) {
			ypos += currSpeed;
		}

		hitBox.setY(ypos);
		hitBox.setX(xpos);
	}

	void baseBehaviour() {
		for (EntityPlayer e : Game.currLevel.players) {
			checkFacingCollision(e);
		}

		for (EntityWall w : Game.currLevel.walls) {
			if (checkFacingCollision(w)) {
				attack(0);
			}
		}
		
		ability[1].currCooldown -= Game.currLevel.deltaTime;

		if (ability[1].currCooldown < 0) {
			ability[1].currCooldown = 0;
		}

		getTargetCoords();
	}
	
	
	void getTargetCoords() {
		if(targetEntity != null){
			targetPos.x = targetEntity.xpos;
			targetPos.y = targetEntity.ypos;
		}
	}
	
}
