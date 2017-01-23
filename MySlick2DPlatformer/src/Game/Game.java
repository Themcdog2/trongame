package Game;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame{
	
	private Color mapColor;
	private Player player1,player2;
	private KeyHandler keys = new KeyHandler();
	private double angle1,angle2;

	public Game(String title) {
		super(title);
		
		
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
		//Setting up the Yung Map
		for(int i = 0; i < 56; i++)
		{
			for(int j = 0; j < 32; j++)
			{
				g.setColor(mapColor);
				g.fillRect(i*32+64, j*32+32, 32, 32);
				g.setColor(Color.white);
				g.drawRect(i*32+64, j*32+32, 32, 32);
			}
		}
		
		//rendering players
		g.setColor(Color.red);
		player1.render(g);
		g.setColor(Color.blue);
		player2.render(g);
	}

	@Override
	public void init(GameContainer cont	) throws SlickException {
		// TODO Auto-generated method stub
		mapColor = new Color(150,235,228);
		player1 = new Player(232,232);
		player2 = new Player(232,264);
		angle1 = 0;
		angle2 = 0;
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		player1.update();
		player2.update();
		keys.update();
		
		//rotation checks
		if(keys.left)
		{
			angle1-=Math.PI/90;
		}
		if(keys.right)
		{
			angle1+=Math.PI/90;
		}
		if(keys.trail)
		{
			player1.trail();
		}
		if(keys.left2)
		{
			angle2-=Math.PI/90;
		}
		if(keys.right2)
		{
			angle2+=Math.PI/90;
		}
		player1.move(angle1);
		player2.move(angle2);
	}
	
	public static void main(String args[]) throws SlickException
	{
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		AppGameContainer app = new AppGameContainer(new Game("Yeet"));
		 
		 app.setDisplayMode((int)width, (int)height, true);
		 
		 app.setTargetFrameRate(60);
		 
		 app.start();
	}
	
	public Boolean isPushing(int x, int y, int width,int height)
	{
		if(Mouse.isButtonDown(0)&& Mouse.getX()>=x && Mouse.getX()<=x+width && Mouse.getY()>=1080-y-height && Mouse.getY()<=1080-y)
		{
			return true;
		}
		return false;
	}
	
	public Boolean isOver(int x, int y, int width,int height)
	{
		if(Mouse.getX()>=x && Mouse.getX()<=x+width && Mouse.getY()>=1080-y-height && Mouse.getY()<=1080-y)
		{
			return true;
		}
		return false;
	}
	
	public void trail(Player player ,Graphics g)
	{
		
		
		if(player.getTrail() == 100)
		{
			
					Timer t = new Timer();
			t.schedule(new TimerTask()	{

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("test");

					Trail trail = new Trail(x,y);
					trail.render(g);
				
				}},500,1);
			
		}
	}


}
