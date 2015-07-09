package Game;

public class Event {
	
	int monsterID;
	boolean bigMonster;
	int activateTime;
	Coords lane;
	
	Event(int mid, boolean bm, Coords l, int at){
		monsterID = mid;
		bigMonster = bm;
		lane = l;
		activateTime = at;
	}
	
	void fire(){
		switch(monsterID){
		case 1:
			Game.currLevel.enemyList.add(new StandardMeleeEnemy(lane, bigMonster));
			break;
		case 2:
			Game.currLevel.enemyList.add(new BeserkerMeleeEnemy(lane, bigMonster));
			break;
		}
		
	}

}
