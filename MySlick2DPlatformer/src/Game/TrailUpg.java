package Game;

public class TrailUpg extends PowerUp{
	
float trailChange;
	
	public TrailUpg(int x,int y, float n)
	{
		super(x,y);
		trailChange = n;
		setType("trail");
		}
	
	public float getSizeChange()
	{
		return trailChange;
	}


}
