package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Player {
	private float x,y,velX,velY;
	private Shape bounds;
	private float boost,trail,trailUpg,weapon,brake;
	private double ang = 0;
	private Boolean fill = false;
	private Color color;
	private float brakeTotal = 300;
	private boolean brakeFill = false;
	private Image bike;
	
	
	
	public Player()
	{
		bounds = new Rectangle(x,y,32,8);
	}
	
	public Player(int x,int y)
	{
		this.x = x;
		this.y = y;
		bounds = new Rectangle(x,y,32,8);
		setBoost(0);
		
		setTrail(100/(boost+1));
	}
	
	public Player(int x,int y, double angle)
	{
		
		this.x = x;
		this.y = y;
		bounds = new Rectangle(x,y,32,8);
		
		setTrail(100/(boost+1));
	}

	
	public void move(double angle)
	{
		
		ang = angle;
		setVelX((3+boost-brake)*(float) Math.cos(angle));
		setVelY((3+boost-brake)*(float) Math.sin(angle));
		bounds = new Rectangle(x,y,32,8);
		bounds =  bounds.transform(Transform.createRotateTransform((float) ang,x,y));
	}
	
	
	
	public void update()
	{
		
		x+=velX;
		y+=velY;
		if(getTrail() <= 0)
		{
			fill = true;
		}
		if(getTrail() >= 100)
		{
			setTrail(100);
			fill = false;
		}
		if(fill)
		{
			setTrail(getTrail()+1+trailUpg);
		}
		bounds = new Rectangle(x,y,32,8);
		
		if(getBrakeTotal()<=0)
		{
			brakeFill = true;
		}
		if(getBrakeTotal()>=300)
		{
			brakeFill = false;
		}
		if(brakeFill)
		{
			brakeTotal+= 0.3f;
		}
	}
	
	public void render(Graphics g,Image i)
	{
		bike = i;
		
	
		
		//bike.setRotation((float) ang);
		//bike.draw(x, y+8, x+32, y, bounds.getX(), bounds.getY(), bounds.getMinX(), bounds.getMinY());
		
		
	
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

	public double getAng() {
		return ang;
	}

	public void setAng(double ang) {
		this.ang = ang;
	}

	public Boolean getFill() {
		return fill;
	}

	public void setFill(Boolean fill) {
		this.fill = fill;
	}
	public Shape getBounds() {
		return bounds;
	}
	public void setBounds(Shape bounds) {
		this.bounds = bounds;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getBoost() {
		return boost;
	}

	public void setBoost(float boost) {
		this.boost = boost;
	}



	public float getTrailUpg() {
		return trailUpg;
	}

	public void setTrailUpg(float trailUpg) {
		this.trailUpg = trailUpg;
	}

	public float getWeapon() {
		return weapon;
	}

	public void setWeapon(float weapon) {
		this.weapon = weapon;
	}

	public float getBrake() {
		return brake;
	}

	public void setBrake(float brake) {
		this.brake = brake;
	}

	public float getBrakeTotal() {
		return brakeTotal;
	}

	public void setBrakeTotal(float brakeTotal) {
		this.brakeTotal = brakeTotal;
	}

	public boolean isBrakeFill() {
		return brakeFill;
	}

	public void setBrakeFill(boolean brakeFill) {
		this.brakeFill = brakeFill;
	}

	
}
