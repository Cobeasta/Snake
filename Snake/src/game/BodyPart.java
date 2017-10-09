package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class BodyPart extends GameObject{
public BodyPart(int x, int y) {
	super(x, y);
}

public void update() {
	if(CenterPanel.getKSize() < 15) {
	setRadius(15);	
	}
	else if(CenterPanel.getKSize() < 20) {
		setRadius(20);	

	}else if(CenterPanel.getKSize() < 25) {
		setRadius(25);	

	}
	else if(CenterPanel.getKSize() < 30) {
		setRadius(30);	

	}
	else if (CenterPanel.getKSize() < 35) {
		setRadius(35);	

	}
	else if(CenterPanel.getKSize() < 40) {
		setRadius(40);	

	}
	else if (CenterPanel.getKSize() < 50) {
		setRadius(45);	

	}
	else {
		setRadius(50);	

	}
}
public void draw(Graphics g) {
	g.setColor(Color.GREEN);

	g.fillOval((int) getX(), (int) getY(), 15, 15);
}

}
