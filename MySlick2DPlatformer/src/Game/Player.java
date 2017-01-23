package Game;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Player {
	private float x,y,velX,velY;
	private Shape bounds;
	private float boost,trail;
	
	
	
	public Player()
	{
		bounds = new Rectangle(x,y,32,8);
	}
	
	public Player(int x,int y)
	{
		this.x = x;
		this.y = y;
		bounds = new Rectangle(x,y,32,8);
		boost = 100;
		setTrail(100);
	}

	
	public void move(double angle)
	{
		setVelX(3*(float) Math.cos(angle));
		setVelY(3*(float) Math.sin(angle));
		bounds = new Rectangle(x,y,32,8);
		bounds =  bounds.transform(Transform.createRotateTransform((float) angle,x,y));
	}
	
	
	
	public void update()
	{
		x+=velX;
		y+=velY;
		if(getTrail() < 100)
		{
			setTrail(getTrail() + 1);
		}
	}
	
	public void render(Graphics g)
	{
		
		g.fill(bounds);
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public float getTrail() {
		return trail;
	}

	public void setTrail(float trail) {
		this.trail = trail;
	}
}
