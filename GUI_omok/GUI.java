package project4_오목;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.Scanner;

//import javax.*;
import javax.imageio.*;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener{

	private JPanel mpanel;
	private JPanel panel;
	private JMenuItem[] mItem = new JMenuItem[5];
	private JMenu[] menu = new JMenu[2];
	private JLabel sizeLabel;
	private JLabel turnLabel;
	private JLabel typeLabel;
	private Position nowPoint = new Position(0,0);
	private int type = 2;
	private int size = 19;
	private int turn = 0;
	
	private MyMatch match = new MyMatch();
	private Player[] player;
	private MyBoard board = new MyBoard(19,19);
	private int gameType;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI main = new GUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("오목 게임");
		setSize(518, 570);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu[0] = new JMenu("사이즈");
		menuBar.add(menu[0]);
		
		
		mItem[0] = new JMenuItem("11");
		mItem[0].addActionListener(this);
		menu[0].add(mItem[0]);
		
		mItem[1] = new JMenuItem("15");
		mItem[1].addActionListener(this);
		menu[0].add(mItem[1]);
		
		mItem[2] = new JMenuItem("19");
		mItem[2].addActionListener(this);
		menu[0].add(mItem[2]);
		
		menu[1] = new JMenu("타입");
		menuBar.add(menu[1]);
		mItem[3] = new JMenuItem("vs Com");
		mItem[3].addActionListener(this);
		menu[1].add(mItem[3]);
		
		mItem[4] = new JMenuItem("vs Player");
		mItem[4].addActionListener(this);
		menu[1].add(mItem[4]);
		
		/*mpanel = new MyPanel();
		mpanel.setBounds(0, 0, 500, 500);
		mpanel.addMouseListener(new MyPanel());
		panel.add(mpanel);*/
		panel.setLayout(null);
		
		sizeLabel = new JLabel("");
		sizeLabel.setFont(new Font("굴림", Font.BOLD, 20));
		sizeLabel.setBounds(510, 40, 149, 36);
		
		typeLabel = new JLabel("게임타입: PvP");
		typeLabel.setFont(new Font("굴림", Font.BOLD, 20));
		typeLabel.setBounds(510, 80, 249, 36);
		
		turnLabel = new JLabel("차례: 흑");
		turnLabel.setFont(new Font("굴림", Font.BOLD, 20));
		turnLabel.setBounds(510, 120, 149, 36);
		
		panel.add(sizeLabel);
		panel.add(turnLabel);
		panel.add(typeLabel);
		
		
	}
	
	class MyPanel extends JPanel implements MouseListener {
		
		public void paintComponent(Graphics graphics) {
			super.paintComponent(graphics);
			graphics.setColor(Color.BLACK);
			BufferedImage img;
			try {
				img = ImageIO.read(new File("C:\\Users\\lapi1\\eclipse-workspace\\시험대비\\src\\오목_GUI\\나무.jpg"));
				int dx1 = 0; int dy1 = 0; 
				int dx2 = this.getWidth(); int dy2 = this.getHeight();
				int sx1 = 0; int sy1 =0; 
				int sx2 = img.getWidth(); int sy2 =img.getHeight();
				graphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null); 
				
			} catch (IOException e) {
				e.printStackTrace();
			}

			int w = 500 / (size + 1);
			int h = 500 / (size + 1);
			for(int i = 1; i < size + 1; i++) {
				graphics.drawLine(i * w, h, i * w, size * h);
			}
			for(int i = 1; i < size + 1; i++) {
				graphics.drawLine(w, i * h, size * w,  i * h);
			}
			
			
			for(int r = 4; r < size - 2; r+=(size - 6) / 2) {
				for(int c = 4; c < size - 2; c+=(size - 6) / 2) {
					graphics.fillOval(r * w - 3, c * h - 3, 6, 6);
				}
			}
			for(int r = 0; r < size; r++) {
				for(int c = 0; c < size; c++) {
					if(board.getStone(r, c) == StoneType.None) {}
					else if(board.getStone(r, c) == StoneType.Black) {
						graphics.setColor(Color.BLACK);
						graphics.fillOval((r + 1) * h - 8, (c + 1) * w - 8, 16, 16);
					}
					else if(board.getStone(r,c) == StoneType.White) {
						graphics.setColor(Color.WHITE);
						graphics.fillOval((r + 1) * h - 8, (c + 1) * w - 8, 16, 16);
					}
				}
			}
		}
		public Dimension getPreferredSize() {
			return new Dimension(500,500);
		}
		
		
		public void mouseClicked(MouseEvent e) {
			Graphics graphics = mpanel.getGraphics();
			int dw = mpanel.getWidth() / (size + 1); //가로간격
			int dh = mpanel.getHeight() / (size + 1); //세로간격
			
			if(type == 1) {
				int x = 0, y = 0;
				Position m = new Position(-1,-1);
				if(match.getPlayer(match.getTurn()).getPlayerType() == 1) {
					for(int r = 1; r <= size; r++) {
						for(int c = 1; c <= size; c++) {
							if(e.getX() >= (r-0.5)*dw && e.getX() < (r+0.5)*dw && e.getY() >= (c-0.5)*dh && e.getY() < (c+0.5)*dh) {
								x = r; y = c;
							}
						}
					}
					m = new Position(x - 1, y - 1);
					if(match.getValidity(m) == true) {
						board.putStone(m.getRow(), m.getColumn(), getStone(match.getTurn()));
						this.paintComponent(graphics);
					}
					else {
						JOptionPane.showMessageDialog(null, "놓을 수 없는 위치");
						return;
					}
				}
				else {
					m = match.getPlayer(match.getTurn()).play(board);
					board.putStone(m.getRow(), m.getColumn(), getStone(match.getTurn()));
					this.paintComponent(graphics);
				}
				if(match.getWinningCondition(m) == true) {
					JOptionPane.showMessageDialog(null, "백돌 승!");
					return;
				}
				match.setTurn(changeTurn(match.getTurn()));
			}
			
			if(type == 2) {
				int x = 0, y = 0;
				Position m = new Position(-1,-1);
				
				for(int r = 1; r <= size; r++) {
					for(int c = 1; c <= size; c++) {
						if(e.getX() >= (r - 0.5)*dw && e.getX() < (r + 0.5)*dw && e.getY() >= (c - 0.5)*dh && e.getY() < (c + 0.5)*dh) {
							x = r; y = c;
						}
					}
				}
				
				m = new Position(x - 1, y - 1);
				if(match.getValidity(m) == true) {
					board.putStone(m.getRow(), m.getColumn(), getStone(match.getTurn()));
					this.paintComponent(graphics);
				}	
				else {
					JOptionPane.showMessageDialog(null, "놓을 수 없는 위치");
					return;
				}
				if(match.getWinningCondition(m) == true) {
					JOptionPane.showMessageDialog(null, "흑돌 승!");
					return;
				}
				match.setTurn(changeTurn(match.getTurn()));
			}
		}	
		
		public void mousePressed(MouseEvent e) { }
		public void mouseReleased(MouseEvent e) { }
		public void mouseEntered(MouseEvent e) { }
		public void mouseExited(MouseEvent e) {	}
	}
	
	public void actionPerformed(ActionEvent e) {
		panel.repaint();
		if(e.getSource() == mItem[0]) {
			size = 11;
			if(mpanel != null)
				panel.remove(mpanel);
			mpanel = new MyPanel();
			mpanel.setBounds(0, 0, 500, 500);
			panel.add(mpanel);
			mpanel.addMouseListener(new MyPanel());
			board = new MyBoard(11,11);
			match.setBoard(board);
			sizeLabel.setText("사이즈: 11X11");
			panel.setLayout(null);
		}
		if(e.getSource() == mItem[1]) {
			size = 15;
			if(mpanel != null)
				panel.remove(mpanel);
			mpanel = new MyPanel();
			mpanel.setBounds(0, 0, 500, 500);
			panel.add(mpanel);
			mpanel.addMouseListener(new MyPanel());
			board = new MyBoard(15,15);
			match.setBoard(board);
			sizeLabel.setText("사이즈: 15X15");
			panel.setLayout(null);
		}
		if(e.getSource() == mItem[2]) {
			size = 19;
			if(mpanel != null)
				panel.remove(mpanel);
			mpanel = new MyPanel();
			mpanel.setBounds(0, 0, 500, 500);
			panel.add(mpanel);
			mpanel.addMouseListener(new MyPanel());
			board = new MyBoard(19,19);
			match.setBoard(board);
			sizeLabel.setText("사이즈: 19X19");
			panel.setLayout(null);
		}
		if(e.getSource() == mItem[3]) {
			type = 1;
			makeGame();
			typeLabel.setText("게입타입: vs Computer");
			//playGameComputer();
			
		}
		if(e.getSource() == mItem[4]) {
			type = 2;
			makeGame();
			typeLabel.setText("게임타입: PvP");
			//playGame();
		}
	}
	
	public void makeGame() {
		if(type == 1) {
			if(mpanel != null)
				panel.remove(mpanel);
			mpanel = new MyPanel();
			mpanel.setBounds(0, 0, 500, 500);
			panel.add(mpanel);
			mpanel.addMouseListener(new MyPanel());
			match = new MyMatch();
			player = new Player[2];
			
			SelectDialog sd = new SelectDialog(this);
			sd.setLocation(200,200);
			sd.setVisible(true);
			
			board = new MyBoard();
			match.setBoard(board);
		}
		if(type == 2) {
			if(mpanel != null)
				panel.remove(mpanel);
			mpanel = new MyPanel();
			mpanel.setBounds(0, 0, 500, 500);
			panel.add(mpanel);
			mpanel.addMouseListener(new MyPanel());
			match = new MyMatch();
			player = new Player[2];
			player[0] = new Player(0,"player1",0,1);
			player[1] = new Player(1,"player2",1,1);
			board = new MyBoard();
			match.setBoard(board);
			match.setPlayer(0, player[0]);
			match.setPlayer(1, player[1]);
			match.setPlayerType(2);
		}
	}
	
	public int changeTurn(int n) {
		if(n != 0) {
			turnLabel.setText("차례: 흑");
			return 0;
		}
		else {
			turnLabel.setText("차례: 백");
			return 1;
		}
	}
	
	public StoneType getStone(int n) {
		if(n == 0) return StoneType.Black;
		else if(n == 1) return StoneType.White;
		else return StoneType.None;
	}
	
	
	class SelectDialog extends JDialog implements ActionListener {
		JLabel text = new JLabel("select");
		JButton button1 = new JButton("Black");
		JButton button2 = new JButton("White");
		public SelectDialog(JFrame frame) {
			super(frame,"select dialog");
			setLayout(new FlowLayout());
			add(text);
			add(button1);
			add(button2);
			pack();
			button1.addActionListener(this);
			button2.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button1) {
				player[0] = new Player(0,"player",0,1);
				player[1] = new ComputerPlayer(1,"computer",1,2);

				match.setPlayer(0, player[0]);
				match.setPlayer(1, player[1]);
				match.setPlayerType(1);
				this.setVisible(false);
			}
			else if(e.getSource() == button2) {
				player[0] = new ComputerPlayer(0,"computer",0,2);
				player[1] = new Player(1,"player",1,1);
				match.setPlayer(0, player[0]);
				match.setPlayer(1, player[1]);
				match.setPlayerType(1);
				this.setVisible(false);
			}
		}
	}
	
}