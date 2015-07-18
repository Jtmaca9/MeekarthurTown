package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ButtonLevel extends Button {

	int levelID;
	String levelName;
	
	ButtonLevel(Image im, Image ims, String lName, int x, int y, int li){
		image = im;
		levelName = lName;
		imageSelected = ims;
		xpos = x;
		ypos = y;
		levelID = li;
	}
	
	void render(GameContainer container, Graphics g){
		if(!selected){
			image.draw(xpos, ypos);
			g.drawString(levelName, xpos +70, ypos +25);
		}else{
			imageSelected.draw(xpos, ypos);
			g.drawString(levelName, xpos +70, ypos + 25);
		}
	}
	

}
