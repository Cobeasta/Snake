package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

public class RightPanel extends JPanel implements Runnable {
	Thread t;
	public static int score;
	private int kScore;
	JLabel label;
	JLabel scoreLabel;
	JLabel sizeLabel;
	JLabel size;
	private static int sizeValue;
	private static boolean running; 
	public RightPanel() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(300, 1000));
		createComponents();
		running = true;
		t = new Thread(this);
		t.start();
	}
	
	private void createComponents() {
		label = new JLabel("Score");
		scoreLabel = new JLabel("");
		this.add(label);
		this.add(scoreLabel);
		size = new JLabel("Size");
		sizeLabel = new JLabel("");
		this.add(size);
		this.add(sizeLabel);
		
	}

	@Override
	public void run() {
		while(running) {
			repaint();
			update();
			try {
				Thread.sleep(1500);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void update() {
		scoreLabel.setText(String.valueOf(score));
		sizeValue = CenterPanel.getKSize();
		sizeLabel.setText(String.valueOf(sizeValue));
	}
public static void setScore(int i) {
	score = i;
}

public static void stop() {
	running = false;
}
public static void setSize(int s) {
	sizeValue = s;
}

}

