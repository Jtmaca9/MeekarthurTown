package Game;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {

	final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, NOMOVE = 4;

	static Image archer;
	static Image wizard;
	static Image knight;
	static Image rogue;
	static Image cleric;
	
	static Image archerIcon;
	static Image wizardIcon;
	static Image knightIcon;
	static Image rogueIcon;
	static Image clericIcon;
	
	static Image wizardAbility0;
	static Image wizardAbility1;
	static Image knightAbility0;
	static Image knightAbility1;
	static Image archerAbility0;
	static Image archerAbility1;
	static Image rogueAbility0;
	static Image clericAbility0;
	
	static Image StandardMeleeImage;
	static Image BerserkerMeleeImage;
	static Image StandardRangedImage;
	static Image StandardFlyingImage;
	static Image boss;
	static Image boss2;
	static Image meleeIndicator;
	static Image blueProjectile;
	static Image redProjectile;
	static Image wallFullImage;
	static Image wallHalfImage;
	static Image healthImage;
	static Image projectileWater; 
	static Image projectileFire; 
	static Image projectileIce;
	static Image projectileArrows;
	static Image projectilePoison;
	static Image projectileAxe;
	static Image projectileDagger;
	static Image projectilePoisonArrow; 
	static Image blood;
	
	private Music music;
	
	StateBasedGame game;

	static Level currLevel;
	static String levelName;

	public static int playerCount;

	static int[] pControls;
	static String[] pClass;
	
	static int[] con;
	
	public Game(String title) {
		super();
		

		

	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		loadImages();
		music.setVolume(0.1f);
		music.loop();
		//newGame();
		

	}
	
	static void newGame(){
		playerCount = MainMenuPlayerSelect.playerCount;
		pControls = new int[MainMenuPlayerSelect.playerCount];
		pClass = new String[MainMenuPlayerSelect.playerCount];
		levelName = MainMenuPlayerSelect.levelName;
		for(int i = 0; i < playerCount; i++){
			pControls[i] = MainMenuPlayerSelect.pControls[i];
			pClass[i] = MainMenuPlayerSelect.pClass[i];
		}
		con = new int[10];
		for(int j = 0; j < playerCount; j++){
			con[pControls[j]] = j;
		}
		currLevel = new Level(playerCount, levelName);
		
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
		currLevel.render(container, g);
		g.setColor(Color.green);
		//g.drawString("Current time: " + (currLevel.time / 1000), 20, 20);
		//g.drawString("Current lives: " + (currLevel.lives), 20, 40);
	}

	@Override
	public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException {
		currLevel.update(delta);

		if (currLevel.checkLives()) {
			game.enterState(1);
		}
	}

	void loadImages() {
		try {
			boss = new Image("Images/boss.png");
			boss2 = new Image("Images/boss2.png");
			blood = new Image("Images/blood.png");
			wizard = new Image("Images/Wizard.png");
			archer = new Image("Images/Archer.png");
			knight = new Image("Images/Knight.png");
			rogue = new Image("Images/Rogue.png");
			cleric = new Image("Images/Cleric.png");
			wizardIcon = new Image("Images/Class_Wizard.png");
			archerIcon = new Image("Images/Class_Archer.png");
			knightIcon = new Image("Images/Class_Knight.png");
			rogueIcon = new Image("Images/Class_Rogue.png");
			clericIcon = new Image("Images/Cleric.png");
			wizardAbility0 = new Image("Images/Wizard_Ability0.png");
			wizardAbility1 = new Image("Images/Wizard_Ability1.png");
			knightAbility0 = new Image("Images/Knight_Ability0.png");
			knightAbility1 = new Image("Images/Knight_Ability1.png");
			archerAbility0 = new Image("Images/Archer_Ability0.png");
			archerAbility1 = new Image("Images/Archer_Ability1.png");
			rogueAbility0 = new Image("Images/Rogue_Ability0.png");
			clericAbility0 = new Image("Images/Cleric_Ability0.png");
			StandardFlyingImage = new Image("Images/FlyingStandard.png");
			StandardMeleeImage = new Image("Images/Goblin.png");
			BerserkerMeleeImage = new Image("Images/GoblinB.png");
			StandardRangedImage = new Image("Images/GoblinA.png");
			meleeIndicator = new Image("Images/MeleeIndicator.png");
			blueProjectile = new Image("Images/BlueOrb.png");
			redProjectile = new Image("Images/RedOrb.png");
			projectileWater = new Image("Images/Projectile_Water.png");
			projectileFire = new Image("Images/Projectile_Fire.png");
			projectileIce = new Image("Images/Projectile_Ice.png");
			projectileArrows = new Image("Images/Projectile_Arrows.png");
			projectilePoison = new Image("Images/Projectile_Poison.png");
			projectileDagger = new Image("Images/Projectile_Dagger.png");
			projectilePoisonArrow = new Image("Images/Projectile_PoisonArrow.png");
			projectileAxe = new Image("Images/Projectile_Axe.png");
			wallFullImage = new Image("Images/Wall_Full.png");
			wallHalfImage = new Image("Images/Wall_Half.png");
			healthImage = new Image("Images/Health.png");
			
			music = new Music("Sounds/Journey.ogg");
			
		} catch (SlickException e) {

			e.printStackTrace();
		}

	}

	@Override
	public int getID() {
		return 0;
	}
	
	public void controllerButtonPressed(int controller, int button){
		System.out.println(controller + " : " + button);
		if (button == 5) {
			currLevel.players[con[controller]].moveUp(true);
		}

		if (button == 8) {
			currLevel.players[con[controller]].moveLeft(true);
		}

		if (button == 7) {
			currLevel.players[con[controller]].moveDown(true);
		}

		if (button == 6) {
			currLevel.players[con[controller]].moveRight(true);
		}

		if (button == 13) {
			currLevel.players[con[controller]].useAbility(UP);
		}

		if (button == 16) {
			currLevel.players[con[controller]].useAbility(LEFT);
		}

		if (button == 15) {
			currLevel.players[con[controller]].useAbility(DOWN);
		}

		if (button == 14) {
			currLevel.players[con[controller]].useAbility(RIGHT);
		}

		if (button == 11) {
			currLevel.players[con[controller]].primeAbility(1);
		}

		if (button == 12) {
			currLevel.players[con[controller]].primeAbility(2);
		}
	
		//start 4
		//up 5
		//right 6
		//down 7
		//left 8
		//x 15
		//0 14
		//∆ 13
		//sq 16
		//r1 12
		//l1 11
	}
	
	public void controllerButtonReleased(int controller, int button){
		if (button == 5) {
			currLevel.players[con[controller]].moveUp(false);
		}

		if (button == 8) {
			currLevel.players[con[controller]].moveLeft(false);
		}

		if (button == 7) {
			currLevel.players[con[controller]].moveDown(false);
		}

		if (button == 6) {
			currLevel.players[con[controller]].moveRight(false);
		}

	
		//start 4
		//up 5
		//right 6
		//down 7
		//left 8
		//x 15
		//0 14
		//∆ 13
		//sq 16
		//r1 12
		//l1 11
	}

	public void keyPressed(int key, char code) {
		// Player 1 keyboard Controls
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