package Game;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Light {
	
	Image lightImage;
	Vector2f position = new Vector2f();
	
	public Light(float x, float y){
		position.x = x;
		position.y = y;
		try {
			lightImage = new Image("Data/light.png");
			lightImage = lightImage.getScaledCopy(32, 32);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	

}
