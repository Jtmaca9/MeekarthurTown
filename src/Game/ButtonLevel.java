package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ButtonLevel extends Button {

	int levelID;
	
	ButtonLevel(Image im, Image ims, int x, int y, int li){
		image = im;
		imageSelected = ims;
		xpos = x;
		ypos = y;
		levelID = li;
	}
	
	void render(GameContainer container, Graphics g){
		if(!selected){
			image.draw(xpos, ypos);
		}else{
			imageSelected.draw(xpos, ypos);
		}
	}
	

}
