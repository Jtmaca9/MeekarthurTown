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
	
	final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, NOMOVE = 4;

	static Image player;
	static Image StandardMeleeImage;
	static Image BerserkerMeleeImage;
	static Image meleeIndicator;
	static Image projectile;
	static Image wallFullImage;
	static Image wallHalfImage;
	StateBasedGame game;

	static Level currLevel;

	public Game(String title) {
		super();

	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		loadImages();
		currLevel = new Level(1, "PENIS");

	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(0, 0, 1920, 1080);
		currLevel.render(container, g);
		g.setColor(Color.green);
		g.drawString("Current time: " +(currLevel.time/1000), 20, 20);
	}

	@Override
	public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException {
		currLevel.update(delta);
		
		if(currLevel.checkLives()){
			game.enterState(1);
		}
	}

	void loadImages() {
		try {
			player = new Image("Images/obama_sprite.png");
			StandardMeleeImage = new Image("Images/MeleeStandard.png");
			BerserkerMeleeImage = new Image("Images/MeleeBeserker.png");
			meleeIndicator = new Image("Images/MeleeIndicator.png");
			projectile = new Image("Images/Green.png");
			wallFullImage = new Image("Images/WallFull.png");
			wallHalfImage = new Image("Images/WallHalf.png");
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
		if (key == Input.KEY_W) {
			currLevel.players[0].moveUp(true);
		}

		if (key == Input.KEY_A) {
			currLevel.players[0].moveLeft(true);
		}

		if (key == Input.KEY_S) {
			currLevel.players[0].moveDown(true);
		}

		if (key == Input.KEY_D) {
			currLevel.players[0].moveRight(true);
		}
		
		if (key == Input.KEY_UP) {
			currLevel.players[0].useAbility(UP);
		}

		if (key == Input.KEY_LEFT) {
			currLevel.players[0].useAbility(LEFT);
		}

		if (key == Input.KEY_DOWN) {
			currLevel.players[0].useAbility(DOWN);
		}

		if (key == Input.KEY_RIGHT) {
			currLevel.players[0].useAbility(RIGHT);
		}
		
		if (key == Input.KEY_Q) {
			currLevel.players[0].primeAbility(1);
		}
		
		if (key == Input.KEY_E) {
			currLevel.players[0].primeAbility(2);
		}
	}

	public void keyReleased(int key, char code) {
		// Player 1 Controls
		if (key == Input.KEY_W) {
			currLevel.players[0].moveUp(false);
		}

		if (key == Input.KEY_A) {
			currLevel.players[0].moveLeft(false);
		}

		if (key == Input.KEY_S) {
			currLevel.players[0].moveDown(false);
		}

		if (key == Input.KEY_D) {
			currLevel.players[0].moveRight(false);
		}
	}

}
