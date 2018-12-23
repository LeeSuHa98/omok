package project4_오목;

import java.util.Scanner;

enum StoneType {None, Black, White}

class Position {
	private int row;
	private int column;
	
	public Position(int r, int c) {row = r; column = c;}
	public int getRow() {return row;}
	public int getColumn() {return column;}
}

class Player {
	private int id;
	private int order;
	private int playerType;
	private double winningRate;
	private String name;
	
	public Player(int id, String name, int order, int playerType) {
		this.id = id;
		this.name = name;
		this.order = order;
		this.playerType = playerType;
	}
	
	public int getID() {return id;}
	public String getName() {return name;}
	public int getOrder() {return order;}
	public double getWinningRate() {return winningRate;}
	public int getPlayerType() {return playerType;}
	public Position play(OmokBoard board) {return new Position(-1,-1);}
}

class ComputerPlayer extends Player
{
	public ComputerPlayer(int id, String name, int order, int playType) {
		super(id,name,order,playType);
	}
	
	private int evaluatePosition(OmokBoard board, int x, int y) {
		int point = 0;
		if(board.getStone(x, y) == StoneType.Black ||board.getStone(x, y) == StoneType.White) 
			return -100;
		Position m = new Position(x,y);
		int[] myStoneNum = new int[8];
		int[] opStoneNum = new int[8];
		StoneType myColor, opColor;
		if(getOrder() == 0) { myColor = StoneType.Black; opColor = StoneType.White;}
		else { myColor = StoneType.White; opColor = StoneType.Black;}
		
		board.countSameColorStones(m, myColor, myStoneNum);
		board.countSameColorStones(m, opColor, opStoneNum);
		
		if(myStoneNum[0] + myStoneNum[4] == 4 || myStoneNum[1] + myStoneNum[5] == 4 ||
				myStoneNum[2] + myStoneNum[6] == 4 || myStoneNum[3] + myStoneNum[7] == 4) {
			point = 100;
		}
		
		else if(opStoneNum[0] + opStoneNum[4] == 4 || opStoneNum[1] + opStoneNum[5] == 4 ||
				opStoneNum[2] + opStoneNum[6] == 4 || opStoneNum[3] + opStoneNum[7] == 4) {
			point = 90;
		}
		
		else if(myStoneNum[0] + myStoneNum[4] == 3 || myStoneNum[1] + myStoneNum[5] == 3 ||
				myStoneNum[2] + myStoneNum[6] == 3 || myStoneNum[3] + myStoneNum[7] == 3) 
		{
			board.putStone(x, y, myColor);
			if(myStoneNum[0] + myStoneNum[4] == 3) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount()) {
					if(board.getStone(mx, my) == myColor)
						mx++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0) {
					if(board.getStone(mx, my) == myColor)
						mx--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0) isBlocked = true;
				}
				if(isBlocked) point = 70;
				else point = 80;
			}
			else if(myStoneNum[1] + myStoneNum[5] == 3) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx >= 0 && my < board.getColCount()) {
					if(board.getStone(mx, my) == myColor) {
						mx--;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx < board.getColCount() && my >= 0) {
					if(board.getStone(mx, my) == myColor) {
						mx++;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getColCount() || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 70;
				else point = 80;
			}
			else if(myStoneNum[2] + myStoneNum[6] == 3) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(my < board.getColCount()) {
					if(board.getStone(mx, my) == myColor)
						my++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(my >= 0) {
					if(board.getStone(mx, my) == myColor)
						my--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my < 0) isBlocked = true;
				}
				if(isBlocked) point = 70;
				else point = 80;
			}
			else if(myStoneNum[3] + myStoneNum[7] == 3) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount() && my < board.getColCount()) {
					if(board.getStone(mx, my) == myColor) {
						mx++;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount() || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0 && my >= 0) {
					if(board.getStone(mx, my) == myColor) {
						mx--;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 70;
				else point = 80;
			}
			board.removeStone(x, y);
		}
		
		else if(opStoneNum[0] + opStoneNum[4] == 3 || opStoneNum[1] + opStoneNum[5] == 3 ||
				opStoneNum[2] + opStoneNum[6] == 3 || opStoneNum[3] + opStoneNum[7] == 3) 
		{
			board.putStone(x, y, opColor);
			if(opStoneNum[0] + opStoneNum[4] == 3) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount()) {
					if(board.getStone(mx, my) == opColor)
						mx++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0) {
					if(board.getStone(mx, my) == opColor)
						mx--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0) isBlocked = true;
				}
				if(isBlocked) point = 65;
				else point = 75;
			}
			else if(opStoneNum[1] + opStoneNum[5] == 3) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx >= 0 && my < board.getColCount()) {
					if(board.getStone(mx, my) == opColor) {
						mx--;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx < board.getColCount() && my >= 0) {
					if(board.getStone(mx, my) == opColor) {
						mx++;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getColCount() || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 65;
				else point = 75;
			}
			else if(opStoneNum[2] + opStoneNum[6] == 3) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(my < board.getColCount()) {
					if(board.getStone(mx, my) == opColor)
						my++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(my >= 0) {
					if(board.getStone(mx, my) == opColor)
						my--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my < 0) isBlocked = true;
				}
				if(isBlocked) point = 65;
				else point = 75;
			}
			else if(opStoneNum[3] + opStoneNum[7] == 3) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount() && my < board.getColCount()) {
					if(board.getStone(mx, my) == opColor) {
						mx++;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount() || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0 && my >= 0) {
					if(board.getStone(mx, my) == opColor) {
						mx--;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 65;
				else point = 75;
			}
			board.removeStone(x, y);
		}
		
		else if(myStoneNum[0] + myStoneNum[4] == 2 || myStoneNum[1] + myStoneNum[5] == 2 ||
				myStoneNum[2] + myStoneNum[6] == 2 || myStoneNum[3] + myStoneNum[7] == 2) 
		{
			board.putStone(x, y, myColor);
			if(myStoneNum[0] + myStoneNum[4] == 2) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount()) {
					if(board.getStone(mx, my) == myColor)
						mx++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0) {
					if(board.getStone(mx, my) == myColor)
						mx--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0) isBlocked = true;
				}
				if(isBlocked) point = 50;
				else point = 60;
			}
			else if(myStoneNum[1] + myStoneNum[5] == 2) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx >= 0 && my < board.getColCount()) {
					if(board.getStone(mx, my) == myColor) {
						mx--;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx < board.getColCount() && my >= 0) {
					if(board.getStone(mx, my) == myColor) {
						mx++;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getColCount() || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 50;
				else point = 60;
			}
			else if(myStoneNum[2] + myStoneNum[6] == 2) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(my < board.getColCount()) {
					if(board.getStone(mx, my) == myColor)
						my++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(my >= 0) {
					if(board.getStone(mx, my) == myColor)
						my--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my < 0) isBlocked = true;
				}
				if(isBlocked) point = 50;
				else point = 60;
			}
			else if(myStoneNum[3] + myStoneNum[7] == 2) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount() && my < board.getColCount()) {
					if(board.getStone(mx, my) == myColor) {
						mx++;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount() || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0 && my >= 0) {
					if(board.getStone(mx, my) == myColor) {
						mx--;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 50;
				else point = 60;
			}
			board.removeStone(x, y);
		}
		
		else if(opStoneNum[0] + opStoneNum[4] == 2 || opStoneNum[1] + opStoneNum[5] == 2 ||
				opStoneNum[2] + opStoneNum[6] == 2 || opStoneNum[3] + opStoneNum[7] == 2) 
		{
			board.putStone(x, y, opColor);
			if(opStoneNum[0] + opStoneNum[4] == 2) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount()) {
					if(board.getStone(mx, my) == opColor)
						mx++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0) {
					if(board.getStone(mx, my) == opColor)
						mx--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0) isBlocked = true;
				}
				if(isBlocked) point = 45;
				else point = 55;
			}
			else if(opStoneNum[1] + opStoneNum[5] == 2) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx >= 0 && my < board.getColCount()) {
					if(board.getStone(mx, my) == opColor) {
						mx--;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx < board.getColCount() && my >= 0) {
					if(board.getStone(mx, my) == opColor) {
						mx++;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getColCount() || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 45;
				else point = 55;
			}
			else if(opStoneNum[2] + opStoneNum[6] == 2) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(my < board.getColCount()) {
					if(board.getStone(mx, my) == opColor)
						my++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(my >= 0) {
					if(board.getStone(mx, my) == opColor)
						my--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my < 0) isBlocked = true;
				}
				if(isBlocked) point = 45;
				else point = 55;
			}
			else if(opStoneNum[3] + opStoneNum[7] == 2) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount() && my < board.getColCount()) {
					if(board.getStone(mx, my) == opColor) {
						mx++;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount() || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0 && my >= 0) {
					if(board.getStone(mx, my) == opColor) {
						mx--;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 45;
				else point = 55;
			}
			board.removeStone(x, y);
		}
		
		else if(myStoneNum[0] + myStoneNum[4] == 1 || myStoneNum[1] + myStoneNum[5] == 1 ||
				myStoneNum[2] + myStoneNum[6] == 1 || myStoneNum[3] + myStoneNum[7] == 1) 
		{
			board.putStone(x, y, myColor);
			if(myStoneNum[0] + myStoneNum[4] == 1) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount()) {
					if(board.getStone(mx, my) == myColor)
						mx++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0) {
					if(board.getStone(mx, my) == myColor)
						mx--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0) isBlocked = true;
				}
				if(isBlocked) point = 30;
				else point = 40;
			}
			else if(myStoneNum[1] + myStoneNum[5] == 1) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx >= 0 && my < board.getColCount()) {
					if(board.getStone(mx, my) == myColor) {
						mx--;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx < board.getColCount() && my >= 0) {
					if(board.getStone(mx, my) == myColor) {
						mx++;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getColCount() || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 30;
				else point = 40;
			}
			else if(myStoneNum[2] + myStoneNum[6] == 1) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(my < board.getColCount()) {
					if(board.getStone(mx, my) == myColor)
						my++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(my >= 0) {
					if(board.getStone(mx, my) == myColor)
						my--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my < 0) isBlocked = true;
				}
				if(isBlocked) point = 30;
				else point = 40;
			}
			else if(myStoneNum[3] + myStoneNum[7] == 1) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount() && my < board.getColCount()) {
					if(board.getStone(mx, my) == myColor) {
						mx++;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount() || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0 && my >= 0) {
					if(board.getStone(mx, my) == myColor) {
						mx--;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 30;
				else point = 40;
			}
			board.removeStone(x, y);
		}
		
		else if(opStoneNum[0] + opStoneNum[4] == 1 || opStoneNum[1] + opStoneNum[5] == 1 ||
				opStoneNum[2] + opStoneNum[6] == 1 || opStoneNum[3] + opStoneNum[7] == 1) 
		{
			board.putStone(x, y, opColor);
			if(opStoneNum[0] + opStoneNum[4] == 1) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount()) {
					if(board.getStone(mx, my) == opColor)
						mx++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0) {
					if(board.getStone(mx, my) == opColor)
						mx--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0) isBlocked = true;
				}
				if(isBlocked) point = 25;
				else point = 35;
			}
			else if(opStoneNum[1] + opStoneNum[5] == 1) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx >= 0 && my < board.getColCount()) {
					if(board.getStone(mx, my) == opColor) {
						mx--;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx < board.getColCount() && my >= 0) {
					if(board.getStone(mx, my) == opColor) {
						mx++;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getColCount() || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 25;
				else point = 35;
			}
			else if(opStoneNum[2] + opStoneNum[6] == 1) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(my < board.getColCount()) {
					if(board.getStone(mx, my) == opColor)
						my++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(my >= 0) {
					if(board.getStone(mx, my) == opColor)
						my--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my < 0) isBlocked = true;
				}
				if(isBlocked) point = 25;
				else point = 35;
			}
			else if(opStoneNum[3] + opStoneNum[7] == 1) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount() && my < board.getColCount()) {
					if(board.getStone(mx, my) == opColor) {
						mx++;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount() || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0 && my >= 0) {
					if(board.getStone(mx, my) == opColor) {
						mx--;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 25;
				else point = 35;
			}
			board.removeStone(x, y);
		}
		
		else if(myStoneNum[0] + myStoneNum[4] == 0 || myStoneNum[1] + myStoneNum[5] == 0 ||
				myStoneNum[2] + myStoneNum[6] == 0 || myStoneNum[3] + myStoneNum[7] == 0) 
		{
			board.putStone(x, y, myColor);
			if(myStoneNum[0] + myStoneNum[4] == 0) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount()) {
					if(board.getStone(mx, my) == myColor)
						mx++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0) {
					if(board.getStone(mx, my) == myColor)
						mx--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0) isBlocked = true;
				}
				if(isBlocked) point = 10;
				else point = 20;
			}
			else if(myStoneNum[1] + myStoneNum[5] == 0) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx >= 0 && my < board.getColCount()) {
					if(board.getStone(mx, my) == myColor) {
						mx--;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx < board.getColCount() && my >= 0) {
					if(board.getStone(mx, my) == myColor) {
						mx++;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getColCount() || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 10;
				else point = 20;
			}
			else if(myStoneNum[2] + myStoneNum[6] == 0) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(my < board.getColCount()) {
					if(board.getStone(mx, my) == myColor)
						my++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(my >= 0) {
					if(board.getStone(mx, my) == myColor)
						my--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my < 0) isBlocked = true;
				}
				if(isBlocked) point = 10;
				else point = 20;
			}
			else if(myStoneNum[3] + myStoneNum[7] == 0) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount() && my < board.getColCount()) {
					if(board.getStone(mx, my) == myColor) {
						mx++;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount() || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0 && my >= 0) {
					if(board.getStone(mx, my) == myColor) {
						mx--;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 10;
				else point = 20;
			}
			board.removeStone(x, y);
		}
		
		else if(opStoneNum[0] + opStoneNum[4] == 0 || opStoneNum[1] + opStoneNum[5] == 0 ||
				opStoneNum[2] + opStoneNum[6] == 0 || opStoneNum[3] + opStoneNum[7] == 0) 
		{
			board.putStone(x, y, opColor);
			if(opStoneNum[0] + opStoneNum[4] == 0) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount()) {
					if(board.getStone(mx, my) == opColor)
						mx++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0) {
					if(board.getStone(mx, my) == opColor)
						mx--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0) isBlocked = true;
				}
				if(isBlocked) point = 5;
				else point = 15;
			}
			else if(opStoneNum[1] + opStoneNum[5] == 0) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx >= 0 && my < board.getColCount()) {
					if(board.getStone(mx, my) == opColor) {
						mx--;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx < board.getColCount() && my >= 0) {
					if(board.getStone(mx, my) == opColor) {
						mx++;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getColCount() || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 5;
				else point = 15;
			}
			else if(opStoneNum[2] + opStoneNum[6] == 0) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(my < board.getColCount()) {
					if(board.getStone(mx, my) == opColor)
						my++;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(my >= 0) {
					if(board.getStone(mx, my) == opColor)
						my--;
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(my < 0) isBlocked = true;
				}
				if(isBlocked) point = 5;
				else point = 15;
			}
			else if(opStoneNum[3] + opStoneNum[7] == 0) {
				boolean isBlocked = false;
				int mx = x,my = y;
				while(mx < board.getRowCount() && my < board.getColCount()) {
					if(board.getStone(mx, my) == opColor) {
						mx++;
						my++;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx == board.getRowCount() || my == board.getColCount()) isBlocked = true;
				}
				mx = x; my = y;
				while(mx >= 0 && my >= 0) {
					if(board.getStone(mx, my) == opColor) {
						mx--;
						my--;
					}
					else if(board.getStone(mx, my) == StoneType.None)
						break;
					else {
						isBlocked = true;
						break;
					}
					if(mx < 0 || my < 0) isBlocked = true;
				}
				if(isBlocked) point = 5;
				else point = 15;
			}
			board.removeStone(x, y);
		}
		
		int bonusPoint = 0;
		double divDist = (double)board.getColCount() / 2 / 5;
		double dist;
		int half = board.getColCount()/2;
		if(x < half && y < half) dist = x < y ? x : y;
		else if(x < half && y > half) dist = x < y - half ? x : y - half;
		else if(x > half && y < half) dist = x - half < y ? x - half : y;
		else dist = x - half < y - half ? x - half : y - half;
		
		if(dist <= divDist || dist >= board.getColCount() - divDist) bonusPoint = 1;
		else if(dist <= 2*divDist || dist >= board.getColCount() - 2*divDist) bonusPoint = 2;
		else if(dist <= 3*divDist || dist >= board.getColCount() - 3*divDist) bonusPoint = 3;
		else if(dist <= 4*divDist || dist >= board.getColCount() - 4*divDist) bonusPoint = 4;
		else bonusPoint = 5;
		
		return point + bonusPoint;
	}
	
	public Position play(OmokBoard board) {
		int maxPoint = 0;
		int x = 0; int y = 0;
		for(int i = 0; i < board.getRowCount(); i++) {
			for(int j = 0; j < board.getColCount(); j++) {
				if(maxPoint < evaluatePosition(board,i,j))
				{
					maxPoint = evaluatePosition(board,i,j);
					x = i; y = j;
				}
			}
		}
		
		return new Position(x,y);
	}
}

