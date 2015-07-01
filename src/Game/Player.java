package Game;

public class Player extends LivingEntity {

	String playerClass;
	int playerID;

	Player(int pID, int x, int y, String pClass) {
		enemy = false;
		playerID = pID;
		playerClass = pClass;
		width = 32;
		height = 32;
		xpos = x;
		ypos = y;
		image = Game.player;
		championCreate();
	}

	void championCreate() {

		switch (playerClass) {

		case "wizard":
			health = 75;
			speed = 4;
			break;

		default:
			break;
		}
	}

}
