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
		Node root = tree.getRoot(); 
		if (root!=null) {
			drawNode(root.getX(), root.getY(), nodeCanvas);
			drawBranches(root,nodeCanvas, edgeCanvas);
		}
	}

	private static void drawBranches(Node parent, Pane nodeCanvas, Pane edgeCanvas) {
		double x1 = parent.getX(); 
		double y1 = parent.getY();
		for (Node child : parent.getChildren()) {
			drawEdge(x1, y1, child.getX(), child.getY(), edgeCanvas);
			drawNode(child.getX(), child.getY(), nodeCanvas);
			drawBranches(child, nodeCanvas, edgeCanvas);
		}
	}
	
	private static void drawNode(double x, double y, Pane nodeCanvas){
		Circle node = new Circle(radius, Color.BLUE);
		node.relocate(x, y);	
	    nodeCanvas.getChildren().add(node);
	}
	
	private static void drawEdge(double x1, double y1, double x2, double y2, Pane edgeCanvas){
		Line edge = new Line(x1+radius, y1+radius, x2+radius, y2+radius);
		edge.setStrokeWidth(2);
	    edgeCanvas.getChildren().add(edge);
	}
	
}
