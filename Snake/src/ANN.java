import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ANN extends JPanel{
	
	private ArrayList<ArrayList<Node>> nodes;
	private ArrayList<Dendrite> dendrites;
	
	private JFrame frame;
	private int inputs;
	private int outputs;
	
	
	public static void main(String args[]){
		ANN n = new ANN();
	}
	
	public ANN(){
	
		
		nodes = new ArrayList<ArrayList<Node>>();
		nodes.add(new ArrayList<Node>());
		nodes.add(new ArrayList<Node>());
		
		dendrites = new ArrayList<Dendrite>();
		
		inputs = 3;
		outputs = 3;
		
		for(int i = 0; i<inputs;i++)
			nodes.get(0).add(new Node(true, 0));
		for(int i =0; i< outputs;i++)
			nodes.get(nodes.size()-1).add(new Node(true,0));
		
		int randAmt = 4;
		
		for(int i = (int) (randAmt*Math.random());i<randAmt;i++){
			nodes.add(nodes.size()-1, new ArrayList<Node>());
			for(int p = (int) (randAmt*Math.random()); p<randAmt;p++){
				nodes.get(nodes.size()-2).add(new Node(true,0));
			}
		}
	
		
		
		
		
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
		
		frame.add(this);
		this.setVisible(true);
		
	}
	
	public void addNewNode(Dendrite d){
		
	}
	
	public void addNewDendrite(Dendrite d){
		
	}
	
	public void paintComponent(Graphics g){
		int distX = frame.getWidth()/(nodes.size()+1);
		g.setColor(Color.RED);
		
		
		for(int i = 0; i < nodes.size(); i++){
			int distY = frame.getHeight()/(nodes.get(i).size() +1);
			for(int m = 0; m < nodes.get(i).size(); m++){
				
				for(Dendrite d: nodes.get(i).get(m).outputs){
					Node n = d.outputNode;
								
					for(int xN = 0; xN < nodes.size();xN++){
						for(int yN =0; yN <nodes.get(xN).size(); yN++){
							if(n==nodes.get(xN).get(yN)){
								int dY = frame.getHeight()/(nodes.get(xN).size()+1);
								g.setColor(Color.green);
								g.drawLine(distX+(i*distX), distY+(m*distY), distX + (xN*distX), dY+(yN*dY));
								break;
							}
						}
					}
				}
				
				g.setColor(Color.RED);
				g.fillOval(distX + (i*distX)-25, distY+(m*distY)-25, 50, 50);
			}
		}
	}
	
	
	private class Node{
		
		private boolean active;
		private int age; 
		public ArrayList<Double> input;
		public ArrayList<Dendrite> outputs;
		
		public Node(boolean a, int m){
			active = a;
			age = m;
			
			input = new ArrayList<Double>();
			outputs = new ArrayList<Dendrite>();
		}
		
		public void addNewOutput(Dendrite d){
			outputs.add(d);
		}
		
		public void removeOutput(Dendrite d){
			for(Dendrite m : outputs){
				if(d==m){
					System.out.println("Still need to do this");
					//TODO
				}
			}
		}
		
		
		public void addToInput(double b){
			input.add(b);
		}
		
		public void output(){
			double value = 0;
			for(double val : input){
				value += val;
			}
			
			for(Dendrite d : outputs){
				d.getInput(value);
			}
		}
		
		
	}
	
	private class Dendrite{
		
		private double weight;
		public Node inputNode;
		public Node outputNode;
		
		public Dendrite(Node m,Node n){
			inputNode = m;
			outputNode = n;
			weight = Math.random();
		}
		
		public void getInput(double input){
			double output = weight*input;
			outputNode.addToInput(output);
		}
		
		public Node getNode(){
			return outputNode;
		}
	}

}
