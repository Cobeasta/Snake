package game;

import java.awt.*;

import javax.swing.*;

public class Driver extends JFrame{
	JPanel c;
public Driver() {
	System.out.println("Beginning");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(new Dimension(900, 600));
	c = (JPanel) getContentPane();
	setLocation(new Point(0, 0));
	c.setLayout(new BorderLayout(2, 2));
	setResizable(false);
	
}
public void init() {
	System.out.println("init");
	JPanel center = new CenterPanel();
	JPanel right = new RightPanel();
	
	c.setBackground(Color.BLACK);
	c.add(center, BorderLayout.CENTER);
	c.add(right, BorderLayout.EAST);
	
	setVisible(true);
}
public void start() {
	
}
	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.init();
	}

}
