package 오목판;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawBoard extends JPanel{
	private MapSize size;

	private Map map;

	public DrawBoard(MapSize m, Map map) {
		setBackground(new Color(206,167,61)); //배경색 지정

		size=m;

		setLayout(null);

		this.map=map;
	}

	@Override

	public void paintComponent(Graphics arg0) {

		// TODO Auto-generated method stub

		super.paintComponent(arg0);

		arg0.setColor(Color.BLACK); //그려질 색을 블랙지정

		board(arg0); //보드를 그림
		paintDot(arg0);
	}

	public void paintDot(Graphics arg0) {
		//super.paintComponent(arg0);
		arg0.setColor(Color.BLACK);
		for(int i = 4; i<18 ; i+=6) {
			for(int j = 4; j <18; j+=6) {
				arg0.fillOval(i*size.getCell()-4, j*size.getCell()-4, 8, 8);
			}
		}
	}
	public void board(Graphics arg0) {

		for(int i=1;i<=size.getSize();i++){

			//가로 줄 그리기

			arg0.drawLine(size.getCell(), i*size.getCell(), size.getCell()*size.getSize(), i*size.getCell()); //시작점 x : 30, 시작점 y : i값 (줄번호)*30, 끝점 x : 600,끝점 y : i값 (줄번호)*30

			//세로줄 그리기

			arg0.drawLine(i*size.getCell(), size.getCell(), i*size.getCell() , size.getCell()*size.getSize()); //시작점 x : i값 (줄번호)*30, 시작점 y : 30, 끝점 x : i값 (줄번호)*30, 끝점 y : 600

		}

	}
}
