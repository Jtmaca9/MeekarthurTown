package Game;

import org.newdawn.slick.geom.Rectangle;

public class Player extends LivingEntity {

	String playerClass;
	int playerID;
	int movementQueue[];
	int lastDirection;
	int tempX, tempY;
	final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, NOMOVE = 4;

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
			break;

		default:
			break;
		}
	}

	void update() {
		move();
		for (Enemy i : Game.currLevel.enemyList) {
			if (checkCollision(i)) {
				System.out.println("lol gay");
			}
		}
	}

	void move() {
		direction = movementQueue[0];

		

		if (direction == UP) {
			ypos -= speed;
			lastDirection = UP;
		}

		if (direction == RIGHT) {
			xpos += speed;
			lastDirection = RIGHT;
		}

		if (direction == DOWN) {
			ypos += speed;
			lastDirection = DOWN;
		}

		if (direction == LEFT) {
			xpos -= speed;
			lastDirection = LEFT;
		}

		for (Enemy e : Game.currLevel.enemyList) {
			if (checkCollision(e)) {
				if (lastDirection == UP) {
					if (e.ypos > ypos - e.height + speed) {
						ypos += speed + 1;
					}
				} else if (lastDirection == DOWN) {
					if (e.ypos < ypos + e.height - speed) {
						ypos -= speed - 1;
					}
				}
			}
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

}
