package Game;

public class SpeedUpg extends PowerUp{
	
	int velChange;
	
	public SpeedUpg(int x,int y, int n)
	{
		super(x,y);
		velChange = n;
		setType("speed");
		}
	
	public int getVelChange()
	{
		return velChange;
	}

}
