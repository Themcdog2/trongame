package Game;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

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
	private ArrayList<Trail> trails = new ArrayList<Trail>();
	private ArrayList<PowerUp> ups = new ArrayList<PowerUp>();
	private int redScore = 0;
	private int blueScore = 0;
	private Random r = new Random();
	
	public Game(String title) {
		super(title);
		
		
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
		//Setting up the Yung Map
		g.drawString(""+player1.getTrail(), 800, 0);
		g.setColor(Color.red);
		g.drawString("Red : "+redScore, 300, 8);
		g.fillRect(0, 0, player1.getTrail(), 32);
		g.setColor(Color.blue);
		g.drawString("Blue : "+blueScore, 400, 8);
		g.fillRect(150, 0, player2.getTrail(), 32);
		g.setColor(Color.white);
		g.drawRect(0, 0, 100, 32);
		g.drawRect(150, 0, 100, 32);
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
		
		for(int i = 0; i < ups.size(); i++)
		{
			g.setColor(Color.orange);
			g.fill(ups.get(i).getBounds());
		}
		
		for(int i = 0; i < trails.size(); i++)
		{
			trails.get(i).render(g);
		}
		
		if(keys.trail)
		{
			trail(player1,g);
		}
		

		if(keys.trail2)
		{
			trail(player2,g);
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
		player2 = new Player(232,cont.getHeight()-256);
		angle1 = 0;
		angle2 = 0;
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		powerUp();
		
		if(player1.getX()>1824 || player1.getX() < 64 || player1.getY() < 32 || player1.getY() > 1056)
		{
			init(arg0);
			trails.clear();
			blueScore++;
		}
		if(player2.getX()>1824 || player2.getX() < 64 || player2.getY() < 32 || player2.getY() > 1056)
		{
			init(arg0);
			trails.clear();
			redScore++;
		}
		
		for(int i = 0; i < trails.size(); i++)
		{
			trails.get(i).update();
			if(trails.get(i).getBounds().intersects(player1.getBounds()) && trails.get(i).isActive())
			{
				blueScore++;
				trails.clear();
				ups.clear();
				init(arg0);
			}
			else if(trails.get(i).getBounds().intersects(player2.getBounds()) && trails.get(i).isActive())
			{
				redScore++;
				trails.clear();
				ups.clear();
				init(arg0);
			}
			
		}
		
		for(int i = 0; i < ups.size(); i++)
		{
			if(ups.get(i).getBounds().intersects(player1.getBounds()))
			{
				
				switch(ups.get(i).getType())
				{
				case "speed":
						boostUp((SpeedUpg) ups.get(i),player1);
					break;
				case "trail":
					trailUp((TrailUpg) ups.get(i),player1);
				break;
				case "weapon":
					weaponUp((WeaponUpg) ups.get(i),player1);
					break;
				
				}
				ups.remove(i);
			}
			
			else if(ups.get(i).getBounds().intersects(player2.getBounds()))
			{
				
				switch(ups.get(i).getType())
				{
				case "speed":
						boostUp((SpeedUpg) ups.get(i),player2);
					break;
				case "trail":
					trailUp((TrailUpg) ups.get(i),player2);
					break;
				case "weapon":
					weaponUp((WeaponUpg) ups.get(i),player2);
					break;
				
				}
				ups.remove(i);
			}
			
		}
		
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
		 
		 app.setShowFPS(false);
		 
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
	
	public void trail(Player player,Graphics g)
	{
		
		
		if(player.getTrail() > 0 && player.getFill() == false)
		{
			
			if(player.getWeapon() == 0){
				trails.add(new Trail(player.getX(),player.getY(),player.getAng()));
			}
			else if(player.getWeapon() == 1)
			{
				trails.add(new Trail(player.getX()-8,player.getY()-8,player.getAng()));
				trails.add(new Trail(player.getX()+8,player.getY()+8,player.getAng()));
			}
			else if(player.getWeapon() == 2)
			{
				trails.add(new Trail(player.getX()-12,player.getY()-12,player.getAng()));
				trails.add(new Trail(player.getX(),player.getY(),player.getAng()));
				trails.add(new Trail(player.getX()+12,player.getY()+12,player.getAng()));
				
			}
			else if(player.getWeapon() >= 3)
			{
				trails.add(new Trail(player.getX()-16,player.getY()-12,player.getAng()));
				trails.add(new Trail(player.getX()-8,player.getY()+8,player.getAng()));
				trails.add(new Trail(player.getX()+8,player.getY()+8,player.getAng()));
				trails.add(new Trail(player.getX()+12,player.getY()+12,player.getAng()));
				
			}
				player.setTrail((float) (player.getTrail()-1.5+player.getTrailUpg()));
			
		}
	}
	 
	public void powerUp()
	{
		if(r.nextInt(1000) == 1)
		{
			
			switch(r.nextInt(3))
			{
			case 0:
				ups.add(new SpeedUpg(r.nextInt(1620)+100,r.nextInt(856)+100,1));
				break;
			case 1:
				ups.add(new TrailUpg(r.nextInt(1620)+100,r.nextInt(856)+100,.4f));
				break;
			case 2:
				ups.add(new WeaponUpg(r.nextInt(1620)+100,r.nextInt(856)+100,1));
				break;
				
				
			}
			
		}
	}
	
	public void boostUp(SpeedUpg upg, Player player)
	{
		player.setBoost(player.getBoost()+upg.velChange);
	}
	
	
	public void trailUp(TrailUpg upg, Player player)
	{
		player.setTrailUpg(player.getTrailUpg()+upg.trailChange);
	}
	
	public void weaponUp(WeaponUpg upg, Player player)
	{
		player.setWeapon(player.getWeapon()+upg.weaponUp);
	}
	
	

	

}
