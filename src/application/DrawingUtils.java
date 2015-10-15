package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import tree.Node;
import tree.Tree;

public class DrawingUtils {
	static int radius = 4; 
	
	public static void drawTree(Tree tree, Pane nodeCanvas, Pane edgeCanvas) {
		nodeCanvas.getChildren().clear();
		edgeCanvas.getChildren().clear();
		Node root = tree.getRoot(); 
		if (root!=null) {
			drawNode(root, nodeCanvas);
			drawBranches(root,nodeCanvas, edgeCanvas);
		}
	}

	private static void drawBranches(Node parent, Pane nodeCanvas, Pane edgeCanvas) {
		for (Node child : parent.getChildren()) {
			drawEdge(parent.getX(), parent.getY(), child.getX(), child.getY(), edgeCanvas);
			drawNode(child, nodeCanvas);
			drawBranches(child, nodeCanvas, edgeCanvas);
		}
	}
	
	private static void drawNode(Node node, Pane nodeCanvas){
		Circle drawnNode = new Circle(radius, Color.BLUE);
		drawnNode.relocate(node.getX(), node.getY());
	    nodeCanvas.getChildren().add(drawnNode);
	}
	
	private static void drawEdge(double x1, double y1, double x2, double y2, Pane edgeCanvas){
		Line edge = new Line(x1+radius, y1+radius, x2+radius, y2+radius);
		edge.setStrokeWidth(2);
	    edgeCanvas.getChildren().add(edge);
	}
	
}
