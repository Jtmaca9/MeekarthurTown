package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuPlayerSelect extends BasicGameState{
	
	static int playerCount = 0;
	static int[] pControls;
	static String[] pClass;
	static String levelName;
	static String[] classList;
	int classCount;
	static int[] con;
	static int[] pClassIn;
	boolean keyboard;
	StateBasedGame game;
	

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		pControls = new int[4];
		pClassIn = new int[4];
		pClass = new String[4];
		con = new int[10];
		for(int i = 0; i < 4; i++){
			pClassIn[i] = 0;
		}
		classList = new String[5];
		classCount = 5;
		classList[0] = "Wizard";
		classList[1] = "Archer";
		classList[2] = "Rogue";
		classList[3] = "Cleric";
		classList[4] = "Knight";
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("Press Start or Enter to join the game!", 500, 500);
		g.drawString("Players = " + playerCount, 500, 550);
		g.drawString("Press space to continue.", 500, 600);
		
		for(int i = 0; i < playerCount; i++){
			g.drawString("Player " + (i+1) + " Class: " + pClass[i], 250 + (i * 300), 700);
		}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException {
		
		
	}
	
	public void controllerButtonPressed(int controller, int button){
		if(button == 4){
			boolean add = true;
			for (int i = 0; i < playerCount; i++){
				if(controller == pControls[i]){
					add = false;
				}
			}
			if(add){
				pControls[playerCount] = controller;
				pClass[playerCount] = classList[pClassIn[playerCount]];
				playerCount++;
				
				for(int j = 0; j < playerCount; j++){
					con[pControls[j]] = j;
				}
			}
			
			
		}
		
		if (button == 5) {
			if(pClass[con[controller]] != classList[classCount-1]){
				for(int i = 0; i < classCount-1; i ++){
					if(pClass[con[controller]] == classList[i]){
						pClass[con[controller]] = classList[i+1];
						break;
					}
				}
			}else{
				pClass[con[controller]] = classList[0];
			}
			
		}

		if (button == 7) {
			
			if(pClass[con[controller]] != classList[0]){
				for(int i = classCount-1; i >= 0; i --){
					if(pClass[con[controller]] == classList[i]){
						pClass[con[controller]] = classList[i-1];
						break;
					}
				}
			}else{
				pClass[con[controller]] = classList[classCount-1];
			}
			
		}
		
		if (button == 15){
			if (playerCount > 0) {
				game.enterState(0);
				Game.newGame();
			}
		}

	}
	
	public void keyPressed(int key, char code){
		if(key == Input.KEY_ENTER && playerCount == 0){
			pControls[playerCount] = 5;
			pClass[playerCount] = classList[pClassIn[0]];
			playerCount++;
			
			for(int j = 0; j < playerCount; j++){
				con[pControls[j]] = j;
			}
		}else if (key == Input.KEY_SPACE){
			if (playerCount > 0) {
				game.enterState(0);
				Game.newGame();
			}
		}
		
		if (key == Input.KEY_UP || key == Input.KEY_W) {
			if(pClass[0] != classList[classCount-1]){
				for(int i = 0; i < classCount-1; i ++){
					if(pClass[0] == classList[i]){
						pClass[0] = classList[i+1];
						break;
					}
				}
			}else{
				pClass[0] = classList[0];
			}
		}
		
		if (key == Input.KEY_DOWN || key == Input.KEY_S) {
			if(pClass[0] != classList[0]){
				for(int i = classCount-1; i >= 0; i --){
					if(pClass[0] == classList[i]){
						pClass[0] = classList[i-1];
						break;
					}
				}
			}else{
				pClass[0] = classList[classCount-1];
			}
		}
		
	}

	@Override
	public int getID() {
		
		return 2;
	}

}
