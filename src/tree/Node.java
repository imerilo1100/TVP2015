package tree;

import java.util.List;
import java.util.ArrayList;

public class Node {
	private String label = ""; 
	private List<Node> children = new ArrayList<Node>(); 
	private int x;
	private int y; 
	
	//Constructors. 
	Node(){}
	Node(String label){
		this.label = label;
	}
	Node(int x, int y){
		this.x=x; 
		this.y=y;
	}
	Node(String label, int x, int y){
		this.label = label;
		this.x=x; 
		this.y=y;
	}
	
	//Getters, setters.
	public List<Node> getChildren() {return children;} 
	public int getX() {return x;} 
	public int getY() {return y;} 
	//Adding and removal. 
	public void addChild(Node node) {children.add(node);} 
	
	@Override
	public String toString() {
		return label + children;
	}
	
}