class OmokBoard {
	private StoneType matrix[][];
	private int rows;
	private int cols;
	
	public OmokBoard() {
		rows = 19; 
		cols = 19; 
		matrix = new StoneType[rows][cols];
		for(int i = 0; i < 19; i++)
			for(int j = 0; j < 19; j++)
				matrix[i][j] = StoneType.None;
	}
	public OmokBoard(int r, int c) {rows = r; cols = c; matrix = new StoneType[rows][cols]; }
	public void putStone(int x, int y, StoneType stone) {
		matrix[x][y] = stone;
	}
	public void removeStone(int x, int y) {
		matrix[x][y] = StoneType.None;
	}
	public StoneType getStone(int x, int y) {
		return matrix[x][y];
	}
	public void clear() {
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++)
				matrix[i][j] = StoneType.None;
	}
	public int getRowCount() {return rows;}
	public int getColCount() {return cols;}
	public void countSameColorStones(Position m, StoneType color, int[] stoneNum) {
		int count = 0;
		
		for(int i = m.getRow() - 1; i >= 0; i--) {
			if(matrix[i][m.getColumn()] != color)
				break;
			count++;
		}
		stoneNum[0] = count;
		
		count = 0;
		
		for(int i = m.getRow() + 1; i < rows; i++) {
			if(matrix[i][m.getColumn()] != color)
				break;
			count++;
		}
		stoneNum[4] = count;
		
		count = 0;
		
		for(int i = m.getColumn() + 1; i < cols; i++) {
			if(matrix[m.getRow()][i] != color)
				break;
			count++;
		}
		stoneNum[2] = count;
		
		count = 0;
		
		for(int i = m.getColumn() - 1; i >= 0; i--) {
			if(matrix[m.getRow()][i] != color)
				break;
			count++;
		}
		stoneNum[6] = count;
		
		count = 0;
		
		for(int i = m.getRow() - 1, j = m.getColumn() + 1; i >= 0 && j < cols; i--,j++) {
			if(matrix[i][j] != color)
				break;
			count++;
		}
		stoneNum[1] = count;
		
		count = 0;
		
		for(int i = m.getRow() + 1, j = m.getColumn() - 1; i < rows && j >= 0; i++,j--) {
			if(matrix[i][j] != color)
				break;
			count++;
		}
		stoneNum[5] = count;
		
		count = 0;
		
		for(int i = m.getRow() + 1, j = m.getColumn() + 1; i < rows && j < cols; i++,j++) {
			if(matrix[i][j] != color)
				break;
			count++;
		}
		stoneNum[3] = count;
		
		count = 0;
		
		for(int i = m.getRow() - 1, j = m.getColumn() - 1; i >= 0 && j >= 0; i--,j--) {
			if(matrix[i][j] != color)
				break;
			count++;
		}
		stoneNum[7] = count;
	}
}

