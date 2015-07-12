package Game;

public class Event {

	int monsterID;
	boolean bigMonster;
	int activateTime;
	Coords lane;

	Event(int mid, boolean bm, Coords l, int at) {
		monsterID = mid;
		bigMonster = bm;
		lane = l;
		activateTime = at;
	}

	void fire() {
		switch (monsterID) {
		case 1:
			Game.currLevel.enemyList.add(new EnemyMeleeStandard(lane, bigMonster));
			break;
		case 2:
			Game.currLevel.enemyList.add(new EnemyMeleeBeserker(lane, bigMonster));
			break;
		case 4:
			Game.currLevel.enemyList.add(new EnemyRangedStandard(lane, bigMonster));
			break;
		case 7:
			Game.currLevel.enemyList.add(new EnemyFlyingStandard(lane, bigMonster));
			break;
		}

	}

}
