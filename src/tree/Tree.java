package tree;

public class Tree {
	Node root; 

	//Constructors
	Tree(){}
	Tree(Node root){this.root=root;}
	Tree(String root){this.root=new Node("Root");}
	
	//Getters and setters. 
	public Node getRoot() {return root;}
	
	@Override
	public String toString() {return root.toString();}

}
