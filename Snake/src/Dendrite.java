
public class Dendrite {
	public double weight;
	public Node inputNode;
	public Node outputNode;
	public boolean active;
	public int age;
	
	public Dendrite(Node m,Node n, int a){
		inputNode = m;
		outputNode = n;
		weight = Math.random();
		active = true;
		age = a;
	}
	
	public Dendrite(Node m, Node n, int a,double w){
		this(m,n,a);
		this.weight = w;
	}
	
	public void getInput(double input){
		double output = weight*input;
		outputNode.addToInput(output);
	}
}
