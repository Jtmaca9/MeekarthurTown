package Game;

import org.newdawn.slick.geom.Rectangle;

public class Player extends LivingEntity {

	String playerClass;
	int playerID;
	int movementQueue[];
	int lastDirection;
	int tempX, tempY;
	Ability abilityBasic;

	Player(int pID, int x, int y, String pClass) {
		enemy = false;
		playerID = pID;
		playerClass = pClass;
		width = 32;
		height = 32;
		xpos = x;
		ypos = y;
		movementQueue = new int[4];
		hitBox = new Rectangle(xpos, ypos, width, height);
		for (int i = 0; i < 4; i++) {
			movementQueue[i] = NOMOVE;
		}
		championCreate();
	}

	void championCreate() {

		switch (playerClass) {

		case "wizard":
			health = 75;
			speed = 4;
			image = Game.player;
			abilityBasic = new Ability(0);
			break;

		default:
			break;
		}
	}

	void update() {
		move();
		collision();
	}
	
	void useBasic() {
		abilityBasic.useAbility(lastDirection, xpos, ypos, width);
	}

	void move() {
		direction = movementQueue[0];

		if (direction == NOMOVE) {
			currSpeed = 0;
		} else {
			currSpeed = speed;
		}

		if (direction == UP) {
			ypos -= currSpeed;
			lastDirection = UP;
		} else if (direction == RIGHT) {
			xpos += currSpeed;
			lastDirection = RIGHT;
		} else if (direction == DOWN) {
			ypos += currSpeed;
			lastDirection = DOWN;
		} else if (direction == LEFT) {
			xpos -= currSpeed;
			lastDirection = LEFT;
		}

		hitBox.setX(xpos);
		hitBox.setY(ypos);

	}

	void moveUp(boolean keyDown) {
		if (keyDown) {
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == NOMOVE) {
					movementQueue[i] = UP;
					break;
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == UP) {
					while (i < 3) {
						movementQueue[i] = movementQueue[i + 1];
						i++;
					}
					movementQueue[3] = NOMOVE;
				}
			}
		}

	}

	void moveRight(boolean keyDown) {
		if (keyDown) {
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == NOMOVE) {
					movementQueue[i] = RIGHT;
					break;
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == RIGHT) {
					while (i < 3) {
						movementQueue[i] = movementQueue[i + 1];
						i++;
					}
					movementQueue[3] = NOMOVE;
				}
			}
		}

	}

	void moveDown(boolean keyDown) {
		if (keyDown) {
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == NOMOVE) {
					movementQueue[i] = DOWN;
					break;
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == DOWN) {
					while (i < 3) {
						movementQueue[i] = movementQueue[i + 1];
						i++;
					}
					movementQueue[3] = NOMOVE;
				}
			}
		}

	}

	void moveLeft(boolean keyDown) {
		if (keyDown) {
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == NOMOVE) {
					movementQueue[i] = LEFT;
					break;
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				if (movementQueue[i] == LEFT) {
					while (i < 3) {
						movementQueue[i] = movementQueue[i + 1];
						i++;
					}
					movementQueue[3] = NOMOVE;
				}
			}
		}

	}
	
	void collision () {
		for (Enemy e : Game.currLevel.enemyList) {
			checkFacingCollision(e);
		}
	}
	
	void checkFacingCollision(LivingEntity e) {
		if ((xpos + width - speed) >= e.xpos
				&& xpos + speed <= (e.xpos + e.width)
				&& (ypos + height - speed) >= e.ypos - e.speed
				&& ypos + e.speed <= (e.ypos + e.height)) {
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
