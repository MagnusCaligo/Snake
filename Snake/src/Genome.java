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
		ArrayList<Dendrite> dendrites = new ArrayList<Dendrite>();
		int age = 0;
		
		for(int i =0; i < genome.type.size(); i++){
			if(genome.type.get(i) == 1){
				boolean created = false;
				for(int m = 0; m < i; m++){
					if(genome.inputs.get(m) == genome.inputs.get(i))
						created = true;
				}
				if(!created){
					if(nodes.size() ==0)
						nodes.add(new ArrayList<Node>());
					nodes.get(0).add(new Node(age));
					age++;
				}	
			}
			else if(genome.type.get(i)==2){
				boolean created = false;
				for(int m = 0; m < i; m++){
					if(genome.outputs.get(m) == genome.outputs.get(i))
						created = true;
				}
				if(!created){
					if(nodes.size() < 1)
						nodes.add(new ArrayList<Node>());
					nodes.get(nodes.size()-1).add(new Node(age));
					age++;
				}
			}
			else if(genome.type.get(i)==3){
				
				boolean inputCreated = false;
				boolean outputCreated = false;
				
				for(int m = 0; m < i; m++){
					if(genome.inputs.get(m) == genome.inputs.get(i))
						inputCreated = true;
				}
				for(int m = 0; m < i; m++){
					if(genome.outputs.get(m) == genome.outputs.get(i))
						outputCreated = true;
				}
				if(!inputCreated && nodes.size()-1 <0){
					nodes.add(new ArrayList<Node>());
					nodes.get(0).add(new Node(age));
					age++;
				}
				if(!outputCreated && nodes.size()-1<0){
					nodes.add(new ArrayList<Node>());				
					nodes.get(nodes.size()-1).add(new Node(age));
					age++;
				}
			}
		}
		
//		for(int i = 0; i < genome.inputs.size(); i++){
//			
//			Node inputNode;
//			int xLoc = 0;
//			for(int x = 0; x < nodes.size(); x++){
//				for(int y = 0; y < nodes.get(x).size(); y++){
//					if(nodes.get(x).get(y).age == genome.inputs.get(i)){
//						inputNode = nodes.get(x).get(y);
//						xLoc = x;
//					}
//				}
//			}
//			
//			Node outputNode=null;
//			for(ArrayList<Node> lis: nodes){
//				for(Node node: lis){
//					if(node.age == genome.outputs.get(i)){
//						outputNode = node;
//					}
//				}
//			}
//			if(outputNode == null){
//				outputNode = new Node(age);
//				age++;
//				nodes.add(xLoc+1, new ArrayList<Node>());
//				nodes.get(xLoc+1).add(outputNode);
//			}
//			
//			
//			
//			if(genome.active.get(i)){
//				
//			}
//			
//		}
		
		return new ANN(nodes, dendrites);
		
	}
	
	public static Genome breed(ANN network1, ANN network2){
		return null;
		
	}
	
}
