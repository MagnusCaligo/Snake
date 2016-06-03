import java.util.ArrayList;

public class Genome {

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
		for(int i = 0; i < g.numOutputs; i++){
			Node n = new Node(nodeAge);
			nodeAge++;
			n.type = 2;
			nodes.add(n);
		}
		
		for(Gene gene: g.getGenes()){
			Node output;
			Node input;
			if(gene.input >= nodes.size()){
				input = new Node(gene.input);
			}else{
				input = nodes.get(gene.input);
			}
			
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
	
	public Genome(ArrayList<Gene> genes, int inputs, int outputs){
		this.genes = genes;
		this.numInputs = inputs;
		this.numOutputs = outputs;
	}
	
	public static newANN breedNewAnn(Genome network1, Genome network2){
		int innovation = network1.getGenes().size();
		ArrayList<Gene> genes = new ArrayList<Gene>();
		
		ArrayList<Gene> gg1 = network1.getGenes();
		ArrayList<Gene> gg2 = network2.getGenes();
		
		int net1loc = 0;
		int net2loc = 0;
		
		while(net1loc != innovation){
			Gene g1 = network1.getGenes().get(net1loc);
			Gene g2;
			if(net2loc < network2.getGenes().size())
				g2 = network2.getGenes().get(net2loc);
			else
				g2 = g1;
			
			if(g1.innovation > g2.innovation){
				genes.add(g2);
				net2loc++;
			}else if(g1.innovation < g2.innovation){
				genes.add(g1);
				net1loc++;
			}else{
				double rand = Math.random();
				if(rand > .5){
					genes.add(g1);
				}else{
					genes.add(g2);
				}
				net1loc++;
				net2loc++;
			}
		}
		
		Genome genome = new Genome(genes, network1.numInputs, network1.numOutputs);
		
		return Genome.buildNewANN(genome);
	}
	
	
	public ArrayList<Gene> getGenes(){
		return genes;
	}
	
}
