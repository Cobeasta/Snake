package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Fruit extends GameObject{
	public Fruit(int x, int y){
		super(x, y);
		
}
	public Fruit(Point p) {
		super((int)p.getX(),(int) p.getY());
	}
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(getX(), getY(), 15, 15);
		
	}
	


}
