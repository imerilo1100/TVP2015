package main.java.tree;

import java.util.List;
import java.util.ArrayList;

public class Node {
	private String label = ""; 
	private Node parent = null; 
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
	Node(Node parent, double x, double y){
		this.parent = parent;
		this.x=x; 
		this.y=y;
	}
	Node(String label, double x, double y){
		this.label = label;
		this.x=x; 
		this.y=y;
	}
	Node(String label, Node parent, double x, double y){
		this.parent = parent;
		this.label = label;
		this.x=x; 
		this.y=y;
	}
	
	//Getters
	public List<Node> getChildren() {return children;} 
	public Node getParent() {return parent;} 
	public double getX() {return x;} 
	public double getY() {return y;} 
	//Setters.
	private void setParent(Node parent) {this.parent=parent;}
	public void setX(double x) {this.x=x;} 
	public void setY(double y) {this.y=y;} 
	//Adding and removal. 
	public void addChild(Node node) {
		children.add(node);
		node.setParent(this);
	} 
	public void removeChild(Node node) {
		children.remove(node);
		node.setParent(null);
	} 
	
	@Override
	public String toString() {
		return label + children;
	}
	
}
