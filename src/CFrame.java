import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;
public class CFrame extends JPanel implements ActionListener { 
	
	ArrayList<Person> people = new ArrayList<Person>();
	ArrayList<Point> points = new ArrayList<Point>();
	int time = 0;
	
	public static void main(String[] arg) {
		CFrame c = new CFrame();
		
	}

	public void paint(Graphics g) {
		time += 16;
		points.add(new Point(time/16, Person.numInfected));
		super.paintComponent(g);
		for(Person p: people) {
			p.paint(g); 
		}
		for(int i =0; i < people.size();i++) {
			for(int j = i+1 ; j < people.size();j++){
				people.get(i).collision(people.get(j));
			}
		}
		g.setColor(Color.blue);
		for(Point p: points) {
			g.fillOval(p.time, 200-p.value, 10, 10);
		}	
	
	}
	
	
	public CFrame() {
		JFrame frame = new JFrame("Stimulation");
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for(int i = 0; i < 100; i++) {
			people.add(new Person());
		}
		Timer t = new Timer(16, this);
		t.restart();
			
		frame.add(this);
		
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
