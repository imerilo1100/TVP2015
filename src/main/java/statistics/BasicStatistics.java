package statistics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tree.Node;
import tree.Tree;

public class BasicStatistics {
	public static int countNodes(Tree tree){
		Node root = tree.getRoot(); 
		if (root!=null) {
			int count = 1; 
			List<Node> nodes = new ArrayList<Node>();
			nodes.addAll(root.getChildren()); 
			while (!nodes.isEmpty()) {
				count += nodes.size(); 
				List<Node> temp = new ArrayList<Node>();
				temp.addAll(nodes);
				nodes.clear(); 
				for (Node node : temp) {nodes.addAll(node.getChildren());}
			}
			return count; 
		}
		else return 0; 
	}

	public static ArrayList<Integer> countNodesAndLeaves(Tree tree){
		Node root = tree.getRoot(); 
		if (root!=null) {
			int countNodes = 1;
			int countLeaves = 0; 
			List<Node> nodes = new ArrayList<Node>();
			nodes.addAll(root.getChildren()); 
			if (nodes.isEmpty()) {countLeaves = 1;}
			else while (!nodes.isEmpty()) {
				countNodes += nodes.size(); 
				List<Node> temp = new ArrayList<Node>();
				temp.addAll(nodes);
				nodes.clear(); 
				for (Node node : temp) {
					if (node.getChildren().isEmpty()) {countLeaves += 1;}
					else nodes.addAll(node.getChildren());
				}
			}
			return new ArrayList<Integer>(Arrays.asList(countNodes, countLeaves)); 
		}
		else return new ArrayList<Integer>(Arrays.asList(0, 0)); 
	}	
	
	public static int countLeaves(Tree tree){
		Node root = tree.getRoot(); 
		if (root!=null) {
			int countLeaves = 0; 
			List<Node> nodes = new ArrayList<Node>();
			nodes.addAll(root.getChildren()); 
			if (nodes.isEmpty()) {countLeaves = 1;}
			else while (!nodes.isEmpty()) {
				List<Node> temp = new ArrayList<Node>();
				temp.addAll(nodes);
				nodes.clear(); 
				for (Node node : temp) {
					if (node.getChildren().isEmpty()) {countLeaves += 1;}
					else nodes.addAll(node.getChildren());
				}
			}
			return countLeaves; 
		}
		else return 0; 
	}
	
}