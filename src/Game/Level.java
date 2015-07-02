package Game;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Iterator;

public class Level {

	Wall[] walls;
	Player[] players;
	int playerCount;
	String levelName;
	Coords[] lanes;

	// Player List and Enemy list and Event list
	List<LivingEntity> livingEntityList = new ArrayList<LivingEntity>();
	List<Enemy> enemyList = new ArrayList<Enemy>();
	List<Projectile> playerProjectiles = new ArrayList<Projectile>();
	List<Projectile> enemyProjectiles = new ArrayList<Projectile>();
	Iterator<Enemy> enemyIterator;
	Iterator<Projectile> projectileIterator;

	Level(int pCount, String lName) {
		players = new Player[pCount];
		walls = new Wall[5];
		playerCount = pCount;
		levelName = lName;
		lanes = new Coords[5];

		// testing area
		players[0] = new Player(1, 320, 320, "wizard");
		lanes[0] = new Coords(320, 160);
		
		for(int i = 0; i < 20; i++){
			enemyList.add(new MeleeEnemy(i * 50 +20, 0, 0, false));
		}

		


	}

	void update() {
		for (int i = 0; i < playerCount; i++) {
			players[i].update();
		}

		for (Enemy j : enemyList) {
			j.update();
		}
		
		for (Projectile p : playerProjectiles) {
			p.update();
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
		
	}

	void render(GameContainer container, Graphics g) {
		for (int i = 0; i < playerCount; i++) {
			players[i].render(container, g);
		}
		for (Enemy i : enemyList) {
			i.render(container, g);
		}
		for (Projectile p : playerProjectiles) {
			p.render(container, g);
		}
	}

}
