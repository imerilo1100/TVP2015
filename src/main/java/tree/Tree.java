package tree;

public class Tree {
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
