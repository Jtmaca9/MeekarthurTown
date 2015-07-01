package Game;

import java.util.List;
import java.util.ArrayList;

public class Level {
	
	Wall[] walls;
	Player[] players;
	int playerCount;
	String levelName;
	
	//Player List and Enemy list and Event list
	List<LivingEntity> livingEntityList = new ArrayList<LivingEntity>();
	
	Level(int pCount, String lName){
		players = new Player[pCount];
		walls = new Wall[5];
		playerCount = pCount;
		levelName = lName;
		
		//testing area
		players[0] = new Player(1, 20, 20, "wizard");
		
		
	}
	
	
	

}
