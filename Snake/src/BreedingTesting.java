import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;



public class BreedingTesting extends JFrame implements KeyListener{
	public newANN ann1;
	public newANN ann2;
	public static JFrame frame;
	public JFrame frame1;
	public JFrame frame2;
	
	
	public static void main(String args[]){
		BreedingTesting bt = new BreedingTesting();
		bt.start();
		
	}
	
	public void start(){
		
		ArrayList<Gene> gene1 = new ArrayList<Gene>();
		gene1.add(new Gene(1,0,3, 1, true));
		gene1.add(new Gene(2,1,3, 1, false));
		gene1.add(new Gene(3,2,3, 1, true));
		gene1.add(new Gene(4,1,4, 1, true));
		gene1.add(new Gene(5,4,3, 1, true));
		gene1.add(new Gene(8,0,4, 1, true));
		Genome g1 = new Genome(gene1, 3, 1);
		
		ArrayList<Gene> gene2 = new ArrayList<Gene>();
		gene2.add(new Gene(1,0,3, 1, true));
		gene2.add(new Gene(2,1,3, 1, false));
		gene2.add(new Gene(3,2,3, 1, true));
		gene2.add(new Gene(4,1,4, 1, true));
		gene2.add(new Gene(5,4,3, 1, false));
		gene2.add(new Gene(6,4,5, 1, true));
		gene2.add(new Gene(7,5,3, 1, true));
		gene2.add(new Gene(9,2,4, 1, true));
		gene2.add(new Gene(10,0,5, 1, true));
		
		Genome g2 = new Genome(gene2, 3, 1);
		
		ann1 =  new newANN(2,1);
		ann2 =  new newANN(2,1);
		
//		ann1 =  Genome.buildNewANN(g1);
//		ann2 =  Genome.buildNewANN(g2);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
		frame.addKeyListener(this);
		
		frame1 = new JFrame("Frame 1");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(640, 480);
		frame1.setVisible(true);
		frame1.add(ann1);

		
		frame2 = new JFrame("Frame 2");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(640, 480);
		frame2.setVisible(true);
		frame2.add(ann2);
	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent event) {
		switch(event.getKeyCode()){
		case KeyEvent.VK_T:
			Genome g1 = new Genome(ann1);
			Genome g2 = new Genome(ann2);
			
			frame1.remove(ann1);
			ann1 = ann2;
			frame1.add(ann1);
			
			frame2.remove(ann2);
			ann2 = Genome.breedNewAnn(g2, g1);
			frame2.add(ann2);
			
			frame2.revalidate();
			frame1.revalidate();
			frame1.repaint();
			frame2.repaint();
			
			break;
		case KeyEvent.VK_U:
			ann1.nodes.get(0).input.add((double) 5);
			ann1.nodes.get(0).input.add((double) 1);
			ann1.nodes.get(0).input.add((double) 2);
			ann1.update(ann1.nodes.get(0));
			ann1.update(ann1.nodes.get(1));
			ann1.update(ann1.nodes.get(2));
			System.out.println(ann1.nodes.get(3).finalValue);
			break;
		}
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
