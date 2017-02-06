package Game;

import org.lwjgl.input.Keyboard;

public class KeyHandler {
	
	Boolean trail2,left,right,up,down,left2,right2,up2,down2,trail, left3,right3,up3,down3,trail3 = false;
	
	public KeyHandler()
	{
		left = right = up = down = trail = trail2 = false;
	}
	
	public void update()
	{
		left = Keyboard.isKeyDown(Keyboard.KEY_A);
		right = Keyboard.isKeyDown(Keyboard.KEY_D);
		up = Keyboard.isKeyDown(Keyboard.KEY_W);
		trail = Keyboard.isKeyDown(Keyboard.KEY_S);
		
		
		left2 = Keyboard.isKeyDown(Keyboard.KEY_LEFT);
		right2 = Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
		up2 = Keyboard.isKeyDown(Keyboard.KEY_UP);
		trail2 = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
		
		left3 = Keyboard.isKeyDown(Keyboard.KEY_J);
		right3 = Keyboard.isKeyDown(Keyboard.KEY_L);
		up3 = Keyboard.isKeyDown(Keyboard.KEY_I);
		trail3 = Keyboard.isKeyDown(Keyboard.KEY_K);
		
		
		
	}

}
