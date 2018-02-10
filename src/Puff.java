import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Puff extends JComponent implements ActionListener, Runnable, MouseListener
{
    private int widthOfScreen = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private int heightOfScreen = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    private JFrame mainGameWindow = new JFrame("NewGame");//Makes window with title "NewGame"
    private Rectangle2D.Double floor = new Rectangle2D.Double(0, 0, 100, 100);
    private Timer paintTicker = new Timer(20, this); //Ticks every 20 milliseconds (50 times per second); calls on actionPerformed() when it ticks.
    int x = 100;
    int y = 450;
    int apk = 1;
    public static BufferedImage puffer;
    public static BufferedImage enemy;
    public static BufferedImage background;
    public static BufferedImage menuu;
    public static BufferedImage upgrade;
    public static BufferedImage upgrade1;
    public static BufferedImage upgrade2;
    public static BufferedImage upgrade3;
    public static BufferedImage upgrade4;
    public static BufferedImage upgrade5;
    public static BufferedImage upgrade6;
    public static BufferedImage reset;
    int bar = 640;
    boolean attack = false;
    boolean die = false;
    Enemy crab = new Enemy(375,575,100,100);
    Player puff = new Player(1,1,1,1);
    int dead = 0;
    int menu = 0;
    int fall = 0;
    int sandDollars = 0;//set to 0! BONUS BONUS BONUS BONUS BONUS BONUS BONUS BONUS BONUS BONUS BONUS BONUS BONUS BONUS BONUS BONUS
    int i = 0;
    int up1cost = 10;
    double costPerAttack1 = up1cost / 1;
    int up2cost = 100;
    double costPerAttack2 = up2cost / 15;
    int up3cost = 1000;
    double costPerAttack3 = up3cost / 225;
    int up4cost = 10000;
    double costPerAttack4 = up4cost / 3375;
    int up5cost = 100000;
    double costPerAttack5 = up5cost / 50625;
    int up6cost = 1000000;
    double costPerAttack6 = up6cost / 759375;
    int bestUpgrade = 0;
    int enemyNum = new Random().nextInt(1) + 1;
    int topBarUnlocked = 0;
    
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Puff());
    }

    public void run(){
    	try {
			Scanner load = new Scanner(new File("puffData.rtf"));
			if(load.hasNextLine()){
				String load2 = (load.nextLine());
				System.out.println(load2);
				String[]stats = load2.split(",");
				System.out.println(load2);
				crab.hp = Integer.parseInt(stats[0]);
				up1cost = Integer.parseInt(stats[1]);
				up2cost = Integer.parseInt(stats[2]);
				up3cost = Integer.parseInt(stats[3]);
				up4cost = Integer.parseInt(stats[4]);
				up5cost = Integer.parseInt(stats[5]);
				up6cost = Integer.parseInt(stats[6]);
				crab.fullHp = Integer.parseInt(stats[7]);
				puff.attack = Integer.parseInt(stats[8]);
				puff.attackPercent = Integer.parseInt(stats[9]);
				sandDollars = Integer.parseInt(stats[10]);
				enemyNum = Integer.parseInt(stats[11]);
				crab.level = Integer.parseInt(stats[12]);
				puff.xp = Integer.parseInt(stats[13]); 
				System.out.println(crab.hp);
				System.out.println(up1cost);
				System.out.println(up2cost);
				System.out.println(up3cost);
				System.out.println(up4cost);
				System.out.println(up5cost);
				System.out.println(up6cost);
				System.out.println(crab.fullHp);
				System.out.println(puff.attack);
				System.out.println(puff.attackPercent);
				System.out.println(sandDollars);
				System.out.println(enemyNum);
				System.out.println(crab.level);
				System.out.println(puff.xp);
			}
			load.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			puffer = ImageIO.read(this.getClass().getResourceAsStream("YellowPuff.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			upgrade = ImageIO.read(this.getClass().getResourceAsStream("UpgradeButton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			upgrade1 = ImageIO.read(this.getClass().getResourceAsStream("Upgrade1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			upgrade2 = ImageIO.read(this.getClass().getResourceAsStream("Upgrade2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			upgrade3 = ImageIO.read(this.getClass().getResourceAsStream("Upgrade3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			upgrade4 = ImageIO.read(this.getClass().getResourceAsStream("Upgrade4.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			upgrade5 = ImageIO.read(this.getClass().getResourceAsStream("Upgrade5.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			upgrade6 = ImageIO.read(this.getClass().getResourceAsStream("Upgrade6.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			reset = ImageIO.read(this.getClass().getResourceAsStream("ResetButton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
    		background = ImageIO.read(this.getClass().getResourceAsStream("SeaFloor.png"));	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
    		menuu = ImageIO.read(this.getClass().getResourceAsStream("UpgradeScreen.png"));	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	paintTicker.start();
        mainGameWindow.setTitle("Puff, The Journey of a Fish");
        mainGameWindow.setSize(640, 1136);
        mainGameWindow.add(this);//Adds the paint method
        mainGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainGameWindow.setVisible(true);
        mainGameWindow.addMouseListener(this);
        paintTicker.start();
    }

    public void paint(Graphics g){
    	/*if(puff.xp < 999999999){//comment out this if statement! comment out this if statement! comment out this if statement! comment out this if statement!
    		puff.xp = puff.xp + 99999999;
    	}*/
    	saveGame();
    	costPerAttack1 = (double)up1cost / 1.0;
    	costPerAttack2 = (double)up2cost / 15.0;
    	costPerAttack3 = (double)up3cost / 225.0;
    	costPerAttack4 = (double)up4cost / 3375.0;
    	costPerAttack5 = (double)up5cost / 50625.0;
    	costPerAttack6 = (double)up6cost / 759375.0;
    	if(costPerAttack1 <= costPerAttack2 && costPerAttack1 <= costPerAttack3 && costPerAttack1 <= costPerAttack4 && costPerAttack1 <= costPerAttack5 && costPerAttack1 <= costPerAttack6){
    		bestUpgrade = 1;
    	}else if(costPerAttack2 <= costPerAttack1 && costPerAttack2 <= costPerAttack3 && costPerAttack2 <= costPerAttack4 && costPerAttack2 <= costPerAttack5 && costPerAttack2 <= costPerAttack6){
    		bestUpgrade = 2;
    	}else if(costPerAttack3 <= costPerAttack1 && costPerAttack3 <= costPerAttack2 && costPerAttack3 <= costPerAttack4 && costPerAttack3 <= costPerAttack5 && costPerAttack3 <= costPerAttack6){
    		bestUpgrade = 3;
    	}else if(costPerAttack4 <= costPerAttack1 && costPerAttack4 <= costPerAttack2 && costPerAttack4 <= costPerAttack3 && costPerAttack4 <= costPerAttack5 && costPerAttack4 <= costPerAttack6){
    		bestUpgrade = 4;
    	}else if(costPerAttack5 <= costPerAttack1 && costPerAttack5 <= costPerAttack2 && costPerAttack5 <= costPerAttack3 && costPerAttack5 <= costPerAttack4 && costPerAttack5 <= costPerAttack6){
    		bestUpgrade = 5;
    	}else if(costPerAttack6 <= costPerAttack1 && costPerAttack6 <= costPerAttack2 && costPerAttack6 <= costPerAttack3 && costPerAttack6 <= costPerAttack4 && costPerAttack6 <= costPerAttack5){
    		bestUpgrade = 6;
    	}
    	if(menu % 2 == 1){
    		topBarUnlocked = 1;
    		try {
    			upgrade = ImageIO.read(this.getClass().getResourceAsStream("BackToGame.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		topBarUnlocked = 1;
    		g.drawImage(background, 0,0, null);
    		xpBar(puff.xp,puff.nextLv, g);
    		g.drawImage(upgrade, 500, 25, null);
    		g.drawImage(reset, 500, 120, null);//96x95..500,120-596,215
    		g.setFont(new Font("Arial", Font.BOLD, 80));
    		g.setColor(new Color(0,0,0));
    		g.drawString("XP:" + puff.xp, 0, 112);
    		g.drawImage(upgrade1, 40, 150, null);//dimensions: 140,88... ends 180,238
    		g.drawString(up1cost + "xp", 180, 228);
    		g.drawImage(upgrade2, 40, 248, null);//dimensions: 140,88... ends 180,336... starts 40,248
    		g.drawString(up2cost + "xp", 180, 326);
    		g.drawImage(upgrade3, 40, 346, null);//dimensions: 140,88... ends 180,434... starts 40,346
    		g.drawString(up3cost + "xp", 180, 424);
    		g.drawImage(upgrade4, 40, 444, null);//dimensions: 140,88... ends 180,532... starts 40,444
    		g.drawString(up4cost + "xp", 180, 522);
    		g.drawImage(upgrade5, 40, 542, null);//dimensions: 140,88... ends 180,630... starts 40,542
    		g.drawString(up5cost + "xp", 180, 620);
    		g.drawImage(upgrade6, 40, 640, null);//dimensions: 140,88... ends 180,728... starts 40,640
    		g.drawString(up6cost + "xp", 180, 718);
    		g.setFont(new Font("Arial", Font.BOLD, 50));
    		g.drawString("Attack modifier:" + puff.attackPercent + "%", 38, 788);
    		if(bestUpgrade != 0){
    			g.setColor(new Color(0,255,128));
    			g.fillOval(10, 98 * bestUpgrade + 52, 30, 30);
    		}
    	}else{
    		try {
    			upgrade = ImageIO.read(this.getClass().getResourceAsStream("UpgradeButton.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		g.drawImage(background, -750,-400, null);
    		if(attack == true){
    			if(dead == 0){
    				if(x < 350){
    					x = x + 75;
    					y = y + 30;
    				}	
    			}
    			if(x >= 350){
    				attack = false;
    			}
    		}
    		if(attack == false){
    			if(x > 100){
    				x = x - 75;
    				y = y - 30;
    			}
    		} 
    		if(dead == 1 && crab.y < 576){
    			crab.x = crab.x + 10;
    			crab.y = crab.y + fall;
    			fall = fall + 1;
    			i = i + 1;
    		}else{
    			if(crab.level % 100 == 0){
    				if(enemyNum == 1){
    					try {
    						enemy = ImageIO.read(this.getClass().getResourceAsStream("SandDollar1.png"));
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}else if(enemyNum == 2){
    					try {
    						enemy = ImageIO.read(this.getClass().getResourceAsStream("elemental blobfish.png"));
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}else if(enemyNum == 3){
    					try {
    						enemy = ImageIO.read(this.getClass().getResourceAsStream("square Fish.png"));
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}else if(enemyNum == 4){
    					try {
    						enemy = ImageIO.read(this.getClass().getResourceAsStream("fish.png"));
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}else if(enemyNum == 5){
    					try {
    						enemy = ImageIO.read(this.getClass().getResourceAsStream("colorful fish.png"));
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}
    			}else{
    				if(enemyNum == 1){
    					try {
    						if(crab.level > 220){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("Horseshoe crab.png"));
    						}else if(crab.level > 120 && crab.level < 221){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("puncho.png"));
    						}else if(crab.level > 20 && crab.level < 121){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("Crab2.png"));
    						}else{
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("Crab.png"));
    						}
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}else if(enemyNum == 2){
    					try {
    						if(crab.level > 240){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("needlefish.png"));
    						}else if(crab.level > 140 && crab.level < 241){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("stingray.png"));
    						}else if(crab.level > 40 && crab.level < 141){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("Jellyfish2.png"));
    						}else{
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("Jellyfish.png"));
    						}
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}else if(enemyNum == 3){
    					try {
    						if(crab.level > 260){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("blobfish.png"));
    						}else if(crab.level > 160 && crab.level < 261){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("school of goldfish.png"));
    						}else if(crab.level > 60 && crab.level < 161){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("Sunfish.png"));
    						}else{
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("ForeheadFish.png"));
    						}
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}else if(enemyNum == 4){
    					try {
    						if(crab.level > 280){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("GoldDragon.png"));
    						}else if(crab.level > 180 && crab.level < 281){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("SeaDragon.png"));
    						}else if(crab.level > 80 && crab.level < 181){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("DarkHorse.png"));
    						}else{
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("SeaHorse.png"));
    						}
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}else{
    					try {
    						if(crab.level > 300){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("CrypticCat.png"));
    						}else if(crab.level > 200 && crab.level < 301){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("BigCat.png"));
    						}else if(crab.level > 100 && crab.level < 201){
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("OceanCat.png"));
    						}else{
    							enemy = ImageIO.read(this.getClass().getResourceAsStream("FuzzyCat.png"));
    						}
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}
    			}
    			
    			if(crab.x > 375){
    				crab.x = crab.x - 5;
    				crab.y = 575;
    				dead = 2;
    			}else{
    				crab.x = 375;
    				crab.y = 575;
    				dead = 0;
    			}
    			
    		}
    		g.setColor(new Color(255,216,0));
    		g.drawImage(puffer, x, y, null);
    		//g.fillRect(x,y,100,100); //character
    		g.setColor(Color.red);
    		//crab.draw(g);
    		g.drawImage(enemy, crab.x,crab.y, null);
    		//  Graphics2D g2 = (Graphics2D)g;
    		//  g2.setColor(Color.BLUE);
    		// g2.fill(floor);
    		//  g2.setColor(Color.red);
    		// g2.draw(floor);
    		if(crab.hp <= 0){
    			if(crab.level % 100 == 0){
    				puff.xp = puff.xp + (apk * 1000);
    				sandDollars = sandDollars + 1;
    			}else{
    				puff.xp = puff.xp + apk;
    			}
    			crab.update();
    			dead = 1;
    			fall = -20;
    			enemyNum = new Random().nextInt(5) + 1;
    			puff.update();
    			apk = crab.level;
    		}
    		
    		hpBar((int)crab.hp,(int)crab.fullHp,crab.level, g);
    		xpBar(puff.xp,puff.nextLv, g);
    	}
    }
    void hpBar(int health, int fullHealth, int level,Graphics g){
    	g.setColor(new Color(0,0,0));
    	g.fillRect(0,986,640,100);	
    	g.setFont(new Font("Arial", Font.BOLD, 100));
    	g.drawString("Level " + level, 0, 986);
    	double doop = (640 / fullHealth) * health;
    	int doopdoop = (int)doop;
    	double red = (fullHealth * (255 / fullHealth)) - (health * (255 / fullHealth));
    	int r = (int)red;
    	double green = health * (255 / fullHealth);
    	int gr = (int)green;
    	for(;r < 254 && gr < 254;){
    		r = r + 1;
    		gr = gr + 1;
    	}
    	g.setColor(new Color(r,gr,0));
    	if(health == fullHealth){
    		g.fillRect(0,986,640,100);	
    	}else{
    		g.fillRect(0,986,doopdoop,100);	
    	}
    }
    void xpBar(int exp, int lvUp, Graphics g){
    	g.setColor(new Color(0,0,0));
    	g.fillRect(0,0,(640),150);
    	g.setColor(new Color(255,216,0));
    	if(exp == lvUp || topBarUnlocked == 1){
    		g.fillRect(0,0,640,150);	
    	}else{
    		g.fillRect(0,0,(640 / lvUp) * exp,150);	
    	}
    	g.setColor(new Color(0,0,0));
    	g.fillOval( 217,14 * 3, 5, 5);
    	g.fillOval( 36,38 * 3,5, 5);
    	g.fillOval(183, 10 * 3, 5, 5);
    	g.fillOval(234,33 * 3,5, 5);
    	g.fillOval( 442,40 * 3, 5, 5);
    	g.fillOval( 410,36 * 3, 5, 5);
    	g.fillOval( 165,43 * 3, 5, 5);
    	g.fillOval( 568,39 * 3, 5, 5);
    	g.fillOval( 109,28 * 3, 5, 5);
    	g.fillOval( 142,23 * 3, 5, 5);
    	g.fillOval( 169,20 * 3, 5, 5);
    	g.fillOval( 182, 32 * 3, 5, 5);
    	g.fillOval( 60,16 * 3, 5, 5);
    	g.fillOval( 346,15 * 3, 5, 5);
    	g.fillOval( 254, 12 * 3, 5, 5);
    	g.fillOval( 405,43 * 3, 5, 5);
    	g.fillOval( 115, 1 * 3, 5, 5);
    	g.fillOval( 513,44 * 3, 5, 5);
    	g.fillOval( 530,29 * 3, 5, 5);
    	g.fillOval( 622,19 * 3, 5, 5);
    	//g.setColor(new Color(255,0,0));
    	//g.fillOval( 593,115, 5, 5); //for place testing/guessing
    	if(puff.upgradeTime == 1){
    		g.setFont(new Font("Arial", Font.BOLD, 80));
    		g.drawString("XP:" + exp, 0, 112);
    		g.drawImage(upgrade, 500, 25, null);//593,115 is end position
    	}

    }
    void saveGame(){
    	String save = crab.hp + "," + up1cost + "," + up2cost + "," + up3cost + "," + up4cost + "," + up5cost + "," + up6cost + "," + crab.fullHp + "," + puff.attack + "," + puff.attackPercent + "," + sandDollars + "," + enemyNum + "," + crab.level + "," + puff.xp;
    	try {
			PrintWriter safe = new PrintWriter(new File("puffData.rtf"));
			safe.println(save);
			safe.close();
			//safe.print(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    }
   
    @Override
    public void actionPerformed(ActionEvent e)
    {
        repaint(); //Calls on the paint() method.
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		//System.out.println(costPerAttack1);
    	//System.out.println(costPerAttack2);
    	//System.out.println(costPerAttack3);
    	//System.out.println(costPerAttack4);
    	//System.out.println(costPerAttack5);
    	//System.out.println(costPerAttack6);
		if(menu % 2 == 1 && arg0.getX() > 40 && arg0.getX() < 180 && arg0.getY() > 180 && arg0.getY() < 238){
			if(puff.xp >= up1cost){
				puff.attack = puff.attack + 1;
				puff.xp = puff.xp - up1cost;
				up1cost = up1cost + ((up1cost / 10) +1);
			}
		}else if(menu % 2 == 1 && arg0.getX() > 40 && arg0.getX() < 180 && arg0.getY() > 248 && arg0.getY() < 336){
			if(puff.xp >= up2cost){
				puff.attack = puff.attack + 15;
				puff.xp = puff.xp - up2cost;
				up2cost = up2cost + ((up2cost / 10) +1);
			}
		}else if(menu % 2 == 1 && arg0.getX() > 40 && arg0.getX() < 180 && arg0.getY() > 346 && arg0.getY() < 446){
			if(puff.xp >= up3cost){
				puff.attack = puff.attack + 225;
				puff.xp = puff.xp - up3cost;
				up3cost = up3cost + ((up3cost / 10) +1);
			}
		}else if(menu % 2 == 1 && arg0.getX() > 40 && arg0.getX() < 180 && arg0.getY() > 444 && arg0.getY() < 532){
			if(puff.xp >= up4cost){
				puff.attack = puff.attack + 3375;
				puff.xp = puff.xp - up4cost;
				up4cost = up4cost + ((up4cost / 10) +1);
			}
		}else if(menu % 2 == 1 && arg0.getX() > 40 && arg0.getX() < 180 && arg0.getY() > 542 && arg0.getY() < 630){
			if(puff.xp >= up5cost){
				puff.attack = puff.attack + 50625;
				puff.xp = puff.xp - up5cost;
				up5cost = up5cost + ((up5cost / 10) +1);
			}
		}else if(menu % 2 == 1 && arg0.getX() > 40 && arg0.getX() < 180 && arg0.getY() > 640 && arg0.getY() < 728){
			if(puff.xp >= up6cost){
				puff.attack = puff.attack + 759375;
				puff.xp = puff.xp - up6cost;
				up6cost = up6cost + ((up6cost / 10) +1);
			}
		}else if(menu % 2 == 1 && arg0.getX() > 500 && arg0.getX() < 596 && arg0.getY() > 120 && arg0.getY() < 215){
			puff.attackPercent = puff.attackPercent + (sandDollars * 10);
			menu = menu + 1;
			crab.level = 1;
			crab.fullHp = 10;
			crab.hp = 10;
			sandDollars = 0;
			puff.xp = 0;
			up1cost = 10;
		    up2cost = 100;
		    up3cost = 1000;
		    up4cost = 10000;
		    up5cost = 100000;
		    up6cost = 1000000;
		    puff.attack = 1;
		}else if(arg0.getX() > 500 && arg0.getX() < 593 && arg0.getY() > 25 && arg0.getY() < 115){
			menu = menu + 1;
		}else{
			if(dead == 0){
				crab.hp = crab.hp - (puff.attack * (puff.attackPercent / 100));
				System.out.println(crab.hp + "HP");
				die = true;
			}
		}
		saveGame();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getX() > 500 && arg0.getX() < 593 && arg0.getY() > 25 && arg0.getY() < 115){
			
		}else{
			attack = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
if(arg0.getX() > 500 && arg0.getX() < 593 && arg0.getY() > 25 && arg0.getY() < 115){
			
		}else{
			attack = false;
		}
	}
}