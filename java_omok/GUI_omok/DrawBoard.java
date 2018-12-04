package ������;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawBoard extends JPanel{
	private MapSize size;

	private Map map;

	public DrawBoard(MapSize m, Map map) {
		setBackground(new Color(206,167,61)); //���� ����

		size=m;

		setLayout(null);

		this.map=map;
	}

	@Override

	public void paintComponent(Graphics arg0) {

		// TODO Auto-generated method stub

		super.paintComponent(arg0);

		arg0.setColor(Color.BLACK); //�׷��� ���� ������

		board(arg0); //���带 �׸�
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

			//���� �� �׸���

			arg0.drawLine(size.getCell(), i*size.getCell(), size.getCell()*size.getSize(), i*size.getCell()); //������ x : 30, ������ y : i�� (�ٹ�ȣ)*30, ���� x : 600,���� y : i�� (�ٹ�ȣ)*30

			//������ �׸���

			arg0.drawLine(i*size.getCell(), size.getCell(), i*size.getCell() , size.getCell()*size.getSize()); //������ x : i�� (�ٹ�ȣ)*30, ������ y : 30, ���� x : i�� (�ٹ�ȣ)*30, ���� y : 600

		}

	}
}
