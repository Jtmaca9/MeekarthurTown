package Game;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Setup extends StateBasedGame {

	public Setup(String name) {
		super(name);

	}

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int screenWidth = (int) screenSize.getWidth();
	static int screenHeight = (int) screenSize.getHeight();
	static final int GAMEWIDTH = 1920;
	static final int GAMEHEIGHT = 1080;

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new ScalableGame(new Setup("Game"), GAMEWIDTH, GAMEHEIGHT, true));
		app.setDisplayMode(screenWidth, screenHeight, true);
		app.setShowFPS(true);
		app.setAlwaysRender(true);
		app.setVSync(true);
		app.setTargetFrameRate(60);
		app.start();
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new Game("Game"));
		this.addState(new GameOver("GameOver"));

	}

}