class MyBoard extends OmokBoard 
{
	public MyBoard() {
		super();
	}
	public MyBoard(int r, int c) {
		super(r,c);
	}
	public void display() {
		for(int i = 0; i < getRowCount(); i++) {
			for(int j = 0; j < getColCount(); j++) {
				if(getStone(i,j) == StoneType.None)
					System.out.print("┼");
				else if(getStone(i,j) == StoneType.Black)
					System.out.print("●");
				else if(getStone(i,j) == StoneType.White)
					System.out.print("○");
			}
			System.out.println();
		}
	}
}

class Match {
	private Player[] players = new Player[2];
	private int turn, playType;
	private OmokBoard playBoard;
	private int boardSize;
	private boolean checkWinningCondition(Position m) {
		int[] stoneNum = new int[8];
		playBoard.countSameColorStones(m, getCurrentColor(), stoneNum);
		
		if(stoneNum[0] + stoneNum[4] == 4 || stoneNum[1] + stoneNum[5] == 4 || 
				stoneNum[2] + stoneNum[6] == 4 || stoneNum[3] + stoneNum[7] == 4)
			return true;
		
		return false;
	}
	private boolean checkValidity(Position m) {
		if(playBoard.getStone(m.getRow(), m.getColumn()) == StoneType.None) {
			if(m.getRow() >= 0 && m.getColumn() >= 0 &&
					m.getRow() < playBoard.getRowCount() && m.getColumn() < playBoard.getColCount())
			{
				return true;
			}
		}
		
		return false;
	}
	
