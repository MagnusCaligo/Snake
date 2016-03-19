import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ANN extends JPanel implements KeyListener{
	
	private ArrayList<ArrayList<Node>> nodes;
	private ArrayList<Dendrite> dendrites;
	
	private JFrame frame;
	private int inputs;
	private int outputs;
	private int age;
	
	
	public static void main(String args[]){
		ANN n = new ANN();
	}
	
	public ANN(){
	
		
		nodes = new ArrayList<ArrayList<Node>>();
		
		dendrites = new ArrayList<Dendrite>();
		
		inputs = 3;
		outputs = 3;
		
		for(int i = 0; i<inputs;i++){
			this.addNewNode(0, i, age,false);
			age++;
		}
		for(int i =0; i< outputs;i++){
			this.addNewNode(1, i, age,false);
			age++;
		}

		
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
		
		frame.add(this);
		frame.addKeyListener(this);
		this.setVisible(true);
		
	}
	
	public void update(){
		for(int x = 0; x < nodes.size(); x++){
			for(int y = 0; y < nodes.get(x).size(); y++){
			
				System.out.println(x+" : "+ y + " : " + nodes.get(x).get(y).output());
			}
		}
	}
	
	public Node addNewNode(int x, int y, int age, boolean insert){
		Node n = null;
		
		if(!insert){
			if(x >= 0 && x <= nodes.size()-1){
				 n = new Node(age);
				if(y>=0 && y<=nodes.get(x).size()-1)
					nodes.get(x).add(n);
				else
					nodes.get(x).add(n);
			}else if(x>=0 && x==nodes.size()){
				n = new Node(age);
				nodes.add(new ArrayList<Node>());
				nodes.get(nodes.size()-1).add(n);
			}
		}else{
			n = new Node(age);
			if(x>=0 && x <=nodes.size()-1){
				nodes.add(x, new ArrayList<Node>());
				nodes.get(x).add(n);
			}
		}
		
		if(n!= null){
			if(x<nodes.size()-1){
				System.out.println();
				Node output = nodes.get(x+1).get((int)(Math.random()*nodes.get(x+1).size()));
				Dendrite den = new Dendrite(n,output);
				n.addNewOutput(den);
				dendrites.add(den);
			}
			if(x>0){
				ArrayList<Node> outputlist = nodes.get(x-1);
				Node output = outputlist.get((int)(Math.random()*nodes.get(x-1).size()));
				Dendrite den = new Dendrite(output, n);
				output.addNewOutput(den);
				dendrites.add(den);
			}
		}
		
		return n;
	}
	
	public void addNodeAtDen(Dendrite den){
		if(!den.active)
			return;
		double inputX = 0;
		double outputX = 0;
		for(int x = 0; x < nodes.size(); x++){
			for(int y = 0; y <nodes.get(x).size(); y++){
				if(nodes.get(x).get(y) == den.inputNode){
					inputX = x;
				}
			}
		}
		for(int x = 0; x < nodes.size(); x++){
			for(int y = 0; y <nodes.get(x).size(); y++){
				if(nodes.get(x).get(y) == den.outputNode){
					outputX = x;
				}
			}
		}
		den.active = false;
		double xDif = outputX - inputX;
		if(xDif%2!=0){
			int xloc = (int) Math.ceil(xDif/2);	
			xloc += inputX;
			Node n = new Node(age);
			age++;
			
			Dendrite newOutput = new Dendrite(n, den.outputNode);
			Dendrite newInput = new Dendrite(den.inputNode, n);
			newOutput.weight = 1;
			
			den.inputNode.addNewOutput(newInput);;
			n.addNewOutput(newOutput);
			
			dendrites.add(newOutput);
			dendrites.add(newInput);
			
			nodes.add(xloc, new ArrayList<Node>());
			nodes.get(xloc).add(n);
			
		}else{
			int xloc = (int) (xDif/2);
			xloc += inputX;
			Node n = new Node(age);
			age++;
			Dendrite newOutput = new Dendrite(n,den.outputNode);
			Dendrite newInput = new Dendrite(den.inputNode,n);
			
			den.inputNode.addNewOutput(newInput);
			n.addNewOutput(newOutput);
			
			dendrites.add(newInput);
			dendrites.add(newOutput);
			n.addNewOutput(newOutput);
			den.inputNode.addNewOutput(newOutput);
			

			nodes.get(xloc).add(n);
		}
	}
	
	public void addRandomDendrite(){
		int x = (int)(Math.random()*(nodes.size()-1));
		int y = (int)(Math.random()*nodes.get(x).size());
		
		int secondX = 0;
		while(secondX <= x){
			secondX = (int)(Math.random()*nodes.size());
		}
		int secondY = (int)(Math.random()*nodes.get(secondX).size());
		
		Node inputNode = nodes.get(x).get(y);
		Node outputNode = nodes.get(secondX).get(secondY);
		
		for(Dendrite den : dendrites){
			if(inputNode == den.inputNode && outputNode == den.outputNode){
				System.out.println("Dendrite already exists");
				return;
			}
		}
		
		Dendrite den = new Dendrite(inputNode, outputNode);
		dendrites.add(den);
		
		inputNode.addNewOutput(den);
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
							if(n==nodes.get(xN).get(yN) && d.active){
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
		
		private int age; 
		public ArrayList<Double> input;
		private ArrayList<Dendrite> outputs;
		
		public Node(int m){
			age = m;
			
			input = new ArrayList<Double>();
			outputs = new ArrayList<Dendrite>();
		}
		
		public void addNewOutput(Dendrite d){
			outputs.add(d);
		}
		
		
		public void addToInput(double b){
			input.add(b);
		}
		
		public double output(){
			double value = 0;
			for(double val : input){
				value += val;
			}
			
			value =  Math.tanh(value);
			
			for(Dendrite d : outputs){
				d.getInput(value);
			}
			
			input = new ArrayList<Double>();
			return value;
		}
		
		public void update(){
			output();
		}
		
		
	}
	
	private class Dendrite{
		
		private double weight;
		public Node inputNode;
		public Node outputNode;
		public boolean active;
		
		public Dendrite(Node m,Node n){
			inputNode = m;
			outputNode = n;
			weight = Math.random();
			active = true;
		}
		
		public Dendrite(Node m, Node n, double w){
			this(m,n);
			this.weight = w;
		}
		
		public void getInput(double input){
			double output = weight*input;
			outputNode.addToInput(output);
		}
		
	}

	public void keyPressed(KeyEvent event) {
		switch(event.getKeyCode()){
		case KeyEvent.VK_SPACE:
			
			if(Math.random()>.9){
				Dendrite den = dendrites.get((int)(Math.random()*dendrites.size()));
				while(!den.active){
					den = dendrites.get((int)(Math.random()*dendrites.size()));
				}
				this.addNodeAtDen(den);
			}else{
				this.addRandomDendrite();
			}
			
			this.update();
			frame.repaint();
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
