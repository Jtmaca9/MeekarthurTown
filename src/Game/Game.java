package Game;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int width = (int) screenSize.getWidth();
	static int height = (int) screenSize.getHeight();

	static Image player;
	static Image enemy;

	Level currLevel;

	public Game(String title) {
		super();

	}

	@Override
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		loadImages();
		currLevel = new Level(1, "PENIS");

	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
		for (int i = 0; i < currLevel.playerCount; i++) {
			currLevel.players[i].render(container, g);
		}

	}

	@Override
	public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException {

	}

	void loadImages() {
		try {
			player = new Image("Images/obama_sprite.png");
			enemy = new Image("Images/Character.png");
		} catch (SlickException e) {

			e.printStackTrace();
		}

	}

	@Override
	public int getID() {
		return 0;
	}

}
