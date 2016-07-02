import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class NEAT<T extends NEATInterface & Comparable<T>> {

	private ArrayList<T> population;
	private ArrayList<newANN<T>> anns;
	private ArrayList<ArrayList<newANN<T>>> species;
	private ArrayList<ArrayList<newANN<T>>> newSpecies;
	private double highestFitness;
	private double averageFitness;
	private double maxDifference = 30;
	private final double C1 = 1;
	private final double C2 = 1;
	private final double C3 = 1;
	private newANN bestANN;
	
	public NEAT(ArrayList<T> pop){
		this.population = pop;
		species = new ArrayList<ArrayList<newANN<T>>>();
		newSpecies = new ArrayList<ArrayList<newANN<T>>>();
		species.add(new ArrayList<newANN<T>>());
		newSpecies.add(new ArrayList<newANN<T>>());
		this.anns = new ArrayList<newANN<T>>();
		for(T obj : population){
			this.anns.add(obj.getANN());
			species.get(0).add(obj.getANN());
		}
	}
	
	public double getHighestFitness(){
		return highestFitness;
	}
	
	public double getAverageFitness(){
		return averageFitness;
	}
	
	public void testChange(newANN<T> ann){
		for(int i = 0; i < newSpecies.size(); i++){
			int excess = 0; 
			int disjoint = 0;
			if(newSpecies.get(i).size() == 0){
				newSpecies.get(i).add(ann);
				return;
			}
			newANN<T> comparable = newSpecies.get(i).get((int)(newSpecies.get(i).size() * Math.random()));
			
			Genome<T> network1 = new Genome<T>(ann);
			Genome<T> network2 = new Genome<T>(comparable);
			
			int innovation = network1.getGenes().size();			
			int net1loc = 0;
			int net2loc = 0;
			double weights = 0;
			int numWeights =0;
			
			while(net1loc != innovation){
				Gene g1 = network1.getGenes().get(net1loc);
				Gene g2;
				if(net2loc < network2.getGenes().size()){
					g2 = network2.getGenes().get(net2loc);
				}
				else{
					excess++;
					net1loc++;
					continue;
				}
				
				if(g1.innovation > g2.innovation){
					net2loc++;
					disjoint++;
				}else if(g1.innovation < g2.innovation){
					net1loc++;
					disjoint++;
				}else{
					weights += g1.weight - g2.weight;
					numWeights++;
					net1loc++;
					net2loc++;
				}
			}
			if(net2loc < network2.getGenes().size()){
				excess += (network2.getGenes().size() - net2loc);
			}
			
			double maxGenes = network1.getGenes().size();
			if(network2.getGenes().size() > maxGenes)
				maxGenes = network2.getGenes().size();
			if(maxGenes == 0 )
				maxGenes = 1;
			
			double delta = 0;
			delta += (this.C1 * excess)/maxGenes;
			delta += (this.C2 * disjoint)/maxGenes;
			if(numWeights == 0)
				numWeights = 1;
			delta += this.C3 * (weights/numWeights);
			
			if(delta > this.maxDifference){
				continue;
			}else{
				newSpecies.get(i).add(ann);
				return;
			}
			
		}
		newSpecies.add(new ArrayList<newANN<T>>());
		newSpecies.get(newSpecies.size()-1).add(ann);
		
	}
	
	public void nextGeneration(){
		averageFitness = 0;
		for(int i = 0;i < population.size(); i ++){
			double val = population.get(i).calculateFitness();
			averageFitness += val;
			if(val > highestFitness){
				highestFitness = val;
				bestANN = population.get(i).getANN();
				FileWriter file = null;
				try {
					file = new FileWriter("Genome.txt", false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedWriter out = new BufferedWriter(file);
				Genome<T> g = new Genome(population.get(i).getANN());
				for(Gene gene : g.getGenes()){
					try {
						out.write("gene.add(new Gene(" + gene.innovation+", "+gene.input+", "+gene.output+", "+gene.weight+", "+gene.active +")); \n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	

		
		for(int i = 0; i < species.size(); i++){
			ArrayList<T> parents = new ArrayList<T>();
			for(int m = 0; m < species.get(i).size(); m++){
				double b = species.get(i).get(m).parent.testFitness();
				//b /= species.get(i).size();
				species.get(i).get(m).parent.setFitness(b);
				averageFitness += species.get(i).get(m).parent.testFitness();
				parents.add(species.get(i).get(m).parent);
			}
			Collections.sort(parents);
			for(int m = 0; m < parents.size()-1; m++){
				Genome<T> g1 = new Genome<T>(parents.get(m).getANN());
				Genome<T> g2 = new Genome<T>(parents.get(m+1).getANN());
				newANN<T> ann = Genome.breedNewAnn(g1, g2);
				parents.get(m).newAnn(ann);
				this.testChange(ann);
			}
			if(parents.size() > 1){
				Genome<T> g1 = new Genome<T>(species.get(i).get(0).parent.getANN());
				Genome<T> g2 = new Genome<T>(species.get(i).get(1).parent.getANN());
				newANN<T> ann = Genome.breedNewAnn(g1, g2);
				species.get(i).get(species.get(i).size() -1).parent.newAnn(ann);
				this.testChange(ann);
			}
		}
		
		averageFitness /= population.size();
		this.species = newSpecies;
		newSpecies = new ArrayList<ArrayList<newANN<T>>>();
		newSpecies.add(new ArrayList<newANN<T>>());
		
//		Collections.sort(population);
//		for(int i = 0; i < population.size()-1; i++){
//			Genome<T> g1 = new Genome<T>(population.get(i).getANN());
//			Genome<T> g2 = new Genome<T>(population.get(i+1).getANN());
//			newANN<T> ann = Genome.breedNewAnn(g1, g2);
//			population.get(i).newAnn(ann);
//			this.testChange(ann);
//		}
//		
//		Genome<T> g1 = new Genome<T>(population.get(0).getANN());
//		Genome<T> g2 = new Genome<T>(population.get(1).getANN());
//		newANN<T> ann = Genome.breedNewAnn(g1, g2);
//		population.get(population.size() -1).newAnn(ann);
//		this.testChange(ann);
//		this.species = newSpecies;
	}
	
}

