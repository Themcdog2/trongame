package Game;

import org.lwjgl.input.Keyboard;

public class KeyHandler {
	
	Boolean trail2,left,right,up,down,left2,right2,up2,down2,trail = false;
	
	public KeyHandler()
	{
		left = right = up = down = trail = trail2 = false;
	}
	
	public void update()
	{
		left = Keyboard.isKeyDown(Keyboard.KEY_A);
		right = Keyboard.isKeyDown(Keyboard.KEY_D);
		up = Keyboard.isKeyDown(Keyboard.KEY_W);
		down = Keyboard.isKeyDown(Keyboard.KEY_S);
		trail = Keyboard.isKeyDown(Keyboard.KEY_T);
		
		left2 = Keyboard.isKeyDown(Keyboard.KEY_LEFT);
		right2 = Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
		up2 = Keyboard.isKeyDown(Keyboard.KEY_UP);
		down2 = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
		trail2 = Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5);
		
		
	}

}
