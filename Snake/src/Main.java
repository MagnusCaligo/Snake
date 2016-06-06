import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main extends JPanel implements KeyListener{
	
	public static JFrame frame;
	public static int gridWidth = 20;
	public static int gridHeight = 20;
	public static int gridBoxSize = 20;
	
	public Snake snake;
	
	public static void main(String args[]){
		Main n = new Main();
		n.begin();
	}
	
	public void begin(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
		
		frame.add(this);
		this.setVisible(true);
		frame.repaint();
		this.repaint();
		
		frame.addKeyListener(this);
		
		snake = new Snake();
		this.add(snake);

		while(true){
			
			if(!snake.failed)
				snake.update();
			else
				snake = new Snake();
			
			try{
				Thread.sleep(1);
			}
			catch(Exception e){}
			
			frame.repaint();
			this.repaint();
		}
		
	}
	
	public void paintComponent(Graphics g){
		int startingX =	(this.frame.getWidth()/2);
		startingX -= this.gridBoxSize*(this.gridWidth/2);
		
		int startingY = this.frame.getHeight()/2;
		startingY -= this.gridBoxSize*(this.gridHeight/2);
				
		g.setColor(Color.BLUE);
		for(int i = 0; i < this.gridWidth;i++){
			for(int m =0; m<this.gridHeight;m++){
				g.drawRect(startingX + (this.gridBoxSize*i), startingY + (this.gridBoxSize*m), this.gridBoxSize, this.gridBoxSize);
			}
		}
		
		snake.paintComponent(g);
		
	}

	public void keyPressed(KeyEvent event) {
		switch(event.getKeyCode()){
		case KeyEvent.VK_LEFT:
			snake.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			snake.moveRight();
			break;
		case KeyEvent.VK_DOWN:
			snake.moveDown();
			break;
		case KeyEvent.VK_UP:
			snake.moveUp();
			break;
		case KeyEvent.VK_SPACE:
			snake.increaseLength();
			break;
		}
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
