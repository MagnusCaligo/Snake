import java.util.ArrayList;

public class Node {
	public ArrayList<Double> input;
	public ArrayList<Dendrite> outputs;
	public int age;
	public boolean beenDrawn = false;
	public int type; //0 = Hidden, 1 = Input, 2 = Output
	public double finalValue;
	
	public Node(int a){
		type = 0;
		input = new ArrayList<Double>();
		outputs = new ArrayList<Dendrite>();
		age = a;
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
	
	public double getValue(){
		double value = 0;
		for(double val : input){
			value += val;
		}
		
		value =  Math.tanh(value);
		return value;
	}
}
