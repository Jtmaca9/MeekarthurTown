package Game;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {

	static Image player;
	static Image enemyImage;

	static Level currLevel;

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
		currLevel.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException {
		currLevel.update();
	}

	void loadImages() {
		try {
			player = new Image("Images/obama_sprite.png");
			enemyImage = new Image("Images/Character.png");
		} catch (SlickException e) {

			e.printStackTrace();
		}

	}

	@Override
	public int getID() {
		return 0;
	}

	public void keyPressed(int key, char code) {
		// Player 1 Controls
		if (key == Input.KEY_UP) {
			currLevel.players[0].moveUp(true);
		}

		if (key == Input.KEY_LEFT) {
			currLevel.players[0].moveLeft(true);
		}

		if (key == Input.KEY_DOWN) {
			currLevel.players[0].moveDown(true);
		}

		if (key == Input.KEY_RIGHT) {
			currLevel.players[0].moveRight(true);
		}
	}

	public void keyReleased(int key, char code) {
		// Player 1 Controls
		if (key == Input.KEY_UP) {
			currLevel.players[0].moveUp(false);
		}

		if (key == Input.KEY_LEFT) {
			currLevel.players[0].moveLeft(false);
		}

		if (key == Input.KEY_DOWN) {
			currLevel.players[0].moveDown(false);
		}

		if (key == Input.KEY_RIGHT) {
			currLevel.players[0].moveRight(false);
		}
	}

}
