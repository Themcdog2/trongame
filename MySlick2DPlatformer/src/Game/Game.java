package Game;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

public class Game extends BasicGame{
	
	private Color mapColor;
	private Player player1,player2,player3;
	private KeyHandler keys = new KeyHandler();
	private double angle1,angle2,angle3;
	private ArrayList<Trail> trails = new ArrayList<Trail>();
	private ArrayList<PowerUp> ups = new ArrayList<PowerUp>();
	private int redScore = 0;
	private int blueScore = 0;
	private int greenScore = 0;
	private Random r = new Random();
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int width =  (int)screenSize.getWidth();
	static int height = (int)screenSize.getHeight();
	public static Image runner1,runner2,runner3;
	
	GameStates gs = GameStates.menu;
	
	
	public Color lightColor = new Color(255,255,255,255);
	private static Image lightImage;
	private static BufferedImage bufferedImageLighting;
	static Texture lightTex;
	Graphics gameGraphics;
	java.awt.Graphics lightGraphics;
	ArrayList<Light> lightList = new ArrayList<Light>();
	
	
	public Game(String title) {
		super(title);
		
		
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {
		// TODO Auto-generated method stub  
		
		
		
		//Setting up the Yung Map
		
		switch(gs)
		{
		case menu:
			
			
			
			if(isOver(175, 485, 125, 50))
			{
				
				g.drawString("2-player", 200, 500);
				g.drawRect(175, 485, 125, 50);
				g.setColor(Color.cyan);
				g.drawRect(176, 486, 123, 48);
				g.setColor(Color.white);
			}
			else
			{
				g.drawString("2-player", 200, 500);
				g.drawRect(175, 485, 125, 50);
			}
			
			if(isOver(475, 485, 125, 50))
			{
				
				g.drawString("3-player", 500, 500);
				g.drawRect(475, 485, 125, 50);
				g.setColor(Color.cyan);
				g.drawRect(476, 486, 123, 48);
				g.setColor(Color.white);
			}
			else
			{
				g.drawString("3-player", 500, 500);
				g.drawRect(475, 485, 125, 50);
			}
			
			
			
			
			
			break;
		case twoPlayer:

		//g.drawString(""+player1.getBrakeTotal(), 800, 0);
		g.setColor(Color.red);
		g.drawString("Red : "+redScore, 1100, 8);
		g.fillRect(0, 0, player1.getTrail(), 32);
		g.setColor(Color.blue);
		g.drawString("Blue : "+blueScore, 1300, 8);
		g.fillRect(150, 0, player2.getTrail(), 32);
		g.setColor(Color.orange);
		g.fillRect(300,  0,  player1.getBrakeTotal(), 32);
		g.setColor(Color.cyan);
		g.fillRect(750,  0, player2.getBrakeTotal(), 32);
		g.setColor(Color.white);
		g.drawRect(0, 0, 100, 32);
		g.drawRect(150, 0, 100, 32);
		g.drawRect(300, 0, 300, 32);
		g.drawRect(750, 0, 300, 32);
		
		
		
		
		//Taking away grid for now
		
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

		g.setDrawMode(Graphics.MODE_ADD);
		g.drawImage(lightList.get(0).lightImage, 0, 0);
		g.setDrawMode(Graphics.MODE_NORMAL);
		g.setColor(Color.red);
		player1.render(g,runner1);
		
		g.setColor(Color.blue);
		player2.render(g,runner2);
		
		break;
		case threePlayer :
			
			g.setColor(Color.red);
			g.drawString("Red : "+redScore, 1100, 8);
			g.fillRect(0, 0, player1.getTrail(), 32);
			g.setColor(Color.blue);
			g.drawString("Blue : "+blueScore, 1300, 8);
			g.fillRect(150, 0, player2.getTrail(), 32);
			g.setColor(new Color(0,100,0));
			g.drawString("Green : "+greenScore, 1500, 8);
			g.fillRect(300, 0, player3.getTrail(), 32);
			
			g.setColor(new Color(265,100,100));
			g.fillRect(450,  0,  player1.getBrakeTotal()/2, 32);
			g.setColor(Color.cyan);
			g.fillRect(650,  0, player2.getBrakeTotal()/2, 32);
			g.setColor(new Color(0,255,0));
			g.fillRect(850,  0,  player3.getBrakeTotal()/2, 32);
			
			
			g.setColor(Color.white);
			g.drawRect(0, 0, 100, 32);
			g.drawRect(150, 0, 100, 32);
			g.drawRect(300, 0, 100, 32);
			g.drawRect(450, 0, 150, 32);
			g.drawRect(650, 0, 150, 32);
			g.drawRect(850, 0, 150, 32);
			
			
			
			
			//Taking away grid for now
			
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
			
			if(keys.trail3)
			{
				trail(player3,g);
			}
			
			//rendering players

			g.setDrawMode(Graphics.MODE_ADD);
			g.drawImage(lightList.get(0).lightImage, 0, 0);
			g.setDrawMode(Graphics.MODE_NORMAL);
			g.setColor(Color.red);
			player1.render(g,runner1);
			
			g.setColor(Color.blue);
			player2.render(g,runner2);
			
			g.setColor(new Color(0,100,0));
			player3.render(g,runner3);
		}
		
	}

	
	
	@Override
	public void init(GameContainer cont	) throws SlickException {
		// TODO Auto-generated method stub
		mapColor = new Color(150,235,228);// old color
		//mapColor = new Color(0, 0, 0);
		player1 = new Player(232,232);
		player2 = new Player(232,cont.getHeight()-256);
		
			player3 = new Player(232,(232+cont.getHeight()-256)/2);
		
		angle1 = 0;
		angle2 = 0;
		angle3 = 0;
		
		
		
		
		
		bufferedImageLighting = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
		lightGraphics = bufferedImageLighting.getGraphics();
		
		try {
			lightTex = BufferedImageUtil.getTexture("dummy", bufferedImageLighting);
		} catch (IOException e) {
			e.printStackTrace();
		}
		lightImage = new Image(lightTex);
		runner1 = new Image("Data/LightRunner.png");
		runner2 = new Image("Data/LightRunner.png");
		runner3 = new Image("Data/LightRunner.png");
        
        lightList.add(new Light(player1.getX(),player1.getY()));
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
		switch(gs)
		{
		case menu:
			
			if(isPushing(175, 485, 125, 50))
			{
				gs = GameStates.twoPlayer;
			}
			
			if(isPushing(475, 485, 125, 50))
			{
				gs = GameStates.threePlayer;
			}
			
			break;
		case twoPlayer:
		powerUp();
		
		if(player1.getX()>1856 || player1.getX() < 64 || player1.getY() < 32 || player1.getY() > 1056)
		{
			init(arg0);
			trails.clear();
			blueScore++;
		}
		if(player2.getX()>1856 || player2.getX() < 64 || player2.getY() < 32 || player2.getY() > 1056)
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
		
		lightList.get(0).position = new Vector2f(player1.getX(), player1.getY());
		keys.update();
		
		
		
		//rotation checks
		if(keys.left)
		{
			angle1 = angle1% 360;
			angle1-=Math.PI/90;
			//runner1.rotate((float) angle1);
		}
		if(keys.right)
		{
			angle1 = angle1% 360;
			angle1+=Math.PI/90;
			//runner1.rotate((float) angle1);
		}
		if(keys.up&&!player1.isBrakeFill())
		{
			player1.setBrake(1);
			player1.setBrakeTotal(player1.getBrakeTotal()-1);
		}
		else
			player1.setBrake(0);
		
		if(keys.left2)
		{
			angle2 = angle2% 360;
			angle2-=Math.PI/90;
			//runner2.rotate((float) angle2);
		}
		if(keys.right2)
		{
			angle2 = angle2% 360;
			angle2+=Math.PI/90;
			//runner2.rotate((float) angle2);
		}
		if(keys.up2&&!player2.isBrakeFill())
		{
			player2.setBrake(1);
			player2.setBrakeTotal(player2.getBrakeTotal()-1);
			
		}
		else
			player2.setBrake(0);
		player1.move(angle1);
		player2.move(angle2);
		break;
		case threePlayer:
			
			powerUp();
			
			if(player1.getX()>1856 || player1.getX() < 64 || player1.getY() < 32 || player1.getY() > 1056)
			{
				init(arg0);
				trails.clear();
				blueScore++;
				greenScore++;
			}
			if(player2.getX()>1856 || player2.getX() < 64 || player2.getY() < 32 || player2.getY() > 1056)
			{
				init(arg0);
				trails.clear();
				redScore++;
				greenScore++;
			}
			
			if(player3.getX()>1856 || player3.getX() < 64 || player3.getY() < 32 || player3.getY() > 1056)
			{
				init(arg0);
				trails.clear();
				redScore++;
				blueScore++;
			}
			
			for(int i = 0; i < trails.size(); i++)
			{
				trails.get(i).update();
				if(trails.get(i).getBounds().intersects(player1.getBounds()) && trails.get(i).isActive())
				{
					blueScore++;
					greenScore++;
					trails.clear();
					ups.clear();
					init(arg0);
				}
				else if(trails.get(i).getBounds().intersects(player2.getBounds()) && trails.get(i).isActive())
				{
					redScore++;
					greenScore++;
					trails.clear();
					ups.clear();
					init(arg0);
				}
				else if(trails.get(i).getBounds().intersects(player3.getBounds()) && trails.get(i).isActive())
				{
					redScore++;
					blueScore++;
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
				else if(ups.get(i).getBounds().intersects(player3.getBounds()))
				{
					
					switch(ups.get(i).getType())
					{
					case "speed":
							boostUp((SpeedUpg) ups.get(i),player3);
						break;
					case "trail":
						trailUp((TrailUpg) ups.get(i),player3);
						break;
					case "weapon":
						weaponUp((WeaponUpg) ups.get(i),player3);
						break;
					
					}
					ups.remove(i);
				}
				
			}
			
			player1.update();
			player2.update();
			player3.update();
			lightList.get(0).position = new Vector2f(player1.getX(), player1.getY());
			keys.update();
			
			
			
			//rotation checks
			if(keys.left)
			{
				angle1 = angle1% 360;
				angle1-=Math.PI/90;
				//runner1.rotate((float) angle1);
			}
			if(keys.right)
			{
				angle1 = angle1% 360;
				angle1+=Math.PI/90;
				//runner1.rotate((float) angle1);
			}
			if(keys.up&&!player1.isBrakeFill())
			{
				player1.setBrake(1);
				player1.setBrakeTotal(player1.getBrakeTotal()-1);
			}
			else
				player1.setBrake(0);
			
			if(keys.left2)
			{
				angle2 = angle2% 360;
				angle2-=Math.PI/90;
				//runner2.rotate((float) angle2);
			}
			if(keys.right2)
			{
				angle2 = angle2% 360;
				angle2+=Math.PI/90;
				//runner2.rotate((float) angle2);
			}
			if(keys.up2&&!player2.isBrakeFill())
			{
				player2.setBrake(1);
				player2.setBrakeTotal(player2.getBrakeTotal()-1);
				
			}
			else
				player2.setBrake(0);
			
			if(keys.left3)
			{
				angle3 = angle3% 360;
				angle3-=Math.PI/90;
				//runner3.rotate((float) angle3);
			}
			if(keys.right3)
			{
				angle3 = angle3% 360;
				angle3+=Math.PI/90;
				//runner3.rotate((float) angle3);
			}
			if(keys.up3&&!player3.isBrakeFill())
			{
				player3.setBrake(1);
				player3.setBrakeTotal(player3.getBrakeTotal()-1);
				
			}
			else
				player3.setBrake(0);
			player1.move(angle1);
			player2.move(angle2);
			player3.move(angle3);
			
			break;
		}
	}
	
	public static void main(String args[]) throws SlickException
	{
		
		
		
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
