package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Trail {
	private double x,y,actTimer;
	private Shape bounds;
	private Boolean active = false;
	private Player player;
	
	public Trail()
	{
		
	}
	public Trail(double x, double y)
	{
		this.setX(x);
		this.setY(y);
		bounds = new Rectangle((float)x,(float) y,8,8);
	}
	public Trail(double x, double y, double angle)
	{
		this.player = player;
		this.setX(x);
		this.setY(y);
		bounds = new Rectangle((float)x,(float) y,8,8);
		bounds =  bounds.transform(Transform.createRotateTransform((float) angle,(float)x,(float) y));
	}
	public Trail(double x, double y, double angle,int w,int h)
	{
		this.player = player;
		this.setX(x);
		this.setY(y);
		bounds = new Rectangle((float)x,(float) y,w,h);
		bounds =  bounds.transform(Transform.createRotateTransform((float) angle,(float)x,(float) y));
	}
	
	
	public void render(Graphics g)
	{
		if(active)
		{
			g.setColor(Color.black);
		}
		else
		g.setColor(Color.white);
		g.fill(bounds);
	}
	
	public void update()
	{
		if(actTimer < 100)
		{
			actTimer++;
		}
		if(actTimer > 10)
		{
			active = true;
		}
	}
	public Shape getBounds() {
		return bounds;
	}
	public void setBounds(Shape bounds) {
		this.bounds = bounds;
	}
	public double getActTimer() {
		return actTimer;
	}
	public void setActTimer(double actTimer) {
		this.actTimer = actTimer;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public Boolean isActive() {
		return active;
	}
	public void setActive(boolean a) {
		active = a;
	}
	
}
