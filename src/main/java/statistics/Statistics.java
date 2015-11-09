package statistics;

//Java imports
import java.util.LinkedList;
import java.util.List;
//java(fx) imports
import javafx.collections.ObservableList;
//Custom imports
import tree.Node;
import tree.Tree;

public class Statistics { 
	public  LeafCount leafCount = new LeafCount(); //TODO: Check for redundancy. 
	private NodeCount nodeCount = new NodeCount();
	private DiameterCalculator diameterCalculator = new DiameterCalculator(); //TODO: Actually implement. 
	private MaximumDegreeCalculator maximumDegreeCalculator = new MaximumDegreeCalculator();
	
	//TODO: Refactor collectData and display refresh to be separate items for power conservation. 
	public void collectData(Tree tree, ObservableList<StatisticsInstance> statsContent){
		Node root = tree.getRoot(); 
		double countNodes = 0.0;
		double countLeaves = 0.0; 
		double maximumDegree = 0.0;
		if (root!=null) {
			countNodes+=1.0;
			List<Node> nodes = new LinkedList<Node>();
			nodes.addAll(root.getChildren()); 
			maximumDegree = nodes.size(); 
			if (nodes.isEmpty()) {countLeaves = 1.0;}
			else while (!nodes.isEmpty()) {
				countNodes += nodes.size(); 
				List<Node> temp = new LinkedList<Node>();
				temp.addAll(nodes);
				nodes.clear(); 
				for (Node node : temp) {
					if (node.getChildren().isEmpty()) {countLeaves += 1.0;}
					else {
						nodes.addAll(node.getChildren());
						double nodeDegree = node.getChildren().size()+1;
						if (nodeDegree>maximumDegree){maximumDegree=nodeDegree;}
					}
				}
			}
		}
		leafCount.addValue(countLeaves);
		nodeCount.addValue(countNodes);
		maximumDegreeCalculator.addValue(maximumDegree);
		includeStatistics(statsContent); //TODO: Clear and readd is more of a hack?
	}
	
	public void includeStatistics(ObservableList<StatisticsInstance> statsContent) {
		statsContent.clear();
		statsContent.add(nodeCount);
		statsContent.add(leafCount);
		statsContent.add(diameterCalculator);
		statsContent.add(maximumDegreeCalculator);
	}

}
