package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Trail {
	private float x,y;
	private Shape bounds;
	
	public Trail()
	{
		
	}
	public Trail(float x, float y)
	{
		this.x = x;
		this.y = y;
		bounds = new Rectangle(x,y,8,8);
	}
	
	
	public void render(Graphics g)
	{
		g.setColor(Color.black);
		g.fill(bounds);
	}

}
