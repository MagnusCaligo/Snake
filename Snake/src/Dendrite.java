
public class Dendrite {
	public double weight;
	public Node inputNode;
	public Node outputNode;
	public boolean active;
	public boolean updated = false;
	public int age;
	
	public Dendrite(Node m,Node n, int a){
		inputNode = m;
		outputNode = n;
		weight = (Math.random()*2)-1;
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