	public int getTurn() { return turn; }
	public void setTurn(int n) { turn = n; }
	public Player getPlayer(int n) { return players[n]; }
	public Player getCurrentPlayer() { return players[getTurn()]; }
	public StoneType getCurrentColor() { 
		if (getTurn() == 0) return StoneType.Black;
		else return StoneType.White;
	}
	public OmokBoard getBoard() { return playBoard; }
	public void setBoard(OmokBoard board) { playBoard = board; }
	public void setPlayer(int n, Player player) {
		players[n] = player;
	}
	public int getPlayerType() {return playType;}
	public void setPlayerType(int n) {playType = n;}
}

class MyMatch extends Match {
	
	public boolean getWinningCondition(Position m) {
		int[] stoneNum = new int[8];
		getBoard().countSameColorStones(m, getCurrentColor(), stoneNum);
		
		if(stoneNum[0] + stoneNum[4] == 4 || stoneNum[1] + stoneNum[5] == 4 || 
				stoneNum[2] + stoneNum[6] == 4 || stoneNum[3] + stoneNum[7] == 4)
			return true;
		
		return false;
	}
	public boolean getValidity(Position m) {
		if(m.getRow() >= 0 && m.getColumn() >= 0 && m.getRow() < getBoard().getRowCount() && m.getColumn() < getBoard().getColCount()) 
		{
			if(getBoard().getStone(m.getRow(), m.getColumn()) == StoneType.None)
			{
				return true;
			}
		}
		return false;
	}
	
}

