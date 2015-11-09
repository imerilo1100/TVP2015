package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import statistics.BasicStatistics;

public class ModifyTree {
	
	//Adds an edge previously not included in the tree, removes old edges that would form loops. 
	public static void addNewEdge(Tree tree){
		int nodeCount = BasicStatistics.countNodes(tree);
		addNewEdgeImpl(tree.getRoot(), nodeCount);
	}
	public static void addNewEdge(Tree tree, int nodeCount){
		addNewEdgeImpl(tree.getRoot(), nodeCount);
	}
	private static void addNewEdgeImpl(Node startNode, int nodeCount) {
		if (nodeCount<=2) return; //New edges cannot be added. 
		int[] potentialNodes = generate2DifferentRandomInts(nodeCount);
		Node firstNode = fetchNodeByOrder(startNode, potentialNodes[0]); 
		Node secondNode = fetchNodeByOrder(startNode, potentialNodes[1]);
		if (!(firstNode.getChildren().contains(secondNode))) {
			if (secondNode.getParent()!=null) secondNode.getParent().removeChild(secondNode);
			firstNode.addChild(secondNode);
		} else addNewEdgeImpl(startNode, nodeCount);
	}
	private static Node fetchNodeByOrder(Node startNode, int orderOfNode){ 
		if (orderOfNode==0) return startNode; 
		Node nodeToBeFetched = null;
		int  count = 0;
		List<Node> nodes = new LinkedList<Node>();
		nodes.addAll(startNode.getChildren()); 
		while (count<orderOfNode) {
			List<Node> temp = new LinkedList<Node>();
			temp.addAll(nodes);
			nodes.clear(); 
			for (Node node : temp) {
				count++;
				if (count==orderOfNode) {nodeToBeFetched=node; break;}
				nodes.addAll(node.getChildren());
			} 
		}
		return nodeToBeFetched; 
	}
	
	//Random generation functions.
	private static int[] generate2DifferentRandomInts(int upperBound) {
		Random randomGenerator = new Random();
		int[] ints = randomGenerator.ints(2, 0, upperBound).toArray();
		if (ints[0]==ints[1]) {ints = generate2DifferentRandomInts(upperBound);}
		java.util.Arrays.sort(ints);
		return ints; 
	} 

}