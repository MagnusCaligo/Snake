import java.util.ArrayList;

public class Genome {

	private ArrayList<Integer> inputs;
	private ArrayList<Integer> outputs;
	private ArrayList<Double> weights;
	private ArrayList<Boolean> active;
	private ArrayList<Integer> type;
	private ArrayList<Node> nodes;
	
	
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
				}
				for(Node m : outputNodes){
					if(den.outputNode == m){
						type.set(type.size()-1, 33);
					}
				}
			}
			for(Node n : outputNodes){
				if(den.outputNode == n){
					type.set(type.size()-1, 2);
				}
				for(Node m : inputNodes){
					if(den.inputNode == m){
						type.set(type.size()-1, 3);
					}
				}
			}
			
		}
		
		
	}
	
	public static ANN buildANN(Genome genome){
		
		ArrayList<ArrayList<Node>> nodes = new ArrayList<ArrayList<Node>>();
		int age = 0;
		
		for(int i =0; i < genome.type.size(); i++){
			if(genome.type.get(i) == 1){
				if(nodes.size() ==0)
					nodes.add(new ArrayList<Node>());
				nodes.get(0).add(new Node(age));
				age++;
			}
			else if(genome.type.get(i)==2){
				if(nodes.size() < 1)
					nodes.add(new ArrayList<Node>());
				nodes.get(nodes.size()-1).add(new Node(age));
				age++;
			}
			else if(genome.type.get(i)==3){
				if(nodes.size()-1 <0){
					nodes.add(new ArrayList<Node>());
					nodes.add(new ArrayList<Node>());
				}
				nodes.get(0).add(new Node(age));
				age++;
				nodes.get(nodes.size()-1).add(new Node(age));
				age++;
			}
		}
		
		return new ANN(nodes);
		
	}
	
	public static Genome breed(ANN network1, ANN network2){
		return null;
		
	}
	
}
