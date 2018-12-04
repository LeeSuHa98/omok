package 오목판;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	public static void main(String[] args) {
		new GUI("오목");
	}
	private Container c;
	MapSize size = new MapSize();
	
	public GUI(String title) {
		c = getContentPane();
		setBounds(200,20,400,430);
		c.setLayout(null);
		Map map = new Map(size);
		DrawBoard d = new DrawBoard(size, map);

		setContentPane(d);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
