package Game;

import java.util.List;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class Level {

	EntityWall[] walls;
	EntityPlayer[] players;
	int playerCount;
	int lives;
	int deltaTime;
	long time = 0;
	int[] tempEvent;
	String levelName;
	Coords[] lanes;
	Image bg;
	
	//score shiz
	int score = 0;
	int scoreMultiplier = 1;

	// Player List and Enemy list and Event list
	List<EntityLiving> livingEntityList = new ArrayList<EntityLiving>();
	List<Enemy> enemyList = new ArrayList<Enemy>();
	List<Enemy> flyingEnemyList = new ArrayList<Enemy>();
	List<Event> eventList = new ArrayList<Event>();
	List<EntityProjectile> playerProjectiles = new ArrayList<EntityProjectile>();
	List<EntityProjectile> enemyProjectiles = new ArrayList<EntityProjectile>();
	List<EntityProjectile> bothProjectiles = new ArrayList<EntityProjectile>();
	List<EntityAbilityMelee> enemyMeleeList = new ArrayList<EntityAbilityMelee>();
	List<EntityAbilityMelee> playerMeleeList = new ArrayList<EntityAbilityMelee>();
	List<EntityAbilityMelee> bothMeleeList = new ArrayList<EntityAbilityMelee>();
	List<EntityAbilityAOE> enemyAOEList = new ArrayList<EntityAbilityAOE>();
	List<EntityAbilityAOE> playerAOEList = new ArrayList<EntityAbilityAOE>();
	List<EntityAbilityAOE> bothAOEList = new ArrayList<EntityAbilityAOE>();
	List<EntityItem> bloodList = new ArrayList<EntityItem>();
	List<EntityItem> itemList = new ArrayList<EntityItem>();
	Iterator<EntityItem> itemIterator;
	Iterator<EntityAbilityAOE> AOEIterator;
	Iterator<EntityAbilityMelee> enemyMeleeIterator;
	Iterator<Enemy> enemyIterator;
	Iterator<Event> eventIterator;
	Iterator<EntityProjectile> projectileIterator;
	Scanner scanner;

	Level(int pCount, String lName) {
		players = new EntityPlayer[pCount];
		walls = new EntityWall[5];
		playerCount = pCount;
		levelName = lName;
		lanes = new Coords[5];
		lives = 5;
		deltaTime = 0;

		// Adjust so they are in the middle of wall segment
		for (int i = 0; i < 5; i++) {
			lanes[i] = new Coords((i * 384) + 192, -64);
		}

		for (int j = 0; j < 5; j++) {
			walls[j] = new EntityWall(j * 384, 1000);
		}

		// testing area
		for(int k = 0; k < playerCount; k++){
			players[k] = new EntityPlayer(k+1, 300 + (k*64), 640, Game.pClass[k]); 
		}
		loadLevel();

	}

	void update(int delta) {
		updatePlayers();
		updateItems();
		updateWalls();
		updateAbilities();
		updateEnemies();
		getTime(delta);
		deltaTime = delta;
		checkEvent();
	}

	void render(GameContainer container, Graphics g) {
		bg.draw(0, 0, 1920, 1080);
		for (int j = 0; j < 5; j++) {
			if (!walls[j].destroyed) {
				walls[j].render(container, g);
			}
		}
		for (EntityItem i : bloodList) {
			i.render(container, g);
		}
		for (EntityAbilityAOE a : enemyAOEList) {
			a.render(container, g);
		}
		for (EntityAbilityAOE o : playerAOEList) {
			o.render(container, g);
		}
		for (EntityAbilityAOE o : bothAOEList) {
			o.render(container, g);
		}
		for (Enemy i : enemyList) {
			i.render(container, g);
		}
		for (Enemy i : flyingEnemyList) {
			i.render(container, g);
		}
		for (EntityAbilityMelee m : enemyMeleeList) {
			m.render(container, g);
		}
		for (EntityAbilityMelee u : bothMeleeList) {
			u.render(container, g);
		}
		for (EntityAbilityMelee n : playerMeleeList) {
			n.render(container, g);
		}
		for (EntityProjectile p : playerProjectiles) {
			p.render(container, g);
		}
		for (EntityProjectile c : bothProjectiles) {
			c.render(container, g);
		}
		for (EntityProjectile k : enemyProjectiles) {
			k.render(container, g);
		}
		for (EntityItem i : itemList) {
			i.render(container, g);
		}
		

		

		for (int i = 0; i < playerCount; i++) {
			players[i].render(container, g);
		}
	}
	
	void loadLevel(){
		if (new File("Data/Levels/" + levelName + "/" + levelName + ".txt").isFile()){
			
			try {
				scanner = new Scanner(new File("Data/Levels/" + levelName + "/" + levelName + ".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int p = 0;
			tempEvent= new int[4];
			
			
			while(scanner.hasNextInt()){
				if(p < 4){
					tempEvent[p++] = scanner.nextInt();
				}else{
					p = 0;
					eventList.add(new Event(tempEvent[0], tempEvent[1], lanes[tempEvent[2]], tempEvent[3]));
					
				}
				
			}
			
		}
		try {
			bg = new Image("Data/Levels/" + levelName + "/" + levelName + ".png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void getTime(int delta) {
		time += delta;
	}

	void checkEvent() {
		eventIterator = eventList.iterator();
		while (eventIterator.hasNext()) {
			Event e = eventIterator.next();
			if (time >= e.activateTime) {
				e.fire();
				eventIterator.remove();
			}

		}

	}
	
	void updateItems(){
		for (EntityItem i : itemList) {
			i.update(deltaTime);
		}
		
		for (EntityItem i : bloodList) {
			i.update(deltaTime);
		}
		
		itemIterator = itemList.iterator();
		while (itemIterator.hasNext()) {
			EntityItem i = itemIterator.next();
			if (i.destroyed) {
				itemIterator.remove();
			}

		}
		
		itemIterator = bloodList.iterator();
		while (itemIterator.hasNext()) {
			EntityItem i = itemIterator.next();
			if (i.destroyed) {
				itemIterator.remove();
			}

		}
	}
	


	void updatePlayers() {
		for (int i = 0; i < playerCount; i++) {

			if (!players[i].destroyed) {
				players[i].update();

			} else {
				players[i].xpos = -100;
				players[i].ypos = -100;
			}
		}
	}

	void updateWalls() {
		for (int j = 0; j < 5; j++) {

			if (!walls[j].destroyed) {
				walls[j].update();

			} else {
				walls[j].xpos = -1000;
				walls[j].ypos = -1000;
			}
		}
	}

	void updateAbilities() {
		for (EntityProjectile p : playerProjectiles) {
			p.update();
		}
		for (EntityProjectile u : bothProjectiles) {
			u.update();
		}
		for (EntityProjectile k : enemyProjectiles) {
			k.update();
		}

		for (EntityAbilityMelee m : enemyMeleeList) {
			m.update(deltaTime);
		}
		
		for (EntityAbilityMelee c : bothMeleeList) {
			c.update(deltaTime);
		}
		
		for (EntityAbilityMelee n : playerMeleeList) {
			n.update(deltaTime);
		}
		
		for (EntityAbilityAOE a : enemyAOEList) {
			a.update(deltaTime);
		}
		
		for (EntityAbilityAOE o : playerAOEList) {
			o.update(deltaTime);
		}
		
		for (EntityAbilityAOE o : bothAOEList) {
			o.update(deltaTime);
		}
		
		projectileIterator = playerProjectiles.iterator();
		while (projectileIterator.hasNext()) {
			EntityProjectile p = projectileIterator.next();
			if (p.destroyed) {
				if(p.targets == 1 && p.spawnsAOE){
					Game.currLevel.playerAOEList.add(new EntityAbilityAOE((int)p.xpos,(int) p.ypos, 0, p.owner));
				}else if(p.targets == 0 && p.spawnsAOE){
					Game.currLevel.enemyAOEList.add(new EntityAbilityAOE((int)p.xpos,(int) p.ypos, 1, p.owner));
				}
				projectileIterator.remove();
			}

		}
		
		projectileIterator = bothProjectiles.iterator();
		while (projectileIterator.hasNext()) {
			EntityProjectile p = projectileIterator.next();
			if (p.destroyed) {
				if(p.targets == 1 && p.spawnsAOE){
					Game.currLevel.playerAOEList.add(new EntityAbilityAOE((int)p.xpos,(int) p.ypos,0, p.owner));
				}else if(p.targets == 0 && p.spawnsAOE){
					Game.currLevel.enemyAOEList.add(new EntityAbilityAOE((int)p.xpos,(int) p.ypos, 1, p.owner));
				}
				projectileIterator.remove();
			}

		}

		projectileIterator = enemyProjectiles.iterator();
		while (projectileIterator.hasNext()) {
			EntityProjectile p = projectileIterator.next();
			if (p.destroyed) {
				if(p.targets == 1 && p.spawnsAOE){
					Game.currLevel.playerAOEList.add(new EntityAbilityAOE((int)p.xpos,(int) p.ypos,0, p.owner));
				}else if(p.targets == 0 && p.spawnsAOE){
					Game.currLevel.enemyAOEList.add(new EntityAbilityAOE((int)p.xpos,(int) p.ypos, 1, p.owner));
				}
				projectileIterator.remove();
			}

		}
		
		AOEIterator = enemyAOEList.iterator();
		while (AOEIterator.hasNext()) {
			EntityAbilityAOE a = AOEIterator.next();
			if (a.destroyed) {
				AOEIterator.remove();
			}

		}

		AOEIterator = playerAOEList.iterator();
		while (AOEIterator.hasNext()) {
			EntityAbilityAOE o = AOEIterator.next();
			if (o.destroyed) {
				AOEIterator.remove();
			}

		}
		
		AOEIterator = bothAOEList.iterator();
		while (AOEIterator.hasNext()) {
			EntityAbilityAOE o = AOEIterator.next();
			if (o.destroyed) {
				AOEIterator.remove();
			}

		}

		enemyMeleeIterator = enemyMeleeList.iterator();
		while (enemyMeleeIterator.hasNext()) {
			EntityAbilityMelee m = enemyMeleeIterator.next();
			if (m.destroyed) {
				enemyMeleeIterator.remove();
			}

		}
		
		enemyMeleeIterator = bothMeleeList.iterator();
		while (enemyMeleeIterator.hasNext()) {
			EntityAbilityMelee m = enemyMeleeIterator.next();
			if (m.destroyed) {
				enemyMeleeIterator.remove();
			}

		}
		
		enemyMeleeIterator = playerMeleeList.iterator();
		while (enemyMeleeIterator.hasNext()) {
			EntityAbilityMelee n = enemyMeleeIterator.next();
			if (n.destroyed) {
				enemyMeleeIterator.remove();
			}

		}
	}

	void updateEnemies() {
		for (Enemy j : enemyList) {
			j.update();
		}
		
		for (Enemy j : flyingEnemyList) {
			j.update();
		}

		enemyIterator = enemyList.iterator();
		while (enemyIterator.hasNext()) {
			Enemy e = enemyIterator.next();
			if (e.destroyed) {
				if(e.bigMonster){
					itemList.add(new EntityItem(0, (int)e.xpos + (e.width/2),(int) e.ypos + (e.height/2)));
					bloodList.add(new EntityItem(2, (int)e.xpos + (e.width/2),(int) e.ypos + (e.height/2)));
				}else{
					bloodList.add(new EntityItem(1, (int)e.xpos + (e.width/2),(int) e.ypos + (e.height/2)));
				}
				
				if(e instanceof EnemyMeleeTargetExplosive){
					enemyAOEList.add(new EntityAbilityAOE((int)e.xpos,(int) e.ypos, 1, e));
				}
				
				score += e.score * scoreMultiplier;
				scoreMultiplier++;
				enemyIterator.remove();
				
			}

		}
		
		enemyIterator = flyingEnemyList.iterator();
		while (enemyIterator.hasNext()) {
			Enemy e = enemyIterator.next();
			if (e.destroyed) {
				if(e.bigMonster){
					itemList.add(new EntityItem(0, (int)e.xpos + (e.width/2),(int) e.ypos + (e.height/2)));
					bloodList.add(new EntityItem(2, (int)e.xpos + (e.width/2),(int) e.ypos + (e.height/2)));
				}else{
					bloodList.add(new EntityItem(1, (int)e.xpos + (e.width/2),(int) e.ypos + (e.height/2)));
				}
				
				if(e instanceof EnemyMeleeTargetExplosive){
					enemyAOEList.add(new EntityAbilityAOE((int)e.xpos,(int) e.ypos, 1, e));
				}
				
				score += e.score * scoreMultiplier;
				scoreMultiplier++;
				enemyIterator.remove();
				
			}

		}
	}

	boolean checkLives() {
		if (lives <= 0 || checkPlayers()) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean checkPlayers(){
		boolean dead = true;
		for(int i = 0; i < playerCount; i++){
			if(!players[i].destroyed){
				dead = false;
			}
		}
		return dead;
	}

}
