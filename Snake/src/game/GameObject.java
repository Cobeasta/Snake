package game;

import java.awt.Graphics;
public abstract class GameObject {
	private  int x, y;
	private int radius;
public GameObject(int x, int y) {
	this.x = x;
	this.y = y;
	
}
public abstract void draw(Graphics g);
public int getX() {
	return x;
}
public int getY() {
	return y;
}
public int getRadius() {
	return radius;
}
public void setRadius(int i) {
	radius = i;
}

public double distance(GameObject o) {
	double dx = o.getX() - this.getX();
	double dy = o.getY() - this.getY();
	dx = dx * dx;
	dy = dy * dy;
	return Math.sqrt(dx + dy);
}


}
