package Game;

public class WeaponUpg extends PowerUp{
	
	int weaponUp;
	
	public WeaponUpg(int x,int y, int n)
	{
		super(x,y);
		weaponUp = n;
		setType("weapon");
		}
	
	public int getVelChange()
	{
		return weaponUp;
	}


}
