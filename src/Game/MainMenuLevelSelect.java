package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuLevelSelect extends BasicGameState {
StateBasedGame game;
	

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
	
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
	
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException {
		
		
	}
	
	public void controllerButtonPressed(int controller, int button){
			if (button == 5) { //up
			
		}

		if (button == 7) {//down
			
			
		}

	}
	
	public void keyPressed(int key, char code){
		
	}

	@Override
	public int getID() {
		
		return 4;
	}

}
