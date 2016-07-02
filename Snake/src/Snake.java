import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;


public class Snake extends JComponent implements Comparable<Snake>, NEATInterface{
	
	public int length;
	private int xpos;
	private int ypos;
	public ArrayList<Integer> moves;
	private Random rand;
	private int foodX;
	private int foodY;
	public int blocksTraveled;
	public int blocksWithoutChange;
	private int maxBlocks = 80;
	private double fit;
	double temp;
	
	public newANN<Snake> ann;
	
	public boolean failed;
	
	public Snake(){
		moves = new ArrayList<Integer>();
		length = 3;
		moves.add(0);

		
		ann = new newANN<Snake>(9,4, this);
		
		xpos = 3;
		ypos = 3;
		
		rand = new Random();
		
		foodX = rand.nextInt(Main.gridWidth);
		foodY = rand.nextInt(Main.gridHeight);
		
		failed = false;
		
	}
	
	public void update(){
		blocksTraveled++;
		blocksWithoutChange++;
		if(blocksWithoutChange >= this.maxBlocks){
			//System.out.println("Failed: " + (this.length -3) + "    :    " + this.blocksTraveled);
			this.failed = true;
			return;
		}
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
			fit += (this.maxBlocks - this.blocksWithoutChange)*2;
			blocksWithoutChange = 0 ;
		}
		
		if(moves.size()<length){
			moves.add(0, moves.get(0));
		}
		for(int i=0; i<moves.size();i++){
			if(i!=moves.size()-1)
				moves.set(i, moves.get(i+1));
		}
		moves.set(moves.size()-1, moves.get(moves.size()-1));
		
		int distX = xpos - foodX;
//		if((Main.gridWidth - xpos)+foodX > distX)
//			distX = (Main.gridWidth - xpos)+foodX;
		int distY = ypos - foodY;
//		if((Main.gridHeight- ypos)+foodY > distY)
//			distY = (Main.gridHeight - ypos)+foodY;
		
		int l1 = this.valueAt((xpos +1)%Main.gridWidth, ypos);
		int l2 = this.valueAt((xpos -1)%Main.gridWidth, ypos);
		int l3 = this.valueAt(xpos, (ypos +1)%Main.gridHeight);
		int l4 = this.valueAt(xpos, (ypos -1)%Main.gridHeight);
		int l5 = 0;
		int l6 = 0;
		
		switch(moves.get(moves.size()-1)){
		case 0:
			l5 = this.valueAt((xpos -1)%Main.gridWidth, (ypos -1)%Main.gridHeight);
			l6 = this.valueAt((xpos +1)%Main.gridWidth, (ypos -1)%Main.gridHeight);
			break;
		case 1:
			l5 = this.valueAt((xpos +1)%Main.gridWidth, (ypos -1)%Main.gridHeight);
			l6 = this.valueAt((xpos +1)%Main.gridWidth, (ypos +1)%Main.gridHeight);
			break;
		case 2:
			l5 = this.valueAt((xpos +1)%Main.gridWidth, (ypos +1)%Main.gridHeight);
			l6 = this.valueAt((xpos -1)%Main.gridWidth, (ypos +1)%Main.gridHeight);
			break;
		case 3:
			l5 = this.valueAt((xpos -1)%Main.gridWidth, (ypos +1)%Main.gridHeight);
			l6 = this.valueAt((xpos -1)%Main.gridWidth, (ypos -1)%Main.gridHeight);
			break;
		}
		
		
		double[] inputs = {distX, distY, l1,l2,l3,l4,l5,l6, 1};
		this.ann.addInputs(inputs);
		double[] output = this.ann.getOutput();
		boolean moved = false;
		for(int i = 0; i < output.length; i++){
			if(output[i] >= .7 && !moved){
				switch(i){
				case 0:
					this.moveUp();
					moved = true;
					break;
				case 1:
					this.moveRight();
					moved = true;
					break;
				case 2:
					this.moveDown();
					moved = true;
					break;
				case 3:
					this.moveLeft();
					moved = true;
					break;
				}
			}
		}
	}
	
	public int valueAt(int x, int y){
		ArrayList<Integer> xTail = new ArrayList<Integer>();
		ArrayList<Integer> yTail = new ArrayList<Integer>();
		int xCur = xpos;
		int yCur = ypos;
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
			
			xTail.add(xCur);
			yTail.add(yCur);
		}
		for(int i = 0; i < xTail.size();i++){
			if(xTail.get(i)==x && yTail.get(i)==y){
				return 1;
			}
		}
		return 0;
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
		
		if(failed)
			return;
		
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
			if(xTail.get(i)==xpos && yTail.get(i)==ypos){
				failed = true;
				//fit -= (length - 3) * 100;
			}
		}
		g.fillRect(startX+(xpos*width)+1, startY+(ypos*width)+1, width-1, width-1);
		g.setColor(Color.YELLOW);
		g.fillRect(startX+(foodX*width)+1, startY+(foodY*width)+1, width-1, width-1);
		
	}
	
	public double calculateFitness(){
		fit += ((this.length - 3)*20);
		fit -= this.blocksTraveled;
		fit += Math.exp(this.length - 25);
		return fit;
	}

	public int compareTo(Snake snake) {
		double other=snake.testFitness();
		double tFit = this.testFitness();
		temp = tFit;
		if(tFit < other)
			return 1;
		else if(tFit > other)
			return -1;
		else
			return 0;
	}

	public double testFitness() {
		return this.fit;
	}
	
	public void setFitness(double fit){
		this.fit = fit;
	}

	public void newAnn(newANN ann) {
		this.ann = ann;
		
	}

	public newANN getANN() {
		return this.ann;
	}

}
