package Game;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Iterator;

public class Level {

	EntityWall[] walls;
	EntityPlayer[] players;
	int playerCount;
	int lives;
	int deltaTime;
	long time = 0;
	String levelName;
	Coords[] lanes;

	// Player List and Enemy list and Event list
	List<EntityLiving> livingEntityList = new ArrayList<EntityLiving>();
	List<Enemy> enemyList = new ArrayList<Enemy>();
	List<Event> eventList = new ArrayList<Event>();
	List<EntityProjectile> playerProjectiles = new ArrayList<EntityProjectile>();
	List<EntityProjectile> enemyProjectiles = new ArrayList<EntityProjectile>();
	List<EntityAbilityMelee> enemyMeleeList = new ArrayList<EntityAbilityMelee>();
	Iterator<EntityAbilityMelee> enemyMeleeIterator;
	Iterator<Enemy> enemyIterator;
	Iterator<Event> eventIterator;
	Iterator<EntityProjectile> projectileIterator;

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
		players[0] = new EntityPlayer(1, 320, 320, "wizard");

		eventList.add(new Event(2, false, lanes[0], 1000));
		eventList.add(new Event(2, false, lanes[1], 2000));
		eventList.add(new Event(1, false, lanes[2], 3000));
		eventList.add(new Event(1, false, lanes[3], 4000));
		eventList.add(new Event(1, false, lanes[4], 5000));

		eventList.add(new Event(1, true, lanes[4], 7000));
		eventList.add(new Event(1, true, lanes[3], 8000));
		eventList.add(new Event(1, true, lanes[2], 9000));
		eventList.add(new Event(1, true, lanes[1], 10000));
		eventList.add(new Event(1, true, lanes[0], 11000));

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
		for (Enemy i : enemyList) {
			i.render(container, g);
		}
		for (EntityAbilityMelee m : enemyMeleeList) {
			m.render(container, g);
		}
		for (EntityProjectile p : playerProjectiles) {
			p.render(container, g);
		}
		for (EntityProjectile k : enemyProjectiles) {
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
		
		projectileIterator = playerProjectiles.iterator();
		while (projectileIterator.hasNext()) {
			EntityProjectile p = projectileIterator.next();
			if (p.destroyed) {
				projectileIterator.remove();
			}

		}

		projectileIterator = enemyProjectiles.iterator();
		while (projectileIterator.hasNext()) {
			EntityProjectile p = projectileIterator.next();
			if (p.destroyed) {
				projectileIterator.remove();
			}

		}

		enemyMeleeIterator = enemyMeleeList.iterator();
		while (enemyMeleeIterator.hasNext()) {
			EntityAbilityMelee m = enemyMeleeIterator.next();
			if (m.destroyed) {
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
