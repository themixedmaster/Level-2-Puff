
public class Player extends GameObject{
	Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	int attack = 1;
	int abilitytimer = 600000;
	int attackPercent = 100;
	int xp = 0; //set to 0! $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	int xpGain = 10;
	int xpGainMultiplier = 1;
	int nextLv = 10;
	int upgradePoints = 1;
	int upgradeTime = 0;
	public static void main(String[] args) {
		
	}
	void update(){
		if(xp >= nextLv){
			upgradeTime = 1;
		}
	}
}
