package Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Image;

public class Ability {
	
	int abilityID;
	int direction, directionMod;
	int range;
	int numOfProjectiles;
	Image image;
	
	final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	
	// negative healthMod value means damage, positive means heal
	int speed, cooldown, currCooldown, healthMod, size;
	// if targetsEnemy is false, it hits players
	boolean targetsEnemy;
	
	Image abilityIcon, projectileImage;
	
	void useAbility(int dir, int pX, int pY, int cS) {
		if(checkCooldown()){
			getCastDirection(dir);
			if (targetsEnemy) {
				Game.currLevel.playerProjectiles.add(new Projectile(speed, healthMod, size, dir, image, pX, pY, cS, range));

			} else {
				Game.currLevel.enemyProjectiles.add(new Projectile(speed, healthMod, size, RIGHT, image, pX, pY, cS, range));
			}
		}
	}
	
	//move to enemy  
	void useMB(int dir, int pX, int pY, int cS) {
		if(checkCooldown()){
			Game.currLevel.enemyProjectiles.add(new Projectile(speed, healthMod, size, RIGHT, image, pX, pY, cS, range));
			Game.currLevel.enemyProjectiles.add(new Projectile(speed, healthMod, size, LEFT, image, pX, pY, cS, range));
			Game.currLevel.enemyProjectiles.add(new Projectile(speed, healthMod, size, DOWN, image, pX, pY, cS, range));
			Game.currLevel.enemyProjectiles.add(new Projectile(speed, healthMod, size, UP, image, pX, pY, cS, range));
		}
	}
	
	void getCastDirection(int dir) {
		// The direction the ability will be cast in (player direction + modifier)
		if (dir == 0) {
			direction = UP;
		} else if (dir == 1) {
			direction = RIGHT;
		} else if (dir == 2) {
			direction = DOWN;
		} else if (dir == 3) {
			direction = LEFT;
		}
		direction += directionMod;
		
	}
	
	boolean checkCooldown(){
		if (currCooldown == 0){
			currCooldown = cooldown;
			return true;
		}else{
			return false;
		}
		
	}
}
