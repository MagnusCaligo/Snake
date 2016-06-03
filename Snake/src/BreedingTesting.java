import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;



public class BreedingTesting extends JFrame implements KeyListener{
	public static newANN ann1;
	public static newANN ann2;
	public static JFrame frame;
	
	
	public static void main(String args[]){
		BreedingTesting bt = new BreedingTesting();
		bt.start();
		
	}
	
	public void start(){
		
		ArrayList<Gene> gene1 = new ArrayList<Gene>();
		gene1.add(new Gene(1,0,3, 0, true));
		gene1.add(new Gene(2,1,3, 0, false));
		gene1.add(new Gene(3,2,3, 0, true));
		gene1.add(new Gene(4,1,4, 0, true));
		gene1.add(new Gene(5,4,3, 0, true));
		gene1.add(new Gene(8,0,4, 0, true));
		Genome g1 = new Genome(gene1, 3, 1);
		
		ArrayList<Gene> gene2 = new ArrayList<Gene>();
		gene2.add(new Gene(1,0,3, 0, true));
		gene2.add(new Gene(2,1,3, 0, false));
		gene2.add(new Gene(3,2,3, 0, true));
		gene2.add(new Gene(4,1,4, 0, true));
		gene2.add(new Gene(5,4,3, 0, false));
		gene2.add(new Gene(6,4,5, 0, true));
		gene2.add(new Gene(7,5,3, 0, true));
		gene2.add(new Gene(9,2,4, 0, true));
		gene2.add(new Gene(10,0,5, 0, true));
		
		Genome g2 = new Genome(gene2, 3, 1);
		
//		ann1 =  new newANN();
//		ann2 =  new newANN();
		
		ann1 =  Genome.buildNewANN(g1);
		ann2 =  Genome.buildNewANN(g2);
		
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
		
		frame.addKeyListener(this);
	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent event) {
		switch(event.getKeyCode()){
		case KeyEvent.VK_T:
			System.out.println("Test");
			Genome g1 = new Genome(ann1);
			Genome g2 = new Genome(ann2);
			
			newANN newAnn = Genome.breedNewAnn(g2, g1);
			
			break;
		}
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
