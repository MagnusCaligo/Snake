//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//
//import javax.swing.JFrame;
//
//public class XOR extends JFrame{
//	
//	public JFrame frame;
//	public ArrayList<newANN> anns;
//	public double highestFitnes = 0;
//	public int bestANN = 0;
//	public newANN best;
//	
//	public static void main(String args[]){
//		XOR n = new XOR();
//		n.begin();
//	}
//	
//	@SuppressWarnings("unchecked")
//	public void begin(){
//		
//		frame = new JFrame();
//		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		frame.setSize(640, 480);
//		frame.setVisible(true);
//		
//		anns = new ArrayList<newANN>();
//		for(int i = 0; i < 500; i++){
////			anns.add(new newANN(3,1));
//		}
//		
//		double max = 3.9;
//
//		while(highestFitnes <= max ){
//			
//			for(int i = 0;i < anns.size(); i ++){
//				double val = this.testFitness(anns.get(i));
//				if(val > highestFitnes){
//					highestFitnes = val;
//					frame.setTitle(Double.toString(highestFitnes));
//					bestANN = i;
//					FileWriter file = null;
//					best = anns.get(i);
//					try {
//						file = new FileWriter("Genome.txt", false);
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					BufferedWriter out = new BufferedWriter(file);
//					Genome g = new Genome(anns.get(i));
////					for(Gene gene : g.getGenes()){
//						try {
////							out.write("gene.add(new Gene(" + gene.innovation+", "+gene.input+", "+gene.output+", "+gene.weight+", "+gene.active +")); \n");
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//					try {
////						out.close();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}	
//			
//			frame.getContentPane().removeAll();
//			frame.add(anns.get(bestANN));
//			frame.revalidate();
//			frame.repaint();
//			
//			System.out.println("Highest fitness is: " + highestFitnes);
//			
//			if(highestFitnes >= max)
//				break;
//			
//			Collections.sort(anns);
//			ArrayList<newANN> newAnns = new ArrayList<newANN>();
//			for(int i = 0; i < anns.size()-1; i++){
//				Genome g1 = new Genome(anns.get(i));
//				Genome g2 = new Genome(anns.get(i+1));
//				newANN ann = Genome.breedNewAnn(g1, g2);
//				newAnns.add(ann);
//			}
//			
//			Genome g1 = new Genome(anns.get(0));
//			Genome g2 = new Genome(anns.get(1));
//			newANN ann = Genome.breedNewAnn(g1, g2);
//			newAnns.add(ann);
//			
//			anns = newAnns;
//			
//			
////		}
//		
////		best.nodes.get(3).finalValue = 2;
//		
////		Genome g = new Genome(best);
//		best = Genome.buildNewANN(g);
//		
//		frame.getContentPane().removeAll();
//		frame.add(best);
//		frame.revalidate();
//		frame.repaint();
//		
//		System.out.println("Fitness has been reached");
//		double[] inputs = {1,0,1};
//		best.addInputs(inputs);
//		System.out.println(best.getOutput()[0]);
//		
//		inputs[0] = 1;
//		inputs[1] = 1;
//		best.addInputs(inputs);
//		System.out.println(best.getOutput()[0]);
//		
//		inputs[0] = 0;
//		inputs[1] = 0;
//		best.addInputs(inputs);
//		System.out.println(best.getOutput()[0]);
//		
//		inputs[0] = 0;
//		inputs[1] = 1;
//		best.addInputs(inputs);
//		System.out.println(best.getOutput()[0]);
//		
//		
//	}
//	
//	public double testFitness(newANN ann){
//		
//		double score = 0;
//		double[] inputs = {1,0,1};
//		ann.addInputs(inputs);
//		double amt = ann.getOutput()[0];
//		double temp = 1;
//		temp -= Math.pow(Math.pow(1 - amt,2),.5);
//		score += temp;
//		//score += ((Math.pow(amt-10, 2)*-1)/(Math.pow(amt-10, 2)+1))+1;
//		//score += ((Math.pow(amt+10, 2)*-1)/(Math.pow(amt+10, 2)+1))+1;
//		//score += (Math.pow(amt-2,2)*-1)+1;
//		
//		inputs[0] = 0;
//		inputs[1] = 0;
//		ann.addInputs(inputs);
//		amt = ann.getOutput()[0];
//		temp = 1;
//		temp -= Math.pow(Math.pow(1+amt,2),.5);
//		score += temp;
//	
//		inputs[0] = 0;
//		inputs[1] = 1;
//		ann.addInputs(inputs);
//		amt = ann.getOutput()[0];
//		temp = 1;
//		temp -= Math.pow(Math.pow(1 - amt,2),.5);
//		score += temp;
//	
//		inputs[0] = 1;
//		inputs[1] = 1;
//		ann.addInputs(inputs);
//		amt = ann.getOutput()[0];
//		temp = 1;
//		temp -= Math.pow(Math.pow(1+amt,2),.5);
//		score += temp;
//
//		ann.fitness = score;
//
//		
//		return score;
//	}
//
//}
