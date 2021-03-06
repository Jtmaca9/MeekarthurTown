package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState{
	
	StateBasedGame game;

	public GameOver(String string) {

	}

	@Override
	public void init(GameContainer arg0, StateBasedGame game) throws SlickException {
		this.game = game;
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawString("Game Over", 800, 500);
		g.drawString("Press Space to retry.", 800, 550);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		
	}public void keyPressed(int key, char code) {
		// Player 1 Controls
		if (key == Input.KEY_SPACE) {
			//Game.currLevel = new Level(Game.playerCount, "level1");
			Game.newGame();
			game.enterState(0);
			
		}
	}
	
	

	@Override
	public int getID() {

		return 1;
	}

}
