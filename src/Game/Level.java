package Game;

import java.util.List;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

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

	// Player List and Enemy list and Event list
	List<EntityLiving> livingEntityList = new ArrayList<EntityLiving>();
	List<Enemy> enemyList = new ArrayList<Enemy>();
	List<Event> eventList = new ArrayList<Event>();
	List<EntityProjectile> playerProjectiles = new ArrayList<EntityProjectile>();
	List<EntityProjectile> enemyProjectiles = new ArrayList<EntityProjectile>();
	List<EntityAbilityMelee> enemyMeleeList = new ArrayList<EntityAbilityMelee>();
	List<EntityAbilityMelee> playerMeleeList = new ArrayList<EntityAbilityMelee>();
	List<EntityAbilityAOE> enemyAOEList = new ArrayList<EntityAbilityAOE>();
	List<EntityAbilityAOE> playerAOEList = new ArrayList<EntityAbilityAOE>();
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
		lives = 10;
		deltaTime = 0;

		// Adjust so they are in the middle of wall segment
		for (int i = 0; i < 5; i++) {
			lanes[i] = new Coords((i * 384) + 192, -64);
		}

		for (int j = 0; j < 5; j++) {
			walls[j] = new EntityWall(j * 384, 1000);
		}

		// testing area
		players[0] = new EntityPlayer(1, 320, 320, "wizard");
		players[1] = new EntityPlayer(2, 320, 320, "wizard");

//		eventList.add(new Event(1, 0, lanes[0], 0));
//		eventList.add(new Event(2, 0, lanes[1], 2000));
//		eventList.add(new Event(4, 0, lanes[2], 3000));
//		eventList.add(new Event(1, 0, lanes[3], 4000));
//		eventList.add(new Event(4, 0, lanes[4], 5000));
//
//		eventList.add(new Event(7, 0, lanes[4], 7000));
//		eventList.add(new Event(1, 0, lanes[3], 8000));
//		eventList.add(new Event(4, 0, lanes[2], 9000));
//		eventList.add(new Event(1, 0, lanes[1], 10000));
//		eventList.add(new Event(4, 0, lanes[0], 11000));
		
		loadLevel();

	}

	void update(int delta) {
		updatePlayers();
		updateWalls();
		updateAbilities();
		updateEnemies();
		getTime(delta);
		deltaTime = delta;
		checkEvent();
	}

	void render(GameContainer container, Graphics g) {
		for (int j = 0; j < 5; j++) {
			if (!walls[j].destroyed) {
				walls[j].render(container, g);
			}
		}
		
		for (Enemy i : enemyList) {
			i.render(container, g);
		}
		for (EntityAbilityMelee m : enemyMeleeList) {
			m.render(container, g);
		}
		for (EntityAbilityMelee n : playerMeleeList) {
			n.render(container, g);
		}
		for (EntityProjectile p : playerProjectiles) {
			p.render(container, g);
		}
		for (EntityProjectile k : enemyProjectiles) {
			k.render(container, g);
		}
		for (EntityAbilityAOE a : enemyAOEList) {
			a.render(container, g);
		}
		for (EntityAbilityAOE o : playerAOEList) {
			o.render(container, g);
		}

		

		for (int i = 0; i < playerCount; i++) {
			if (!players[i].destroyed) {
				players[i].render(container, g);
			}
		}
	}
	
	void loadLevel(){
		if (new File("Data/Levels/" + levelName + ".txt").isFile()){
			
			try {
				scanner = new Scanner(new File("Data/Levels/" + levelName + ".txt"));
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
		for (EntityProjectile k : enemyProjectiles) {
			k.update();
		}

		for (EntityAbilityMelee m : enemyMeleeList) {
			m.update(deltaTime);
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
		
		projectileIterator = playerProjectiles.iterator();
		while (projectileIterator.hasNext()) {
			EntityProjectile p = projectileIterator.next();
			if (p.destroyed) {
				if(p.targetsEnemy && p.spawnsAOE){
					Game.currLevel.playerAOEList.add(new EntityAbilityAOE((int)p.xpos,(int) p.ypos,0));
				}else if(p.spawnsAOE){
					Game.currLevel.enemyAOEList.add(new EntityAbilityAOE((int)p.xpos,(int) p.ypos, 1));
				}
				projectileIterator.remove();
			}

		}

		projectileIterator = enemyProjectiles.iterator();
		while (projectileIterator.hasNext()) {
			EntityProjectile p = projectileIterator.next();
			if (p.destroyed) {
				if(p.targetsEnemy && p.spawnsAOE){
					Game.currLevel.playerAOEList.add(new EntityAbilityAOE((int)p.xpos,(int) p.ypos,0));
				}else if(p.spawnsAOE){
					Game.currLevel.enemyAOEList.add(new EntityAbilityAOE((int)p.xpos,(int) p.ypos, 1));
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

		enemyMeleeIterator = enemyMeleeList.iterator();
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

		enemyIterator = enemyList.iterator();
		while (enemyIterator.hasNext()) {
			Enemy e = enemyIterator.next();
			if (e.destroyed) {
				enemyIterator.remove();
			}

		}
	}

	boolean checkLives() {
		if (lives <= 0) {
			return true;
		} else {
			return false;
		}
	}

}
