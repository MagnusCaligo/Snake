import java.util.ArrayList;

public class Genome {

	private ArrayList<Integer> inputs;
	private ArrayList<Integer> outputs;
	private ArrayList<Double> weights;
	private ArrayList<Boolean> active;
	private ArrayList<Integer> type;
	private ArrayList<Node> nodes;
	private ArrayList<Dendrite> dendrites;
	private ArrayList<Gene> genes;
	private int numInputs;
	private int numOutputs;
	
	
	
	public Genome(newANN network){
		genes = new ArrayList<Gene>();
		numInputs = network.inputs;
		numOutputs = network.outputs;
		
		for(int i =0; i < network.dendrites.size(); i++){
			Dendrite d = network.dendrites.get(i);
			Gene g = new Gene(d.age, d.inputNode.age, d.outputNode.age, d.weight, d.active);
			genes.add(g);
			//System.out.println("Created Gene: Age: " + g.innovation + "  Input: " + g.input+ "  Output: " + g.output);
		}
		

	}
	
	public static newANN buildNewANN(Genome g){
		//return null;
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		ArrayList<Dendrite> dendrites = new ArrayList<Dendrite>();
		int nodeAge = 0;
		
		for(int i = 0; i < g.numInputs; i++){
			Node n = new Node(nodeAge);
			nodeAge++;
			n.type = 1;
			nodes.add(n);
		}
		for(int i = 0; i < g.numInputs; i++){
			Node n = new Node(nodeAge);
			nodeAge++;
			n.type = 2;
			nodes.add(n);
		}
		
		for(Gene gene: g.getGenes()){
			Node output;
			Node input = nodes.get(gene.input);
			if(gene.output >= nodeAge){
				output = new Node(nodeAge);
				nodeAge++;
				nodes.add(output);
			}else{
				output = nodes.get(gene.output);
			}
			
			Dendrite den = new Dendrite(input, output, gene.innovation, gene.weight);
			if(!gene.active){
				den.active = false;
			}
			input.addNewOutput(den);
			dendrites.add(den);
			
		}
		
		newANN ann= new newANN(nodes,dendrites,g.numInputs,g.numOutputs);
		ann.innovation = g.getGenes().get(g.getGenes().size()-1).innovation;
		ann.nodeAge = nodeAge;
		return ann;
	}
	
	
	public Genome(ANN network){
		
		
		inputs = new ArrayList<Integer>();
		outputs = new ArrayList<Integer>();
		weights = new ArrayList<Double>();
		active = new ArrayList<Boolean>();
		type = new ArrayList<Integer>();
		nodes = new ArrayList<Node>();
		
		ArrayList<Node> inputNodes = new ArrayList<Node>();
		ArrayList<Node> outputNodes = new ArrayList<Node>();
		
		for(int i = 0; i < network.nodes.get(0).size(); i++){
			inputNodes.add(network.nodes.get(0).get(i));
		}
		for(int i = 0; i < network.nodes.get(network.nodes.size()-1).size();i++){
			outputNodes.add(network.nodes.get(network.nodes.size()-1).get(i));
		}
		
		for(Dendrite den : network.dendrites){
			inputs.add(den.inputNode.age);
			outputs.add(den.outputNode.age);
			weights.add(den.weight);
			active.add(den.active);
			
			type.add(0);
			for(Node n : inputNodes){
				if(den.inputNode == n){
					type.set(type.size()-1, 1);
					for(Node m : outputNodes){
						if(den.outputNode == m){
							type.set(type.size()-1, 3);
						}
					}
				}
			}
			for(Node n : outputNodes){
				if(den.outputNode == n){
					type.set(type.size()-1, 2);
					for(Node m : inputNodes){
						if(den.inputNode == m){
							type.set(type.size()-1, 3);
						}
					}
				}
			}
			
		}
		
		
	}
	



	public static ANN buildANN(Genome genome){
		
		ArrayList<ArrayList<Node>> nodes = new ArrayList<ArrayList<Node>>();
		ArrayList<Dendrite> dendrites = new ArrayList<Dendrite>();
		int age = 0;
		
		for(int i =0; i < genome.type.size(); i++){
			if(!genome.active.get(i))
				continue;
			System.out.println(genome.type.get(i));
			boolean created = false;
			switch(genome.type.get(i)){
			case 1:
				for(int m = 0; m < i; m++){
					if(genome.inputs.get(m) == genome.inputs.get(i))
						created = true;
				}
				if(!created){
					if(nodes.size() ==0)
						nodes.add(new ArrayList<Node>());
					System.out.println("Creating 1");
					nodes.get(0).add(new Node(age));
					age++;
				}	
				System.out.println("Creating a 1");
				break;
			case 2:
				for(int m = 0; m < i; m++){
					if(genome.outputs.get(m) == genome.outputs.get(i))
						created = true;
				}
				if(!created){
					if(nodes.size() < 1)
						nodes.add(new ArrayList<Node>());
					System.out.println("Creating 2");
					nodes.get(nodes.size()-1).add(new Node(age));
					age++;
				}
				System.out.println("Creating a 2");
				break;
			case 3:
				
				boolean inputCreated = false;
				boolean outputCreated = false;
				
				for(int m = 0; m < nodes.size(); m++){
					if(genome.inputs.get(m) == genome.inputs.get(i))
						inputCreated = true;
				}
				for(int m = 0; m < i; m++){
					if(genome.outputs.get(m) == genome.outputs.get(i))
						outputCreated = true;
				}
				
				if(nodes.size() > 0){
					if(nodes.get(0).size()-1>=i){
						inputCreated = true;
					}
				}else{ 
					nodes.add(new ArrayList<Node>());
					inputCreated = false;
				}
				System.out.println(inputCreated);
				
				
				if(!inputCreated){
					if(nodes.size()-1 < 0)
						nodes.add(new ArrayList<Node>());
					nodes.get(0).add(new Node(age));
					System.out.println("Creating 3 input");
					age++;
				}
				if(!outputCreated){
					if(nodes.size()-1 < 1)
						nodes.add(new ArrayList<Node>());		
					System.out.println("Creating 3 output");
					nodes.get(nodes.size()-1).add(new Node(age));
					age++;
				}
				break;
			}
		}
		
		return new ANN(nodes, dendrites);
		
	}
	
	public static Genome breed(ANN network1, ANN network2){
		return null;
		
	}
	
	public ArrayList<Gene> getGenes(){
		return genes;
	}
	
	static class Gene{
		
		public int innovation;
		public int input;
		public int output;
		public double weight;
		public boolean active;
	
		public Gene(int i, int in, int out, double w, boolean a){
			innovation = i;
			input = in;
			output = out;
			weight = w;
			active = a;
		}
		
		public static void combineGenes(){
			
		}
		
	}
	
}
