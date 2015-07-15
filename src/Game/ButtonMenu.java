package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ButtonMenu extends Button {

	int stateAddr;
	
	ButtonMenu(Image im, Image ims, int x, int y, int sa){
		image = im;
		imageSelected = ims;
		xpos = x;
		ypos = y;
		stateAddr = sa;
	}
	
	void render(GameContainer container, Graphics g){
		if(!selected){
			image.draw(xpos, ypos);
		}else{
			imageSelected.draw(xpos, ypos);
		}
	}
	

}
