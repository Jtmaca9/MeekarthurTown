package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuGameSelect extends BasicGameState {
	StateBasedGame game;
	
	int buttonSelected = 0, buttonCount = 4;
	ButtonMenu[] buttons;
	
	Image campaignButton;
	Image campaignButtonSelected;
	Image customButton;
	Image customButtonSelected;
	Image survivalButton;
	Image survivalButtonSelected;
	Image backButton;
	Image backButtonSelected;
	

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		loadImages();
		buttons = new ButtonMenu[buttonCount];
		buttons[0] = new ButtonMenu(campaignButton, campaignButtonSelected, 960 - 142, 400, 5);
		buttons[1] = new ButtonMenu(customButton, customButtonSelected,  960 - 142, 500, 2);
		buttons[2] = new ButtonMenu(survivalButton, survivalButtonSelected,  960 - 142, 600, 5);
		buttons[3] = new ButtonMenu(backButton, backButtonSelected,  960 - 142, 700, 3);
		buttons[buttonSelected].selected = true;
	
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
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
			campaignButton = new Image("Images/GUI/Button_Campaign.png");
			campaignButtonSelected = new Image("Images/GUI/Button_Campaign_Selected.png");
			customButton = new Image("Images/GUI/Button_Custom.png");
			customButtonSelected = new Image("Images/GUI/Button_Custom_Selected.png");
			survivalButton = new Image("Images/GUI/Button_Survival.png");
			survivalButtonSelected = new Image("Images/GUI/Button_Survival_Selected.png");
			backButton = new Image("Images/GUI/Button_Back.png");
			backButtonSelected = new Image("Images/GUI/Button_Back_Selected.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public int getID() {
		
		return 5;
	}

}
