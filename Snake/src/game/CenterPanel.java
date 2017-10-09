package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CenterPanel extends JPanel implements Runnable{
	private static float kSpeed;
	private static int kSize;
	
	
	
	private static final int width = 600;
	private static final int height = 600;
	
	
	
	
	private ArrayList<BodyPart> snake;
	private ArrayList<Fruit> fruitArray;
	Thread t;
	private Fruit fruitToRemove;
	
	private int x, y;
	private float speed;
	
	private int xPos, yPos;
	public static boolean running;
	public int score;
	private int snakeSize;
	private int toRemove;
	
	
	
	
	
	/**\
	 * Constructor that starts thread, sets up panel and 
	 * initializes some game details
	 */
public CenterPanel() {
	toRemove = 0;
	running = true;

	xPos = 0; yPos = 0;
	x = 1;
	y = 1;
	fruitToRemove = null;
	speed = 7;
	snakeSize = 10;
	snake = new ArrayList<BodyPart>();
	fruitArray = new ArrayList<Fruit>();
	snake.add(new BodyPart(0, 0));
	
	setPreferredSize(new Dimension(600, 600));
	this.addKeyListener(new Key());
	setFocusable(true);
	System.out.println("Starting");
	t = new Thread(this);
	t.start();
	
}
/**
 * Main game loop. Updates, repaints, then sleeps for .5 seconds.
 */
@Override
public void run() {
	while(running == true) {
		
		update();
		repaint();	
	try {
		Thread.sleep(100);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}


/**
 * Paints all components when repaint() is called. 
 */
@Override
public void paint(Graphics g) {
	//Draw grid on screen, draw grid, snake and fruit.
	g.setColor(Color.GRAY);
	g.fillRect(0, 0, width, height);
	g.setColor(Color.WHITE);
	g.drawRect(0, 0, width, height);
	//drawGrid(g);
	drawSnake(g);
	drawFruit(g);
}
/**
 * Draw a grid with 10 pixel spacing on screen.
 * @param g
 */
public void drawGrid(Graphics g) {
	for(int i = 0; i < 600; i+= 10) {
		for(int temp = 0; temp < 600; temp+= 10) {
			g.drawLine(i, temp, i, height);
			g.drawLine(i, temp, width, temp);
		}
		
		
	}
}
/**
 * Draws the fruit on the screen
 * @param g
 */
public void drawFruit(Graphics g) {
	for(Fruit f : fruitArray) {
		f.draw(g);
	}
}
public void drawSnake(Graphics g) {
	
	

	for(BodyPart p: snake) {
		p.draw(g);
	}
}
/**
 * Main tick method. This updates each of the components. Called each time the loop runs.
 */
public void update() {
	/**
	 * FIXME This did not work!. References same Snake part!!
	 */
boolean die = checkForSelfCollisions();
updateSnake();

if(die != true) {
	checkForCollisions();

	updateFruit();
	if(snake.size() > snakeSize) {
		snake.remove(0);
		}
	for(BodyPart b:snake) {
		b.update();
	}
	
	if(fruitToRemove != null) {
		fruitArray.remove(fruitToRemove);
		fruitToRemove = null;
	}
}
else if(die == true) {
	System.out.println("You Lose!");
	System.exit(0);
}

}


public boolean checkForSelfCollisions() {
	
	for(BodyPart a: snake) {
		for(BodyPart b: snake) {
			if(!a.equals(b)) {
			if(a.distance(b) <  a.getRadius()/4) {
				return true;
			}
			}
		}
	}
	return false;
}

/**
 * Updates the snake location and removes back body part.
 */
public void updateSnake() {
	float distanceX, distanceY;
	
	distanceX = x * speed;
	distanceY = y * speed;
	xPos += distanceX;
	yPos += distanceY;
	snake.add(new BodyPart(xPos, yPos));
	
}
/**
 * Checks for collisions with fruit.
 */
private void checkForCollisions() {
	for(Fruit f: fruitArray) {
		for(BodyPart b: snake) {
		if(checkFruit(f, b)) {
			fruitToRemove = f;
			toRemove++;
			setSnakeSize(getSnakeSize() + 1);
			setScore(getScore() + 1);
			RightPanel.setScore(score);
			setSpeed((float) (getSpeed() + 0.5));
			RightPanel.setScore(score);
		}
		
		}
	}
}
/**
 * Checks for collisions for the given fruit and body part.
 * @param f Fruit to be compared to the BodyPart.
 * @param b BodyPart to be compared to the fruit for a collision.
 * @return
 */
private boolean checkFruit(Fruit f, BodyPart b) {
	int tolerance = b.getRadius();
	int xHigh, yHigh, xLow, yLow;
	xHigh = f.getX() + tolerance;
	yHigh = f.getY() + tolerance;
	xLow = f.getX() - tolerance;
	yLow = f.getY() - tolerance;
	if(b.getX() < xHigh && b.getX() > xLow) {
		if(b.getY() < yHigh && b.getY() > yLow) {
			return true;
		}
	}
	
return false;
}
/**
 * Checks how many fruit are on the screen.
 */
private void updateFruit() {
	if(fruitArray.size() < 10) {
		fruitArray.add(new Fruit(newFruit()));
		
	}
}
private Point newFruit() {
	Point location;
	int x; int y;
	x = (int) Math.rint((Math.random() * 600));
	y = (int) Math.rint(Math.random() * 600);
	location = new Point(x, y);
	return location;
	
}
public  float getSpeed() {
	
	return speed;
}
public void setSpeed(float f) {
	setStaticSpeed(f);
	speed = f;
}
public int getScore() {
	
	return score;
	
}
public void setScore(int i) {
	RightPanel.setScore(i);
	score = i;
}
public void setSnakeSize(int i) {
	snakeSize = i;
	setStaticSize(i);
}
public int getSnakeSize() {
	return snakeSize;
}
 











public static void setStaticSpeed(float i) {
	kSpeed = i;
}
public static void setStaticSize(int i) {
	kSize = i;
}

public static float getKSpeed() {
	return kSpeed;
}
public static int getKSize() {
	return kSize;
}
public class Key implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			if(y != 1) {
			y = -1;
			x =0;
			}
			break;
		case KeyEvent.VK_S:
			if(y != -1) {
			y = 1;
			x = 0;
			}
			break;
		case KeyEvent.VK_A:
			if(x != 1) {
			x = -1;
			y = 0;
			}
			break;
		case KeyEvent.VK_D:
			if(x != -1) {
			x = 1;
			y = 0;
			}
			break;
		default:
			break;			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}


}