public class Main {
	private MyMatch match;
	private Player[] player;
	private MyBoard board;
	private Scanner s = new Scanner(System.in);
	private int gameType;
	
	/*public static void main(String[] args) {
		Main m = new Main();
		m.selectGameType();
		m.makeGame();
		if(m.gameType == 1)
			m.playGameComputer();
		else if(m.gameType == 2)
			m.playGame();
	}*/
	public void selectGameType() {
		System.out.println("1. 로컬    2. 네트워크");
		System.out.print("게임타입을 고르시오: ");
		gameType = s.nextInt();
	}
	public void makeGame() {
		if(gameType == 1) {
			match = new MyMatch();
			player = new Player[2];
			System.out.println("0. 선공(흑)   1. 후공(백)");
			System.out.print("순서를 선택하시오: ");
			int order = s.nextInt();
			if(order == 0) {
				player[0] = new Player(0,"player",0,1);
				player[1] = new ComputerPlayer(1,"computer",1,2);
			}
			else {
				player[0] = new ComputerPlayer(0,"computer",0,2);
				player[1] = new Player(1,"player",1,1);
			}
			board = new MyBoard();
			match.setBoard(board);
			match.setPlayer(0, player[0]);
			match.setPlayer(1, player[1]);
			match.setPlayerType(1);
		}
		if(gameType == 2) {
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
	public void playGameComputer() {
		System.out.println("게임 시작!");
		Position m = null;
		match.setTurn(-1);
		
		while(true) {
			board.display();
			match.setTurn(changeTurn(match.getTurn()));
			if(match.getPlayer(match.getTurn()).getPlayerType() == 1) {
				System.out.println(match.getTurn() + 1 + "번째 차례입니다. 바둑돌을 놓아주세요");
				System.out.print("row, col: ");
				int row = s.nextInt(); int col = s.nextInt();
				m = new Position(row,col);
				if(match.getValidity(m) == true)
					board.putStone(m.getRow(), m.getColumn(), getStone(match.getTurn()));
				else {
					while(match.getValidity(m) == false) {
						System.out.println("놓을 수 없는 자리입니다. 다시 놓아주세요!");
						System.out.print("row, col: ");
						row = s.nextInt(); col = s.nextInt();
						m = new Position(row,col);
					}
					board.putStone(m.getRow(), m.getColumn(), getStone(match.getTurn()));
				}
			}
			else {
				System.out.println(match.getTurn() + 1 + "번째 차례입니다");
				m = match.getPlayer(match.getTurn()).play(board);
				board.putStone(m.getRow(), m.getColumn(), getStone(match.getTurn()));
			}
			if(match.getWinningCondition(m) == true) break;
			
		}
		board.display();
		System.out.println(match.getPlayer(match.getTurn()).getName() + "의 승리입니다!");
		board.clear();
	}
	public void playGame() {
		System.out.println("게임 시작!");
		Position m = null;
		match.setTurn(-1);
		while(true) {
			board.display();
			match.setTurn(changeTurn(match.getTurn()));
			System.out.println(match.getTurn() + 1 + "번째 차례입니다. 바둑돌을 놓아주세요");
			System.out.print("row, col: ");
			int row = s.nextInt(); int col = s.nextInt();
			m = new Position(row,col);
			if(match.getValidity(m) == true)
				board.putStone(m.getRow(), m.getColumn(), getStone(match.getTurn()));
			else {
				while(match.getValidity(m) == false) {
					System.out.println("놓을 수 없는 자리입니다. 다시 놓아주세요!");
					System.out.print("row, col: ");
					row = s.nextInt(); col = s.nextInt();
					m = new Position(row,col);
				}
				board.putStone(m.getRow(), m.getColumn(), getStone(match.getTurn()));
			}
			if(match.getWinningCondition(m) == true) break;
			
		}
		board.display();
		System.out.println(match.getPlayer(match.getTurn()).getName() + "의 승리입니다!");
		board.clear();
	}
	
	public int changeTurn(int n) {
		if(n != 0) return 0;
		else return 1;
	}
	
	public StoneType getStone(int n) {
		if(n == 0) return StoneType.Black;
		else if(n == 1) return StoneType.White;
		else return StoneType.None;
	}
}