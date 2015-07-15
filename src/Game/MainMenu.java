package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState{
	
	StateBasedGame game;
	int buttonSelected = 0, buttonCount = 3;
	ButtonMenu[] buttons;
	Image startButton;
	Image startButtonSelected;
	Image optionsButton;
	Image optionsButtonSelected;
	Image quitButton;
	Image quitButtonSelected;
	Image menuTitle;
	

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		loadImages();
		buttons = new ButtonMenu[buttonCount];
		buttons[0] = new ButtonMenu(startButton, startButtonSelected, 960 - 142, 400, 5);
		buttons[1] = new ButtonMenu(optionsButton, optionsButtonSelected,  960 - 142, 500, 3);
		buttons[2] = new ButtonMenu(quitButton, quitButtonSelected,  960 - 142, 600, 3);
		buttons[buttonSelected].selected = true;
	
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
		menuTitle.draw(960 - 312, 75);
		for(int i = 0; i < buttonCount; i++){
			buttons[i].render(container, g);
		}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException {
		for(int i = 0; i < buttonCount; i++){
			if( i == buttonSelected){
				buttons[i].selected = true;
			}else{
				buttons[i].selected = false;
			}
		}
		
	}
	
	public void controllerButtonPressed(int controller, int button){
		if (button == 5) { //up
			if(buttonSelected > 0){
				buttonSelected--;
			}else{
				buttonSelected = buttonCount - 1;
			}
		}

		if (button == 7) {//down
			if(buttonSelected < buttonCount - 1){
				buttonSelected++;
			}else{
				buttonSelected = 0;
			}
		}
		
		if (button == 15) {//press (x)
			game.enterState(buttons[buttonSelected].stateAddr);
			
		}

	}
	
	public void keyPressed(int key, char code){
		if(key == Input.KEY_UP || key == Input.KEY_W){
			if(buttonSelected > 0){
				buttonSelected--;
			}else{
				buttonSelected = buttonCount - 1;
			}
		}
		if(key == Input.KEY_DOWN || key == Input.KEY_S){
			if(buttonSelected < buttonCount - 1){
				buttonSelected++;
			}else{
				buttonSelected = 0;
			}
		}
		if(key == Input.KEY_ENTER || key == Input.KEY_SPACE){
			game.enterState(buttons[buttonSelected].stateAddr);
		}
	}
	
	void loadImages(){
		try {
			startButton = new Image("Images/GUI/Button_Start.png");
			startButtonSelected = new Image("Images/GUI/Button_Start_Selected.png");
			optionsButton = new Image("Images/GUI/Button_Options.png");
			optionsButtonSelected = new Image("Images/GUI/Button_Options_Selected.png");
			quitButton = new Image("Images/GUI/Button_Quit.png");
			quitButtonSelected = new Image("Images/GUI/Button_Quit_Selected.png");
			menuTitle = new Image("Images/GUI/MenuTitle.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public int getID() {
		
		return 3;
	}

}
