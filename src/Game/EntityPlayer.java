package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class EntityPlayer extends EntityLiving {

	String playerClass;
	int playerID;
	int movementQueue[];
	int lastDirection;
	int tempX, tempY, dx, dy;
	int abilityDir;
	float healthPercent;
	Ability activeAbility;
	Image classIcon;
	int animTime = 0;
	boolean  anim = false;

	EntityPlayer(int pID, int x, int y, String pClass) {
		enemy = false;
		playerID = pID;
		playerClass = pClass;
		width = 48;
		height = 48;
		xpos = x;
		ypos = y;
		ability = new Ability[3];
		movementQueue = new int[4];
		hitBox = new Rectangle(xpos, ypos, width, height);
		for (int i = 0; i < 4; i++) {
			movementQueue[i] = NOMOVE;
		}
		championCreate();
	}

	void championCreate() {

		switch (playerClass) {

		case "Wizard":
			health = 75;
			currHealth = health;
			speed = 6;
			baseSpeed = speed;
			image = Game.wizard;
			classIcon = Game.wizardIcon;

			ability[0] = new AbilityProjectile(0);
			ability[1] = new AbilityProjectile(1);
			ability[2] = new AbilityProjectile(2);

			activeAbility = ability[0];

			break;
			
		case "Archer":
			health = 75;
			currHealth = health;
			speed = 8;
			baseSpeed = speed;
			image = Game.archer;
			classIcon = Game.archerIcon;

			ability[0] = new AbilityProjectile(3);
			ability[1] = new AbilityProjectile(4);
			ability[2] = new AbilityProjectile(5);

			activeAbility = ability[0];

			break;
			
		case "Knight":
			health = 75;
			currHealth = health;
			speed = 6;
			baseSpeed = speed;
			image = Game.knight;
			classIcon = Game.knightIcon;

			ability[0] = new AbilityMelee(1);
			ability[1] = new AbilityProjectile(1);
			ability[2] = new AbilityProjectile(1);

			activeAbility = ability[0];

			break;
			
		case "Rogue":
			health = 75;
			currHealth = health;
			speed = 8;
			baseSpeed = speed;
			image = Game.rogue;
			classIcon = Game.rogueIcon;

			ability[0] = new AbilityProjectile(0);
			ability[1] = new AbilityProjectile(1);
			ability[2] = new AbilityProjectile(1);

			activeAbility = ability[0];

			break;
			
		case "Cleric":
			health = 75;
			currHealth = health;
			speed = 6;
			baseSpeed = speed;
			image = Game.cleric;
			classIcon = Game.clericIcon;

			ability[0] = new AbilityProjectile(0);
			ability[1] = new AbilityProjectile(1);
			ability[2] = new AbilityProjectile(1);

			activeAbility = ability[0];

			break;
		}
	}
	void render(GameContainer container, Graphics g) {
		if(!destroyed){
			image.draw(xpos, ypos, width, height);
			g.setColor(Color.red);
			g.fillRect(xpos, ypos - 10, width, 5);
			g.setColor(Color.green);
			g.fillRect(xpos, ypos - 10, ((currHealth / health)) * width, 5);
		
			meleeAttack(container, g);
		}
		
		drawPlayerFrame(container, g);
	}
	
	void meleeAttack(GameContainer container, Graphics g){
		if(meleeAttack){
			animTime += Game.currLevel.deltaTime;
			Game.meleeAttack.draw(xpos - 75, ypos - 75, width +150, height + 150);
		}
		if (animTime > 320){
			meleeAttack = false;
		}
		
	}
	
	void drawPlayerFrame(GameContainer container, Graphics g){
		if(playerID == 1){
			 dx = 10;
			 dy = 10;
			
		}else if(playerID == 2){
			 dx = 1920 - 270;
			 dy = 10;
			
		}else if(playerID == 3){
			 dx = 10;
			 dy = 1080 - 140;
			
		}else if(playerID == 4){
			 dx = 1920 - 270;
			 dy = 1080 - 140;
			
		}
		
		g.setColor(Color.lightGray);
		g.fillRect(dx, dy, 260, 130);
		g.setColor(Color.black);
		g.drawRect(dx, dy, 260, 130);
		g.setColor(Color.blue);
		g.drawString("Player " + playerID, dx + 10, dy + 10);
		classIcon.draw(dx + 180, dy + 10, 64, 64);
		g.setColor(Color.red);
		g.fillRect(dx + 10, dy + 40, 150, 20);
		g.setColor(Color.green);
		g.fillRect(dx + 10, dy + 40, ((currHealth / health)) * 150, 20);
		if(ability[1].currCooldown > 0){
			g.setColor(Color.red);
		}else{
			g.setColor(Color.white);
		}
		g.fillRect(dx + 10, dy + 70, 48, 48);
		ability[1].abilityIcon.draw(dx + 10, dy + 70, 48, 48);
		
		if(ability[2].currCooldown > 0){
			g.setColor(Color.red);
		}else{
			g.setColor(Color.white);
		}
		g.fillRect(dx + 68, dy + 70, 48, 48);
		ability[2].abilityIcon.draw(dx + 68, dy + 70, 48, 48);
	}

	void update() {
		
		move();
		collision();
		checkHealth();
		updateEffects();
		cooldowns();
		healthPercent = (currHealth / health) * 100;
		
		
	}
	

	void useAbility(int dir) {
		if((activeAbility == ability[0]) && (ability[0] instanceof AbilityMelee)){
			meleeAttack = true;
			animTime = 0;
		}
		activeAbility.useAbility(dir, (int) xpos, (int) ypos, width);
		activeAbility = ability[0];
	}

	void primeAbility(int a) {
		if (activeAbility == ability[a]) {
			activeAbility = ability[0];
		} else {
			activeAbility = ability[a];
		}
	}

	void cooldowns() {
		ability[0].currCooldown -= Game.currLevel.deltaTime;

		if (ability[0].currCooldown < 0) {
			ability[0].currCooldown = 0;
		}
		ability[1].currCooldown -= Game.currLevel.deltaTime;

		if (ability[1].currCooldown < 0) {
			ability[1].currCooldown = 0;
		}
		ability[2].currCooldown -= Game.currLevel.deltaTime;

		if (ability[2].currCooldown < 0) {
			ability[2].currCooldown = 0;
		}
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

	void collision() {
		for (Enemy e : Game.currLevel.enemyList) {
			checkFacingCollision(e);
			if (e instanceof EnemyMelee) {
				if (checkRangeBox(e)) {
					e.attack(0);
				}
			}
		}

		for (EntityWall w : Game.currLevel.walls) {
			checkFacingCollision(w);
		}

		for (EntityProjectile p : Game.currLevel.enemyProjectiles) {
			if (checkCollision(p)) {
				if(p.hasEffect){
					getEffect(p.effectIDPlayer);
				}
				currHealth += p.playerHealthMod;
				Game.currLevel.scoreMultiplier = 1;
				p.destroyed = true;
			}
		}
		
		for (EntityProjectile p : Game.currLevel.bothProjectiles) {
			if (checkCollision(p)) {
				if(p.hasEffect){
					getEffect(p.effectIDPlayer);
				}
				currHealth += p.playerHealthMod;
				Game.currLevel.scoreMultiplier = 1;
				p.destroyed = true;
			}
		}
		
		for (EntityItem i : Game.currLevel.itemList) {
			if (checkCollision(i)) {
				if(i.hasEffect){
					getEffect(i.effectID);
				}
				currHealth += i.healthMod;
				i.destroyed = true;
			}
		}
		
		for (EntityAbilityMelee m : Game.currLevel.enemyMeleeList) {
			if (checkCollision(m)) {
				if(m.hasEffect){
					getEffect(m.effectIDPlayer);
				}
				currHealth += m.playerHealthMod;
				Game.currLevel.scoreMultiplier = 1;
				m.destroyed = true;
			}
		}
		
		for (EntityAbilityMelee m : Game.currLevel.bothMeleeList) {
			if (checkCollision(m)) {
				if(m.hasEffect){
					getEffect(m.effectIDPlayer);
				}
				currHealth += m.playerHealthMod;
				Game.currLevel.scoreMultiplier = 1;
				m.destroyed = true;
			}
		}
		
		checkBounds();
	}
	
	void checkBounds() {
		if (xpos < 0) {
			xpos = 0;
		} else if (xpos > Setup.GAMEWIDTH - width) {
			xpos = Setup.GAMEWIDTH - width;
		} else if (ypos < 0) {
			ypos = 0;
		} else if (ypos > Setup.GAMEHEIGHT -  height) {
			ypos = Setup.GAMEHEIGHT - height;
		}
	}
	
	void checkHealth() {
		if (currHealth <= 0) {
			currHealth = 0;
			destroyed = true;
		}else if(currHealth > health){
			currHealth = health;
		}
	}
	

	boolean checkFacingCollision(EntityLiving e) {
		if ((xpos + width - speed) >= e.xpos && xpos + speed <= (e.xpos + e.width)
				&& (ypos + height - speed) >= e.ypos - e.speed && ypos + e.speed <= (e.ypos + e.height)) {
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
