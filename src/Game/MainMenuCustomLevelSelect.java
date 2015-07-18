package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuCustomLevelSelect extends BasicGameState {
	StateBasedGame game;
	
	int buttonSelected = 0;
	ButtonLevel[] buttons;
	Scanner scanner;
	int levelCount;
	String[] levels;
	
	Image b;
	Image bSelected;
	LevelData ld;

	private int pageNumber = 0;
	private int pageCount = 0;
	private int buttonCount;

	
	
	

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		loadImages();
		loadLevelData();
		createButtons();
		buttons[0].selected = true;
		ld = new LevelData(buttons[0].levelName);

	
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.white);
		for(int i = 0; i < 8; i++){
			if(buttons[i] != null){
				buttons[i].render(container, g);
				
			}
		}
		
		ld.render(container, g);
		
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
			
			try {
				ld = new LevelData(buttons[buttonSelected].levelName);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (button == 7) {//down
			if(buttonSelected < buttonCount - 1){
				buttonSelected++;
			}else{
				buttonSelected = 0;
			}
			try {
				ld = new LevelData(buttons[buttonSelected].levelName);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (button == 15) {//press (x)
			MainMenuPlayerSelect.levelName = buttons[buttonSelected].levelName;
			game.enterState(2);
			
		}
		if (button == 6) {//press (x)
			if(pageNumber < pageCount - 1){
				pageNumber++;
				createButtons();
				buttonSelected = 0;
			}
			try {
				ld = new LevelData(buttons[buttonSelected].levelName);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (button == 8) {//press (x)
			if(pageNumber > 0){
				pageNumber--;
				createButtons();
				buttonSelected = 0;
			}
			try {
				ld = new LevelData(buttons[buttonSelected].levelName);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(button == 14){
			game.enterState(5);
			
		}

	}
	
	public void keyPressed(int key, char code){
		if(key == Input.KEY_UP || key == Input.KEY_W){
			if(buttonSelected > 0){
				buttonSelected--;
			}else{
				buttonSelected = buttonCount - 1;
			}
			
			try {
				ld = new LevelData(buttons[buttonSelected].levelName);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(key == Input.KEY_DOWN || key == Input.KEY_S){
			if(buttonSelected < buttonCount - 1){
				buttonSelected++;
			}else{
				buttonSelected = 0;
			}
			try {
				ld = new LevelData(buttons[buttonSelected].levelName);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(key == Input.KEY_ENTER || key == Input.KEY_SPACE){
			MainMenuPlayerSelect.levelName = buttons[buttonSelected].levelName;
			game.enterState(2);
			
		}
		
		if(key == Input.KEY_BACK){
			game.enterState(5);
			
		}
		
		if (key == Input.KEY_RIGHT || key == Input.KEY_D) {//press (x)
			if(pageNumber < pageCount - 1){
				pageNumber++;
				createButtons();
				buttonSelected = 0;
			}
			try {
				ld = new LevelData(buttons[buttonSelected].levelName);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (key == Input.KEY_LEFT || key == Input.KEY_A) {//press (x)
			if(pageNumber > 0){
				pageNumber--;
				createButtons();
				buttonSelected = 0;
			}
			try {
				ld = new LevelData(buttons[buttonSelected].levelName);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	void loadImages(){
		try {
			b = new Image("Images/GUI/Button.png");
			bSelected = new Image("Images/GUI/Button_Selected.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void loadLevelData(){
	if (new File("Data/Levels/levelList.txt").isFile()){
			
			try {
				scanner = new Scanner(new File("Data/Levels/levelList.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int i = 0;
			
			
			while(scanner.hasNextLine()){
				if(i == 0){
					levelCount = Integer.parseInt(scanner.nextLine());
					levels = new String[levelCount];
					
					i++;
				}else{
					levels[i-1] = scanner.nextLine();
					i++;
				}
			}
			
		}
	
	pageCount = (levelCount/8);
	if((levelCount % 8) > 0){
		pageCount++;
	}
		
	}
	
	void createButtons(){
		buttonCount = 0;
		buttons = new ButtonLevel[8];
		for(int i = (pageNumber  * 8); i < ((pageNumber * 8)+ 8); i ++){
			if(i < levelCount && pageNumber > 0){
				buttons[i%(pageNumber *8)] = new ButtonLevel(b, bSelected,levels[i], 50, ((i%8) * 85) + 150, i);
				buttonCount++;
			}
			else if (i < levelCount){
				buttons[i] = new ButtonLevel(b, bSelected, levels[i], 50, ((i%8) * 85) + 150, i);
				buttonCount++;
			}
			
		}
		
	}
	
	


	@Override
	public int getID() {
		
		return 6;
	}

}
