package tree;

import java.util.List;
import java.util.ArrayList;

public class Node {
	private String label = ""; 
	private List<Node> children = new ArrayList<Node>(); 
	private double x = 0.0;
	private double y = 0.0; 
	
	//Constructors. 
	Node(){}
	Node(String label){
		this.label = label;
	}
	Node(double x, double y){
		this.x=x; 
		this.y=y;
	}
	Node(String label, double x, double y){
		this.label = label;
		this.x=x; 
		this.y=y;
	}
	
	//Getters, setters.
	public List<Node> getChildren() {return children;} 
	public double getX() {return x;} 
	public double getY() {return y;} 
	//Adding and removal. 
	public void addChild(Node node) {children.add(node);} 
	
	@Override
	public String toString() {
		return label + children;
	}
	
}
