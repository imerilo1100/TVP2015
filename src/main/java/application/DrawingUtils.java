package application;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import tree.Node;
import tree.Tree;

public class DrawingUtils {
	static boolean nodeDragAndDropEnabled = false; 
	static int radius = 4; 
	
	//Draws the entire tree, nodes and edges
	public static void drawEntireTree(Tree tree, Pane nodeCanvas, Pane edgeCanvas) {
		nodeCanvas.getChildren().clear();
		edgeCanvas.getChildren().clear();
		Node root = tree.getRoot(); 
		if (root!=null) {
			drawNode(root, nodeCanvas);
			drawBranches(root,nodeCanvas, edgeCanvas);
		}
	}
	
	//Redraws the edges of the tree only - for modifications that don't affect nodes. 
	public static void redrawEdges(Tree tree, Pane edgeCanvas) {
		edgeCanvas.getChildren().clear();
		Node root = tree.getRoot(); 
		if (root!=null) {drawBranches(root, edgeCanvas);}
	}

	//Draws the branches of a fully redrawn tree.
	private static void drawBranches(Node parent, Pane nodeCanvas, Pane edgeCanvas) {
		for (Node child : parent.getChildren()) {
			drawEdge(parent, child, edgeCanvas);
			drawNode(child, nodeCanvas);
			drawBranches(child, nodeCanvas, edgeCanvas);
		}
	}
	//Draws branches for edges-only redraws.
	private static void drawBranches(Node parent, Pane edgeCanvas) {
		for (Node child : parent.getChildren()) {
			drawEdge(parent, child, edgeCanvas);
			drawBranches(child, edgeCanvas);
		}
	}
	
	//Draws a node and enables relocating it. 
	//TODO: edges should move along with node...
	private static void drawNode(Node node, Pane nodeCanvas){
		Circle drawnNode = new Circle(radius, Color.BLUE);
		drawnNode.relocate(node.getX(), node.getY());
		drawnNode.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent mouseEvent) {
				if (nodeDragAndDropEnabled) {
					drawnNode.setCenterX(mouseEvent.getX());
					drawnNode.setCenterY(mouseEvent.getY());
					node.setX(mouseEvent.getSceneX()-320.0-radius);
					node.setY(mouseEvent.getSceneY()-30.0-radius);
				}
			}
		});
	    nodeCanvas.getChildren().add(drawnNode);
	}
	
	//Draws an edge. 
	private static void drawEdge(Node parent, Node child, Pane edgeCanvas){
		Line edge = new Line(parent.getX()+radius,
							 parent.getY()+radius,
							 child.getX()+radius,
							 child.getY()+radius);
		edge.setStrokeWidth(2);
	    edgeCanvas.getChildren().add(edge);
	}
	
}