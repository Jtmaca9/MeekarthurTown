package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LevelData {
	String name, description;
	int bronze, silver, gold, platinum;
	Image image;
	Scanner scanner;
	Highscore[] scores;
	
	LevelData(String n) throws SlickException{
		name = n;
		loadData();
		loadScores();
	}
	
	void loadData() throws SlickException{
		if (new File("Data/Levels/" + name + "/" + name + "Data.txt").isFile()){
				
				try {
					scanner = new Scanner(new File("Data/Levels/" + name + "/" + name + "Data.txt"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
				description = scanner.nextLine();
				bronze = Integer.parseInt(scanner.nextLine());
				silver = Integer.parseInt(scanner.nextLine());
				gold = Integer.parseInt(scanner.nextLine());
				platinum = Integer.parseInt(scanner.nextLine());
				
		}
		
		image = new Image("Data/Levels/" + name + "/" + name + ".png");
			
	}
	
	void loadScores(){
		if (new File("Data/Levels/" + name + "/" + name + "Scores.txt").isFile()){
				
				try {
					scanner = new Scanner(new File("Data/Levels/" + name + "/" + name + "Scores.txt"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				scores = new Highscore[5];
				int i = 0;
				
				while(scanner.hasNextLine()){
					scores[i++] = new Highscore(scanner.next(), Integer.parseInt(scanner.next()));
				}
		}
				
	}
	
	void render(GameContainer container, Graphics g){
		g.setColor(Color.lightGray);
		g.fillRect(500, 100, 1320, 880);
		g.setColor(Color.black);
		g.drawString(name, 550, 120);
		image.draw(520, 160, 512, 360);
		g.drawString("Bronze Score: " + bronze, 1060, 165);
		g.drawString("Silver Score: " + silver, 1060, 205);
		g.drawString("Gold Score: " + gold, 1060, 245);
		g.drawString("Platinum Score: " + platinum, 1060, 285);
		g.drawString("Description: " + description, 1060, 385);
		g.drawString("Scores: ", 520, 545);
		for (int i = 0; i < 5; i ++){
			g.drawString((i+1) + ". " + scores[i].name + "    " + scores[i].score , 525, 565 + ( i * 20));
		}
	}

}
