import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;


public class Snake extends JComponent{
	
	private int length;
	private int secondLength;
	private int xpos;
	private int ypos;
	private ArrayList<Integer> moves;
	private ArrayList<Integer> secondMoves;
	private Random rand;
	private int foodX;
	private int foodY;
	
	public boolean failed;
	
	public Snake(){
		moves = new ArrayList<Integer>();
		secondMoves = new ArrayList<Integer>();
		length = 3;
		secondLength = 0;
		moves.add(0);
		moves.add(0);
		moves.add(0);
		
		xpos = 3;
		ypos = 3;
		
		rand = new Random(2);
		
		foodX = rand.nextInt(Main.gridWidth);
		foodY = rand.nextInt(Main.gridHeight);
		
		failed = false;
		
	}
	
	public void update(){
		switch(moves.get(moves.size()-1)){
		case 0:
			ypos--;
			break;
		case 1:
			xpos++;
			break;
		case 2:
			ypos++;
			break;
		case 3:
			xpos--;
			break;
		}
		if(xpos < 0)
			xpos = Main.gridWidth-1;
		else if(xpos > Main.gridWidth-1)
			xpos = 0;
		if(ypos <0)
			ypos = Main.gridHeight-1;
		else if(ypos > Main.gridHeight-1)
			ypos = 0;
		
		if(xpos==foodX && ypos==foodY){
			length++;
			foodX = rand.nextInt(Main.gridWidth);
			foodY = rand.nextInt(Main.gridHeight);
		}
		
		if(moves.size()<length){
			moves.add(0, moves.get(0));
		}
		for(int i=0; i<moves.size();i++){
			if(i!=moves.size()-1)
				moves.set(i, moves.get(i+1));
		}
		moves.set(moves.size()-1, moves.get(moves.size()-1));
	}
	
	public void moveUp(){
		if(moves.get(moves.size()-1)!=2)
			moves.set(moves.size()-1, 0);
	}
	
	public void moveRight(){
		if(moves.get(moves.size()-1)!=3)
			moves.set(moves.size()-1, 1);
	}
	
	public void moveDown(){
		if(moves.get(moves.size()-1)!=0)
			moves.set(moves.size()-1, 2);
	}
	
	public void moveLeft(){
		if(moves.get(moves.size()-1)!=1)
			moves.set(moves.size()-1, 3);
	}
	
	public void increaseLength(){
		length++;
	}
	
	public void paintComponent(Graphics g){
		
		int startX =	Main.frame.getWidth()/2;
		startX -= Main.gridBoxSize*(Main.gridWidth/2);
		
		int startY = Main.frame.getHeight()/2;
		startY -= Main.gridBoxSize*(Main.gridHeight/2);
		
		ArrayList<Integer> xTail = new ArrayList<Integer>();
		ArrayList<Integer> yTail = new ArrayList<Integer>();
		
		g.setColor(Color.RED);
		int xCur = xpos;
		int yCur = ypos;
		int width = Main.gridWidth;
		for(int m=moves.size()-2;m>=0;m--){
			switch(moves.get(m)){
			case 0:
				yCur++;
				break;
			case 1:
				xCur--;
				break;
			case 2:
				yCur--;
				break;
			case 3:
				xCur++;
				break;
			}
			if(xCur <0)
				xCur=Main.gridWidth-1;
			else if(xCur > Main.gridWidth-1)
				xCur =0;
			if(yCur <0)
				yCur=Main.gridHeight-1;
			else if(yCur > Main.gridHeight-1)
				yCur = 0;
			g.fillRect(startX+(xCur*width)+1, startY+(yCur*width)+1, width-1, width-1);
			
			xTail.add(xCur);
			yTail.add(yCur);
			
		}
		for(int i = 0; i < xTail.size();i++){
			if(xTail.get(i)==xpos && yTail.get(i)==ypos)
				failed = true;
		}
		g.fillRect(startX+(xpos*width)+1, startY+(ypos*width)+1, width-1, width-1);
		g.setColor(Color.YELLOW);
		g.fillRect(startX+(foodX*width)+1, startY+(foodY*width)+1, width-1, width-1);
		
	}

}
