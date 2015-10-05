package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import tree.CreateTree;
import tree.Tree;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Main extends Application {
	AnchorPane root = new AnchorPane();
	Pane statsPane  = new Pane();
	Pane edgeCanvas = new Pane();
	Pane nodeCanvas = new Pane();
	HBox toolbar    = new HBox(); 
	Tree tree; 
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//Set scene, title and style. 
			Scene scene = new Scene(root,1000,650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Visualization of the Properties of Random Spanning Trees");
			primaryStage.setScene(scene);
			primaryStage.show();
			//Set relative pane positions.
			AnchorPane.setTopAnchor(toolbar, 0.0);
			AnchorPane.setTopAnchor(edgeCanvas, 30.0);
			AnchorPane.setTopAnchor(nodeCanvas, 30.0);
			AnchorPane.setTopAnchor(statsPane, 30.0);
			AnchorPane.setBottomAnchor(edgeCanvas, 0.0);
			AnchorPane.setBottomAnchor(nodeCanvas, 0.0);
			AnchorPane.setBottomAnchor(statsPane, 0.0);
			AnchorPane.setRightAnchor(edgeCanvas, 0.0);
			AnchorPane.setRightAnchor(nodeCanvas, 0.0);
			AnchorPane.setLeftAnchor(edgeCanvas, 320.0);
			AnchorPane.setLeftAnchor(nodeCanvas, 320.0);
			AnchorPane.setLeftAnchor(statsPane, 0.0);
			//Add panes to the tree. 
			root.getChildren().addAll(statsPane, toolbar, edgeCanvas, nodeCanvas);
			//Create and draw arbitrary initial tree. 
			//TODO Created tree should probably be more random than this; replace function.
			tree = CreateTree.CreateCompleteTree1(5, 2, 680, 620); //TODO actual finding height. 
			DrawingUtils.drawTree(tree, nodeCanvas, edgeCanvas); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}