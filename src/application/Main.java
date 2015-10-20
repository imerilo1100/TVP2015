package application;

//Java imports. 
import java.util.Timer;
import java.util.TimerTask;
import java.util.HashMap;
import java.util.Map;

//Java(fx) imports
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

//Custom imports. 
import tree.CreateTree;
import tree.Tree;
import tree.Node;
import tree.ModifyTree;

public class Main extends Application { 
	//Tools, random variables and data structures. 
	int fastforwardNValue = 1000; //TODO: Give user the ability to choose another value
	int timedStepInterval = 1000; //TODO: Give user the ability to choose another value
	Map<Circle, Node> representationToNodeMap = new HashMap<Circle, Node>();
	Timer timer;
	Tree tree; 
	//Panes. 
	AnchorPane root = new AnchorPane();
	Pane statsPane  = new Pane();
	Pane edgeCanvas = new Pane();
	Pane nodeCanvas = new Pane();
	HBox toolbar    = new HBox(); 
	//Buttons.
	Button nextButton = new Button("Next");
	Button ffButton2 = new Button("FastForward");
	Button ffButton1 = new Button("FastForward(" + fastforwardNValue + ")");
	Button timedstepButton = new Button("Timed-step");
	//Flags. 
	Boolean timedStep = false; 
	Boolean fastForward = false;
	Boolean fastForwardN = false;
	Boolean nodeDragAndDrop = false;
	
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
			//Add panes to the root pane, populate panes. 
			root.getChildren().addAll(statsPane, toolbar, edgeCanvas, nodeCanvas);
			addToolbarButtons();
			//Create and draw arbitrary initial tree. 
			//TODO Created tree should probably be more random than this; replace function.
			tree = CreateTree.CreateCompleteTreeOffsetCircular(5, 3, 680, 620); //TODO actual finding height. 
			DrawingUtils.drawEntireTree(tree, nodeCanvas, edgeCanvas, representationToNodeMap); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//Buttons should have graphics and tooltips?
	private void addToolbarButtons(){
		nextButton.setOnMouseClicked( new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
            	modificationButtonsAllFalse();
				ModifyTree.addNewEdge(tree); 
				DrawingUtils.redrawEdges(tree, edgeCanvas); 
            }
        });
		timedstepButton.setOnMouseClicked( new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
            	if (!timedStep) {
            		modificationButtonsAllFalse();
            		timedstepButton.setText("Timed-step [ON]");
            		timedStep = true; 
            		timer = new Timer(); 
            	    timer.scheduleAtFixedRate(new TimerTask() {
            	        @Override
            	        public void run() {
            	        	Platform.runLater(new Runnable() {
            	        		public void run() {
            	        			ModifyTree.addNewEdge(tree); 
            	        			DrawingUtils.redrawEdges(tree, edgeCanvas);
            	        		}
            	        	});
            	        }
            	    }, 0, timedStepInterval); 
            	} else {modificationButtonsAllFalse();}
            }
        });
		ffButton1.setOnMouseClicked( new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
            	if (!fastForward) {
            		modificationButtonsAllFalse();
            		ffButton1.setText("FastForward(" + fastforwardNValue + ") [ON]");
                	fastForwardN = true;
                	//TODO: Display label informing user about no redraws.
            		for (int i=0; i<fastforwardNValue; i++) {ModifyTree.addNewEdge(tree);}
                	fastForwardN = false;
                	DrawingUtils.redrawEdges(tree, edgeCanvas); 
            		ffButton1.setText("FastForward(" + fastforwardNValue + ")");
            	} else {modificationButtonsAllFalse();}
            }
        });
		ffButton2.setOnMouseClicked( new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
            	if (!fastForward) {
            		modificationButtonsAllFalse();
            		ffButton2.setText("FastForward [ON]");
            		fastForward = true; 
            		timer = new Timer();
            		//TODO: Display label informing user about no redraws. 
            	    timer.scheduleAtFixedRate(new TimerTask() {
            	        @Override
            	        public void run() {
            	        	Platform.runLater(new Runnable() {
            	        		public void run() {ModifyTree.addNewEdge(tree);}
            	        	});
            	        }
            	    }, 0, 1); 
            	    DrawingUtils.redrawEdges(tree, edgeCanvas); 
            	} else {modificationButtonsAllFalse();}
            }
        });
		toolbar.getChildren().addAll(nextButton,timedstepButton,ffButton1,ffButton2); 
	}
	
	private void modificationButtonsAllFalse() {
		timedstepButton.setText("Timed-step");
		ffButton1.setText("FastForward(" + fastforwardNValue + ")");
		ffButton2.setText("FastForward");
    	fastForwardN = false;
    	fastForward = false; 
    	timedStep = false; 
    	if (timer!=null) timer.cancel();
    	DrawingUtils.redrawEdges(tree, edgeCanvas); 
	}
	
}