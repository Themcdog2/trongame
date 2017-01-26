package Game;

public class SizeUpg extends PowerUp {
int sizeChange;
	
	public SizeUpg(int x,int y, int n)
	{
		super(x,y);
		sizeChange = n;
		setType("size");
		}
	
	public int getSizeChange()
	{
		return sizeChange;
	}


}
