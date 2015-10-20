package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.Map;
import tree.Node;
import tree.Tree;

public class DrawingUtils {
	static int radius = 4; 
	
	//Draws the entire tree, nodes and edges
	public static void drawEntireTree(Tree tree, Pane nodeCanvas, Pane edgeCanvas, Map<Circle, Node> representationToNodeMap) {
		representationToNodeMap.clear(); 
		nodeCanvas.getChildren().clear();
		edgeCanvas.getChildren().clear();
		Node root = tree.getRoot(); 
		if (root!=null) {
			drawNode(root, nodeCanvas, representationToNodeMap);
			drawBranches(root,nodeCanvas, edgeCanvas, representationToNodeMap);
		}
	}
	
	//Redraws the edges of the tree only - for modifications that don't affect nodes. 
	public static void redrawEdges(Tree tree, Pane edgeCanvas) {
		edgeCanvas.getChildren().clear();
		Node root = tree.getRoot(); 
		if (root!=null) {drawBranches(root, edgeCanvas);}
	}

	//Draws the branches of a fully redrawn tree.
	private static void drawBranches(Node parent, Pane nodeCanvas, Pane edgeCanvas, Map<Circle, Node> representationToNodeMap) {
		for (Node child : parent.getChildren()) {
			drawEdge(parent.getX(), parent.getY(), child.getX(), child.getY(), edgeCanvas);
			drawNode(child, nodeCanvas, representationToNodeMap);
			drawBranches(child, nodeCanvas, edgeCanvas, representationToNodeMap);
		}
	}
	//Draws branches for edges-only redraws.
	private static void drawBranches(Node parent, Pane edgeCanvas) {
		for (Node child : parent.getChildren()) {
			drawEdge(parent.getX(), parent.getY(), child.getX(), child.getY(), edgeCanvas);
			drawBranches(child, edgeCanvas);
		}
	}
	
	//Draws a node and adds it to map. 
	private static void drawNode(Node node, Pane nodeCanvas, Map<Circle, Node> representationToNodeMap){
		Circle drawnNode = new Circle(radius, Color.BLUE);
		drawnNode.relocate(node.getX(), node.getY());
		representationToNodeMap.put(drawnNode, node);
	    nodeCanvas.getChildren().add(drawnNode);
	}
	
	//Draws an edge. 
	private static void drawEdge(double x1, double y1, double x2, double y2, Pane edgeCanvas){
		Line edge = new Line(x1+radius, y1+radius, x2+radius, y2+radius);
		edge.setStrokeWidth(2);
	    edgeCanvas.getChildren().add(edge);
	}
	
}
