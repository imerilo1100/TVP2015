package tree;

import java.io.Serializable;

public class Tree implements Serializable {
	private static final long serialVersionUID = -2737528350745934458L;
	private Node root; 

	//Constructors
	Tree(){this.root=new Node();}
	Tree(Node root){this.root=root;}
	Tree(String root){this.root=new Node(root);}
	
	//Getters and setters. 
	public Node getRoot() {return root;}
	
	@Override
	public String toString() {return root.toString();}

}