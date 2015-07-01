package Game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Setup extends StateBasedGame {

	public Setup(String name) {
		super(name);

	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Setup("Game"));
		app.setDisplayMode(800, 600, false);
		app.setShowFPS(true);
		app.setAlwaysRender(true);
		app.setVSync(true);
		app.setTargetFrameRate(60);
		app.start();
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new Game("Game"));

	}

}
