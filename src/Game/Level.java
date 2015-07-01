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
	Iterator<Enemy> enemyIterator;

	Level(int pCount, String lName) {
		players = new Player[pCount];
		walls = new Wall[5];
		playerCount = pCount;
		levelName = lName;
		lanes = new Coords[5];

		// testing area
		players[0] = new Player(1, 32, 32, "wizard");
		lanes[0] = new Coords(320, -32);
		enemyList.add(new MeleeEnemy(lanes[0], 0, false));

	}

	void update() {
		for (int i = 0; i < playerCount; i++) {
			players[i].update();
		}

		for (Enemy j : enemyList) {
			j.update();
		}

		// We will use the same iterator loop for destroyed projectiles.
		enemyIterator = enemyList.iterator();
		while (enemyIterator.hasNext()) {
			Enemy e = enemyIterator.next();
			if (e.destroyed) {
				enemyIterator.remove();
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
	}

}
