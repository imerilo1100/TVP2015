package tree;

import java.util.ArrayList;
import java.util.List;

public class CreateTree {

	//TODO: Try to ensure that nodes do not overlap. Currently no check and not optimal node positioning.  
	public static Tree CreateCompleteTree1(int height, int branchesPerNode, double x, double y) {
		Tree tree = new Tree(new Node("X", x/2.0, 5.0)); 
		double heightStep = (y-15.0)/height; 
		double currentHeight = 5; 
		List<Node> nodes = new ArrayList<Node>(); 
		nodes.add(tree.getRoot());
		for (int i = 0; i < height; i++) {
			List<Node> temp = new ArrayList<Node>();
			temp.addAll(nodes);
			nodes.clear(); 
			currentHeight += heightStep;
			double widthStep = (x-5)/branchesPerNode/temp.size();
			double currentWidth = 0.5*widthStep;
			for (Node node : temp) {
				for (int j = 0; j<branchesPerNode; j++) {
					Node newnode = new Node(Integer.toString(i)+"-"+Integer.toString(j),currentWidth,currentHeight);
					currentWidth += widthStep;
					node.addChild(newnode);
					nodes.add(newnode);
				}
			}
		} 
		return tree; 
	}
	
}
