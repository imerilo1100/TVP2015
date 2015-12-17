package application;
	
//Java imports. 
import java.util.Timer;
//Java(fx) imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import statistics.Statistics;
//Custom imports
import tree.CreateTree;
import tree.Tree;

public class Main extends Application { 
	//Tools, random variables and data structures. 
	int fastforwardNValue = 1000; //TODO: Give user the ability to choose another value
	int timedStepInterval = 1000; //TODO: Give user the ability to choose another value
	static Statistics stats;
	static Tree tree; 
	StatisticsBase statsBase;
	Timer timer;
	//Panes. 
	AnchorPane root = new AnchorPane();
	Pane statsPane  = new VBox();
	Pane edgeCanvas = new Pane();
	Pane nodeCanvas = new Pane();
	HBox toolbar    = new HBox(); 
	//Buttons.
	Buttons buttons = new Buttons(); 
	Button settingsButton = new Button("Settings");
	Button generateButton = new Button("GenerateTree");
	Button saveButton = new Button("Save");
	Button loadButton = new Button("Load");
	Button exportButton = new Button("Export");
	Button importButton = new Button("Import");
	Button nextButton = new Button("Next");
	Button ffButton2 = new Button("FastForward");
	Button ffButton1 = new Button("FastForward(" + fastforwardNValue + ")");
	Button timedstepButton = new Button("Timed-step");
	Button toggleNodeDragAndDropButton = new Button("Node drag-and-drop [OFF]");
	//Flags. 
	Boolean timedStep = false; 
	Boolean fastForward = false;
	Boolean fastForwardN = false;
	
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
			AnchorPane.setLeftAnchor(toolbar, 0.0);
			AnchorPane.setRightAnchor(toolbar, 0.0);
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
			statsPane.setPrefWidth(320);
			//Add panes to the root pane, populate panes. 
			root.getChildren().addAll(statsPane, toolbar, edgeCanvas, nodeCanvas);
			buttons.addToolbarButtons(this);
			//Create and draw arbitrary initial tree. 
			//TODO: should probably be more random than this. 
			tree = CreateTree.CreateCompleteTreeOffsetCircular(5, 3, 680, 620); //TODO actual finding height. 
			DrawingUtils.drawEntireTree(tree, nodeCanvas, edgeCanvas); 
			//Initialize and display statistics.
			stats = new Statistics();
			statsBase = new StatisticsBase(statsPane);
			stats.collectData(tree, StatisticsBase.getStatsContent());
		} catch(Exception e) {e.printStackTrace();}
	}
	
	public static void main(String[] args) {launch(args);}	
}