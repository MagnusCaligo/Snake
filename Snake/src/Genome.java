import java.util.ArrayList;

public class Genome {

	private ArrayList<Integer> inputs;
	private ArrayList<Integer> outputs;
	private ArrayList<Double> weights;
	private ArrayList<Boolean> active;
	
	public Genome(ANN network){
		
		inputs = new ArrayList<Integer>();
		outputs = new ArrayList<Integer>();
		weights = new ArrayList<Double>();
		active = new ArrayList<Boolean>();
		
		for(Dendrite den : network.dendrites){
			inputs.add(den.inputNode.age);
			outputs.add(den.outputNode.age);
			weights.add(den.weight);
			active.add(den.active);
		}
		
		
	}
	
	public static ANN buildANN(Genome genome){
		
		ArrayList<Node> tempArray = new ArrayList<Node>();
		for(int i = 0; i < genome.inputs.size(); i++){
			while(tempArray.size()-1 < i){
				tempArray.add(null);
			}
			tempArray.set(i, new Node(i));
		}
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		for(int i = genome.inputs.size()-1; i >=0; i--){
			
		}
		
		return null;
		
	}
	
	public static Genome breed(ANN network1, ANN network2){
		return null;
		
	}
	
}
