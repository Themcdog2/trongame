 package Game;

import org.newdawn.slick.geom.Rectangle;

public class PowerUp {
	
	private int x,y;
	private Rectangle bounds;
	private String type;
	
	public PowerUp()
	{
		
	}
	
	public PowerUp(int x,int y)
	{
		this.setX(x);
		this.setY(y);
		bounds = new Rectangle(x,y,32,32);
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	


}
