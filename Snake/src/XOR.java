import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;

public class XOR extends JFrame{
	
	public JFrame frame;
	public ArrayList<newANN> anns;
	public double highestFitnes = 0;
	public int bestANN = 0;
	
	public static void main(String args[]){
		XOR n = new XOR();
		n.begin();
	}
	
	@SuppressWarnings("unchecked")
	public void begin(){
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
		
		anns = new ArrayList<newANN>();
		for(int i = 0; i < 50; i++){
			anns.add(new newANN(3,1));
		}

		while(highestFitnes < 3 ){
			
			for(int i = 0;i < anns.size(); i ++){
				double val = this.testFitness(anns.get(i));
				if(val > highestFitnes){
					highestFitnes = val;
					bestANN = i;
				}
				else{
					System.out.println("Highest fitness is: " + highestFitnes);
				}
			}	
			
			frame.getContentPane().removeAll();
			frame.add(anns.get(bestANN));
			frame.revalidate();
			frame.repaint();
			
			if(highestFitnes >= 3)
				break;
			
			Collections.sort(anns);
			ArrayList<newANN> newAnns = new ArrayList<newANN>();
			for(int i = 0; i < anns.size()-1; i++){
				Genome g1 = new Genome(anns.get(i));
				Genome g2 = new Genome(anns.get(i+1));
				newANN ann = Genome.breedNewAnn(g1, g2);
				newAnns.add(ann);
			}
			Genome g1 = new Genome(anns.get(0));
			Genome g2 = new Genome(anns.get(1));
			newANN ann = Genome.breedNewAnn(g1, g2);
			newAnns.add(ann);
			
			anns = newAnns;
			
			
		}
		
		System.out.println("Fitness has been reached");
		
		
	}
	
	public double testFitness(newANN ann){
		
		if(ann.dendrites.size() >0){
			int m = 0;
		}
		
		double score = 0;
		double[] inputs = {0,0,2};
		ann.addInputs(inputs);
		double amt = ann.getOutput()[0];
		score += (Math.pow(amt+1,2)*(-1/9))+1;
		
		inputs[0] = 0;
		inputs[1] = 1;
		ann.addInputs(inputs);
		amt = ann.getOutput()[0];
		score += (Math.pow(amt-1,2)*(-1/9))+1;
		
		inputs[0] = 1;
		inputs[1] = 0;
		ann.addInputs(inputs);
		amt = ann.getOutput()[0];
		score += (Math.pow(amt-1,2)*(-1/9))+1;
		
		inputs[0] = 1;
		inputs[1] = 1;
		ann.addInputs(inputs);
		amt = ann.getOutput()[0];
		score += (Math.pow(amt+1,2)*(-1/9))+1;
		
		ann.fitness = score;
		return score;
	}

}
