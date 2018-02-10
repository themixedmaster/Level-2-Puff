import java.awt.Graphics;

public class Enemy extends GameObject {

	int level = 1;//set to 1! LevelLevelLevelLevelLevelLevelLevelLevelLevelLevelLevelLevelLevelLevelLevelLevelLevelLevel
	int fullHp = 10;
	int hp = 10;
	Enemy(int x,int y, int width, int height){
		super(x, y, width, height);
	}
	void draw(Graphics g){
		//g.fillRect(x,y,width,height);
	}
	void update(){
		if(hp <= 0){
			
			fullHp = (int)(fullHp * 1.1);
			hp = fullHp;
			level = level + 1;
			System.out.println("Enemies Defeated: " + (level - 1));
		}
	}
}
