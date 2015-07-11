package Game;

public class EnemyRanged extends Enemy {
	
	int range;
	int target;
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
	
	void findTargetHighestHP() {
		// sets target to index of player with highest hp percentage
		target = 0;
		for (int i = 1; i <= Game.currLevel.playerCount; i++) {
			if (Game.currLevel.playerCount == i) {
				break;
			} else if (!Game.currLevel.players[i-1].destroyed) {
				if (Game.currLevel.players[target].healthPercent <= Game.currLevel.players[i].healthPercent) {
					target = i;
				}
			}
		}
		//gettarget
	}
	
	void getTargetCoords() {
		targetPos.x = Game.currLevel.players[target].xpos;
		targetPos.y = Game.currLevel.players[target].ypos;
	}
}
