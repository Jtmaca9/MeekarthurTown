package Game;

import java.util.List;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Iterator;

public class Level {

	Wall[] walls;
	Player[] players;
	int playerCount;
	int lives;
	long time = 0;
	String levelName;
	Coords[] lanes;

	// Player List and Enemy list and Event list
	List<LivingEntity> livingEntityList = new ArrayList<LivingEntity>();
	List<Enemy> enemyList = new ArrayList<Enemy>();
	List<Event> eventList = new ArrayList<Event>();
	List<Projectile> playerProjectiles = new ArrayList<Projectile>();
	List<Projectile> enemyProjectiles = new ArrayList<Projectile>();
	List<MeleeAbilityEntity> enemyMeleeList = new ArrayList<MeleeAbilityEntity>();
	Iterator<MeleeAbilityEntity> enemyMeleeIterator;
	Iterator<Enemy> enemyIterator;
	Iterator<Event> eventIterator;
	Iterator<Projectile> projectileIterator;

	Level(int pCount, String lName) {
		players = new Player[pCount];
		walls = new Wall[5];
		playerCount = pCount;
		levelName = lName;
		lanes = new Coords[5];
		lives = 5;
		
		for(int i = 0; i < 5; i++){
			lanes[i] = new Coords((i*320)+320, -64);
		}
		
		for (int j = 0; j < 5; j++) {
			walls[j] = new Wall(j * 384, 1000);
		}

		// testing area
		players[0] = new Player(1, 320, 320, "wizard");
		
		int enemyCount = 15;
		int[][] eventData = new int[enemyCount][4];
		
		Random randomGenerator = new Random();
		int rand;
		
		for (int n = 0; n < enemyCount; n++) {
			eventData[n][0] = 1;
			eventData[n][1] = (n%5==4) ? 1 : 0;
			eventData[n][2] = (rand = randomGenerator.nextInt(5));
			eventData[n][3] = 1000 * 2 * (n+1);
		}
		
		for (int k = 0; k < 15; k++) {
			eventList.add(new Event(eventData[k][0], (eventData[k][1]==1) ? true : false, lanes[eventData[k][2]], eventData[k][3]));
		}
		
		/*
		eventList.add(new Event(1, false, lanes[0], 1000));
		eventList.add(new Event(1, false, lanes[1], 2000));
		eventList.add(new Event(1, false, lanes[2], 3000));
		eventList.add(new Event(1, false, lanes[3], 4000));
		eventList.add(new Event(1, false, lanes[4], 5000));
		
		eventList.add(new Event(1, true, lanes[4], 7000));
		eventList.add(new Event(1, true, lanes[3], 8000));
		eventList.add(new Event(1, true, lanes[2], 9000));
		eventList.add(new Event(1, true, lanes[1], 10000));
		eventList.add(new Event(1, true, lanes[0], 11000));	
		*/
	}

	void update(int delta) {
		for (int i = 0; i < playerCount; i++) {

			if (!players[i].destroyed) {
				players[i].update();

			} else {
				players[i].xpos = -100;
				players[i].ypos = -100;
			}
		}

		for (int j = 0; j < 5; j++) {

			if (!walls[j].destroyed) {
				walls[j].update();

			} else {
				walls[j].xpos = -1000;
				walls[j].ypos = -1000;
			}
		}

		for (Projectile p : playerProjectiles) {
			p.update();
		}

		for (Enemy j : enemyList) {
			j.update();
		}

		for (Projectile k : enemyProjectiles) {
			k.update();
		}
		
		for (MeleeAbilityEntity m : enemyMeleeList) {
			m.update();
		}

		// We will use the same iterator loop for destroyed projectiles.
		enemyIterator = enemyList.iterator();
		while (enemyIterator.hasNext()) {
			Enemy e = enemyIterator.next();
			if (e.destroyed) {
				enemyIterator.remove();
			}

		}

		projectileIterator = playerProjectiles.iterator();
		while (projectileIterator.hasNext()) {
			Projectile p = projectileIterator.next();
			if (p.destroyed) {
				projectileIterator.remove();
			}

		}

		projectileIterator = enemyProjectiles.iterator();
		while (projectileIterator.hasNext()) {
			Projectile p = projectileIterator.next();
			if (p.destroyed) {
				projectileIterator.remove();
			}

		}
		
		enemyMeleeIterator = enemyMeleeList.iterator();
		while (enemyMeleeIterator.hasNext()) {
			MeleeAbilityEntity m = enemyMeleeIterator.next();
			if (m.destroyed) {
				enemyMeleeIterator.remove();
			}

		}
		
		getTime(delta);
		checkEvent();

	}
	
	void getTime(int delta){
		time += delta;
		//System.out.println(time);
		
	}
	
	void checkEvent(){
		eventIterator = eventList.iterator();
		while (eventIterator.hasNext()) {
			Event e = eventIterator.next();
			if (time >= e.activateTime) {
				e.fire();
				eventIterator.remove();
			}

		}
		
	}

	void render(GameContainer container, Graphics g) {
		for (Enemy i : enemyList) {
			i.render(container, g);
		}
		for (Projectile p : playerProjectiles) {
			p.render(container, g);
		}
		for (Projectile k : enemyProjectiles) {
			k.render(container, g);
		}

		for (int j = 0; j < 5; j++) {
			if (!walls[j].destroyed) {
				walls[j].render(container, g);
			}
		}

		for (int i = 0; i < playerCount; i++) {
			if (!players[i].destroyed) {
				players[i].render(container, g);
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
