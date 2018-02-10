import java.awt.Graphics;
import java.awt.Rectangle;
public class GameObject {
	Rectangle collisionBox;
	boolean isAlive = true;
	int x;
	int y;
	int width;
	int height;
	GameObject(int x,int y,int width,int height){
		collisionBox = new Rectangle();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	void update(){
		collisionBox.setBounds(x, y, width, height);
				
	}
	void draw(Graphics g){
	}

}

