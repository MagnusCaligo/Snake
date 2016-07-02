import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main extends JPanel implements KeyListener{
	
	public static JFrame frame;
	public static int gridWidth = 20;
	public static int gridHeight = 20;
	public static int gridBoxSize = 20;
	public double highestFitness = 0;
	public ArrayList<Snake> snakes;
	public int bestANN;
	public newANN best;
	public boolean testOne = false;
	public NEAT<Snake> neat;
	
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
		
		snakes = new ArrayList<Snake>();
		int m = 500;
		if(testOne)
			m = 1;
		for(int i = 0; i < m; i++){
			snakes.add(new Snake());
			this.add(snakes.get(i));
		}
		
		neat = new NEAT<Snake>(snakes);
		
		ArrayList<Gene> gene = new ArrayList<Gene>();
		gene.add(new Gene(0, 5, 10, 5.984844145503002, false)); 
		gene.add(new Gene(0, 6, 11, 6.142503679664982, true)); 
		gene.add(new Gene(0, 8, 11, -7.965301973608474, false)); 
		gene.add(new Gene(0, 1, 11, -4.804535892472087, true)); 
		gene.add(new Gene(0, 3, 12, 5.779511388400557, true)); 
		gene.add(new Gene(0, 0, 10, -10.58261572368305, true)); 
		gene.add(new Gene(0, 7, 11, 1.708503454158572, true)); 
		gene.add(new Gene(0, 3, 11, 7.2599330864846685, true)); 
		gene.add(new Gene(0, 5, 11, -10.278874912780287, true)); 
		gene.add(new Gene(0, 1, 10, -4.5532430406729905, false)); 
		gene.add(new Gene(1, 3, 13, 1.051671807281832, true)); 
		gene.add(new Gene(2, 13, 10, -2.6786572398825528, true)); 
		gene.add(new Gene(2, 1, 10, -12.959266857204462, false)); 
		gene.add(new Gene(2, 2, 10, 6.056998547249243, true)); 
		gene.add(new Gene(2, 0, 10, -6.8220019289415745, false)); 
		gene.add(new Gene(2, 0, 10, -3.990821138255884, true)); 
		gene.add(new Gene(2, 1, 9, 1.9285981572362902, true)); 
		gene.add(new Gene(2, 3, 10, -7.99346445959757, true)); 
		gene.add(new Gene(3, 0, 14, 3.037556321591572, true)); 
		gene.add(new Gene(4, 14, 12, 4.768679445467744, true)); 
		gene.add(new Gene(4, 2, 14, -3.208512346343044, true)); 
		gene.add(new Gene(4, 7, 10, 2.1442687455322753, false)); 
		gene.add(new Gene(5, 13, 15, -2.8313638128113796, true)); 
		gene.add(new Gene(6, 15, 11, 5.705759097018572, true)); 
		gene.add(new Gene(6, 3, 9, -9.725480576244859, true)); 



 



		Genome g = new Genome<Snake>(gene, 9, 4, snakes.get(0));
		newANN ann = Genome.buildNewANN(g);
		
//		for(int i = 0; i < snakes.size(); i++)
//			snakes.get(i).ann = ann;
		
		
		if(testOne)
			snakes.get(0).ann = ann;
		
		int generation = 1;
		while(true){
			System.out.println("Generation: " + generation);
			generation++;
			boolean allFailed = false;
			
			while(!allFailed){
				
				frame.setTitle(Double.toString(neat.getHighestFitness()) + "      :     " + Double.toString(neat.getAverageFitness()));
				
				for(int i = 0; i < snakes.size(); i++){
					if(!snakes.get(i).failed){
						snakes.get(i).update();
					}
				}
				try{
					if(testOne)
						Thread.sleep(100);
					else
						Thread.sleep(1);
				}
				catch(Exception e){}
				
				frame.repaint();
				this.repaint();
				allFailed = true;
				for(int i = 0; i < snakes.size(); i++){
					if(snakes.get(i).failed == false){
						allFailed = false;
						break;
					}
				}
				
			}
			
			if(testOne)
				System.exit(0);
			
			neat.nextGeneration();
			frame.setTitle(Double.toString(neat.getHighestFitness()));
			for(int i = 0; i < snakes.size(); i++){
				
				Snake s = new Snake();
				s.ann = snakes.get(i).ann;
				s.ann.parent = s;
				snakes.set(i, s);
			}
			
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
		for(int i = 0; i < snakes.size(); i++)
			snakes.get(i).paintComponent(g);
		
	}
	
//	public void evaluate(){
//		for(int i = 0;i < snakes.size(); i ++){
//			double val = snakes.get(i).testFitness();
//			if(val > highestFitness){
//				highestFitness = val;
//				frame.setTitle(Double.toString(highestFitness));
//				bestANN = i;
//				FileWriter file = null;
//				best = snakes.get(i).ann;
//				try {
//					file = new FileWriter("Genome.txt", false);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				BufferedWriter out = new BufferedWriter(file);
//				Genome g = new Genome(snakes.get(i).ann);
//				for(Gene gene : g.getGenes()){
//					try {
//						out.write("gene.add(new Gene(" + gene.innovation+", "+gene.input+", "+gene.output+", "+gene.weight+", "+gene.active +")); \n");
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				try {
//					out.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}	
//		
//		Collections.sort(snakes);
//		ArrayList<Snake> newSnakes = new ArrayList<Snake>();
//		for(int i = 0; i < snakes.size()-1; i++){
//			Genome g1 = new Genome(snakes.get(i).ann);
//			Genome g2 = new Genome(snakes.get(i+1).ann);
//			newANN ann = Genome.breedNewAnn(g1, g2);
//			Snake s = new Snake();
//			s.ann = ann;
//			newSnakes.add(s);
//		}
//		
//		Genome g1 = new Genome(snakes.get(0).ann);
//		Genome g2 = new Genome(snakes.get(1).ann);
//		newANN ann = Genome.breedNewAnn(g1, g2);
//		Snake s = new Snake();
//		s.ann = ann;
//		newSnakes.add(s);
//		
//		snakes = newSnakes;
//	}

	public void keyPressed(KeyEvent event) {
		switch(event.getKeyCode()){
		case KeyEvent.VK_LEFT:
			snakes.get(0).moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			snakes.get(0).moveRight();
			break;
		case KeyEvent.VK_DOWN:
			snakes.get(0).moveDown();
			break;
		case KeyEvent.VK_UP:
			snakes.get(0).moveUp();
			break;
		case KeyEvent.VK_SPACE:
			for(Snake s : snakes){
				s.failed = true;
			}
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
