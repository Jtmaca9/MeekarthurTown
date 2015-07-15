package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Enemy extends EntityLiving {

	int monsterID;
	boolean bigMonster;
	int target;

	void update() {
		baseBehaviour();
		behaviour();
		move();
		cooldowns();
		collision();
		updateEffects();
		checkBounds();
		checkHealth();
	}

	void move() {

	}

	void baseBehaviour() {

	}

	void behaviour() {

	}
	
	

	void checkBounds() {
		if (ypos > Setup.GAMEHEIGHT) {
			destroyed = true;
			Game.currLevel.lives--;
		}
	}

	void collision() {
		for (EntityProjectile p : Game.currLevel.playerProjectiles) {
			if (checkCollision(p)) {
				if(p.hasEffect){
					getEffect(p.effectID);
				}
				p.destroyed = true;
				currHealth += p.healthMod;
			}
		}
		
		for (EntityAbilityMelee m : Game.currLevel.playerMeleeList) {
			if (checkCollision(m)) {
			
				m.destroyed = true;
				currHealth += m.healthMod;
			}
		}
	}

	void attack(int i) {

	}
	
	void render(GameContainer container, Graphics g) {
		image.draw(xpos, ypos, width, height);
		g.setColor(Color.red);
		g.fillRect(xpos, ypos - 10, width, 5);
		g.setColor(Color.green);
		g.fillRect(xpos, ypos - 10, ((currHealth / health)) * width, 5);
		enemyMeleeAttack(container, g);
	}
	
	void enemyMeleeAttack(GameContainer container, Graphics g){
		if(meleeAttack){
			Game.meleeAttack.drawFlash(xpos - 48, ypos - 48, width +96, height + 96);
			meleeAttack = false;
			
			
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
}
