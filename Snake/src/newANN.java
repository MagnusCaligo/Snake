import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class newANN<T extends NEATInterface> extends JPanel implements KeyListener, Comparable<newANN>{
	
	public ArrayList<Node> nodes;
	public ArrayList<Dendrite> dendrites;
	
	public int inputs;
	public int outputs;
	public int innovation;
	public int nodeAge;
	public double fitness= 0;
	public double parentsFitness = 0;
	public T parent;

	public newANN(int in, int out, T parent){		
		nodes = new ArrayList<Node>();
		this.parent = parent;
		dendrites = new ArrayList<Dendrite>();
		
		inputs = in;
		outputs = out;
		
		for(int i = 0; i<inputs;i++){
			Node n = this.addNewNode();
			innovation--;
			n.type = 1;
		}
		for(int i =0; i< outputs;i++){
			Node n = this.addNewNode();
			innovation--;
			n.type = 2;
		}


		this.setVisible(true);
		
	}
	
	public newANN(ArrayList<Node> nodes, ArrayList<Dendrite> dendrites, int i, int o, T parent){
		
		this.nodes = nodes;
		this.dendrites = dendrites; 
		this.inputs = i;
		this.outputs = o;
		this.parent = parent;

		this.setVisible(true);
	}
	
	public void update(){
		ArrayList<ArrayList<Node>> nodes2d = this.convertTo2D();
		
		for(int x = 0; x < nodes2d.size(); x++){
			for(int y = 0; y < nodes2d.get(x).size(); y++){
				double value = 0;
				for(double v: nodes2d.get(x).get(y).input)
					value +=v;
				if(nodes2d.get(x).get(y).type!=1)
					value = Math.tanh(value);
				if(nodes2d.get(x).get(y).type==2)
					nodes2d.get(x).get(y).finalValue += value;
				for(Dendrite den : nodes2d.get(x).get(y).outputs){
					if(den.active)
						den.getInput(value);
				}
				nodes2d.get(x).get(y).input = new ArrayList<Double>();
			}
		}
		
	}
	
	public void update(Node node){
		double value = 0;
		for(double v: node.input)
			value +=v;
		if(node.type!=1)
			value = Math.tanh(value);
		
		if(node.type==2)
			node.finalValue += value;
		
		for(Dendrite den : node.outputs){
			if(den.updated == false){
				den.updated = true;
				den.getInput(value);
				this.update(den.outputNode);
			}
		}
		node.input = new ArrayList<Double>();
		if(node.type== 1){
			for(Dendrite den : dendrites){
				den.updated = false;
			}
		}
	}
	
	public Node addNewNode(){
		Node n = new Node(nodeAge);
		nodes.add(n);
		nodeAge++;
		innovation++;
		return n;
	}
	
	public double[] getOutput(){
		double[] values = new double[this.outputs];
		int size = 0;
		for(int i = this.inputs; i < this.outputs+this.inputs; i++){
			values[size] = nodes.get(i).finalValue;
			size++;
		}
		return values;
	}
	
	public void addInputs(double[] inputs){
		for(Node n : nodes){
			n.finalValue = 0;
		}
		for(int i = 0; i < this.inputs; i++){
			nodes.get(i).input.add(inputs[i]);
			//this.update(nodes.get(i));
		}
		this.update();
	}
	
	public void addNodeAtDen(Dendrite den){
		if(!den.active)
			return;
		
	
		den.active = false;
		
		
		Node newNode = this.addNewNode();
		Dendrite newInput = new Dendrite(den.inputNode, newNode, innovation);
		innovation++;
		Dendrite newOutput = new Dendrite(newNode, den.outputNode, innovation);
		innovation++;
		
		newNode.outputs.add(newOutput);
		den.inputNode.outputs.add(newInput);
		dendrites.add(newInput);
		dendrites.add(newOutput);

	}
	
	public void addRandomNode(){
		if(dendrites.size() == 0) return;
		Dendrite den = dendrites.get((int)(Math.random() * dendrites.size()));
		this.addNodeAtDen(den);
	}
	
	public void addRandomDendrite(){
		
		int x = 0;
		do{
			x = (int)(Math.random()*(nodes.size()-1));
		}while(nodes.get(x).type == 2);

		int secondX = 0;
		do{
			secondX = (int)(Math.random()*nodes.size());
		}while(nodes.get(secondX) == nodes.get(x) || nodes.get(secondX).type == 1);
		
		Node inputNode = nodes.get(x);
		Node outputNode = nodes.get(secondX);
		
		for(Dendrite den : dendrites){
			if(inputNode == den.inputNode && outputNode == den.outputNode){
				System.out.println("Dendrite already exists");
				return;
			}
		}
		
		System.out.println("Adding New Dendrite from " + x + " to "+secondX+"      Age: " + innovation);
		Dendrite den = new Dendrite(inputNode, outputNode,innovation);
		innovation++;
		dendrites.add(den);
		
		inputNode.addNewOutput(den);
	}
	
	
	
	public void paintComponent(Graphics g){
		g.setColor(Color.RED);
		
		
		ArrayList<ArrayList<Node>> nodeDrawing = convertTo2D();

		
		int distX = this.getWidth()/(nodeDrawing.size()+1);
		
		for(int i = 0; i < nodeDrawing.size(); i++){
			int distY = this.getHeight()/(nodeDrawing.get(i).size() +1);
			for(int m = 0; m < nodeDrawing.get(i).size(); m++){
				for(Dendrite d: nodeDrawing.get(i).get(m).outputs){
					Node n = d.outputNode;
								
					for(int xN = 0; xN < nodeDrawing.size();xN++){
						for(int yN =0; yN <nodeDrawing.get(xN).size(); yN++){
							if(n==nodeDrawing.get(xN).get(yN) && d.active){
								int dY = this.getHeight()/(nodeDrawing.get(xN).size()+1);
								g.setColor(Color.green);
								g.drawLine(distX+(i*distX), distY+(m*distY), distX + (xN*distX), dY+(yN*dY));
								
								double xHalf = ((distX + (xN*distX)) - (distX+(i*distX)))*2/3;
								double yHalf = ((dY+(yN*dY)) - (distY+(m*distY)))*2/3;
								
								
								double angle = Math.atan(yHalf/xHalf);
								
								if(xHalf < 0){
									angle-=Math.PI;
								}
								
								xHalf += distX+(i*distX);
								yHalf += distY+(m*distY);
								
								g.setColor(Color.MAGENTA);
								
								
								double xVal = (xHalf)+(20*Math.cos(angle+2.61));
								double yVal = (yHalf)+(20*Math.sin(angle+2.61));
								g.drawLine((int)xHalf, (int)yHalf, (int) xVal, (int) yVal);
								xVal = (xHalf)+(20*Math.cos(angle-2.61));
								yVal = (yHalf)+(20*Math.sin(angle-2.61));
								g.drawLine((int)xHalf, (int)yHalf, (int) xVal, (int) yVal);
								
								break;
							}
						}
					}
				}
				
				g.setColor(Color.RED);
				if(nodeDrawing.get(i).get(m).type != 0)
					g.setColor(Color.BLUE);
				g.fillOval(distX + (i*distX)-25, distY+(m*distY)-25, 50, 50);
			}
		}
	}
	

	public void keyPressed(KeyEvent event) {
		switch(event.getKeyCode()){
		case KeyEvent.VK_SPACE:
			
			double chance = Math.random();
			if(chance>.5){
				if(dendrites.size() == 0) break;
				Dendrite den = dendrites.get((int)(Math.random()*dendrites.size()));
				while(!den.active){
					den = dendrites.get((int)(Math.random()*dendrites.size()));
				}
				this.addNodeAtDen(den);
			}else if(chance >0){
				this.addRandomDendrite();     
			}
			
			this.repaint();
			break;
		case KeyEvent.VK_N:
			Genome g = new Genome(this);
			Genome.buildNewANN(g);
			break;
		}
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<ArrayList<Node>> convertTo2D(){
		ArrayList<ArrayList<Node>> nodeDrawing = new ArrayList<ArrayList<Node>>();
		nodeDrawing.add(new ArrayList<Node>());
		
		for(int i = 0; i < nodes.size(); i++){
			nodeDrawing.get(0).add(nodes.get(i));
		}
		
		int column = 0;
		boolean allDrawn = false;
		
		while(!allDrawn){
			nodeDrawing.add(new ArrayList<Node>());
			
			for(int i = nodeDrawing.get(column).size()-1; i >= 0; i--){
				boolean nextColumn = false;
				if(nodeDrawing.get(column).get(i).type == 2) nextColumn = true;
				for(int m = 0; m < nodeDrawing.get(column).size(); m++){
					ArrayList<Dendrite> outputs = nodeDrawing.get(column).get(m).outputs;
					for(int t = 0; t < outputs.size(); t++){
						if(outputs.get(t).outputNode == nodeDrawing.get(column).get(i)){
							nextColumn = true;
							break;
						}
					}
					if(nextColumn) break;
				}
				if(nextColumn){
					nodeDrawing.get(column+1).add(nodeDrawing.get(column).get(i));
					nodeDrawing.get(column).remove(i);
				}else{
					nodeDrawing.get(column).get(i).beenDrawn = true;
				}
				
			}
			column++;
			allDrawn = true;
			for(int x = 0; x < nodes.size(); x++){
				if(nodes.get(x).beenDrawn==false && nodes.get(x).type != 2){
					allDrawn = false;
					break;
				}
			}
			
		}
		
		for(int i = 0; i < nodes.size(); i++){
			nodes.get(i).beenDrawn = false;
		}
	
	return nodeDrawing;

	}
	
	public int getLastInnovation(){
		return innovation;
	}

	public int compareTo(newANN ann) {
		double other=(ann).fitness;
		if(this.fitness < other)
			return 1;
		if(this.fitness > other)
			return -1;
		else
			return 0;
	}
}