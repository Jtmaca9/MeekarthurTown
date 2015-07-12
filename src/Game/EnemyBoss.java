package Game;

public class EnemyBoss extends Enemy {
	
	int range;
	int target;
	Coords targetPos;
	
	void move() {
		currSpeed = speed; // Change this later (test)
		if ((direction == DOWN) && (ypos < 300)) {
			ypos += currSpeed;
		}

		hitBox.setY(ypos);
		hitBox.setX(xpos);
	}

	void baseBehaviour() {
		for (EntityPlayer e : Game.currLevel.players) {
			checkFacingCollision(e);
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
		float highest = 0;
		for (int i = 0; i < Game.currLevel.playerCount; i++) {
			if(Game.currLevel.players[i].healthPercent >= highest){
				target = i;
				highest = Game.currLevel.players[i].healthPercent;
			}
		}
			
	}
	
	void getTargetCoords() {
		targetPos.x = Game.currLevel.players[target].xpos;
		targetPos.y = Game.currLevel.players[target].ypos;
	}
	
}
