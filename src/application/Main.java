package application;

//Java imports. 
import java.util.Timer;
import java.util.TimerTask;

//Java(fx) imports
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

//Custom imports. 
import tree.CreateTree;
import tree.Tree;
import tree.ModifyTree;

public class Main extends Application { 
	//Tools, random variables and data structures. 
	int fastforwardNValue = 1000; //TODO: Give user the ability to choose another value
	int timedStepInterval = 3000; //TODO: Give user the ability to choose another value
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
			//Add panes to the rootpane, populate panes. 
			root.getChildren().addAll(statsPane, toolbar, edgeCanvas, nodeCanvas);
			addToolbarButtons();
			//Create and draw arbitrary initial tree. 
			//TODO Created tree should probably be more random than this; replace function.
			tree = CreateTree.CreateCompleteTree2(5, 3, 680, 620); //TODO actual finding height. 
			DrawingUtils.drawTree(tree, nodeCanvas, edgeCanvas); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void addToolbarButtons(){
		nextButton.setOnMouseClicked( new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
            	toolBarButtonsAllFalse();
				ModifyTree.addNewEdge(tree); 
				DrawingUtils.drawTree(tree, nodeCanvas, edgeCanvas); 
            }
        });
		timedstepButton.setOnMouseClicked( new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
            	if (!timedStep) {
            		toolBarButtonsAllFalse();
            		timedstepButton.setText("Timed-step [ON]");
            		timedStep = true; 
            		timer = new Timer(); 
            	    timer.scheduleAtFixedRate(new TimerTask() {
            	        @Override
            	        public void run() {
            	        	Platform.runLater(new Runnable() {
            	        		public void run() {
            	        			ModifyTree.addNewEdge(tree); 
            	        			DrawingUtils.drawTree(tree, nodeCanvas, edgeCanvas); 
            	        		}
            	        	});
            	        }
            	    }, 0, timedStepInterval); 
            	} else {toolBarButtonsAllFalse();}
            }
        });
		ffButton1.setOnMouseClicked( new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
            	if (!fastForward) {
            		toolBarButtonsAllFalse();
            		ffButton1.setText("FastForward(" + fastforwardNValue + ") [ON]");
                	fastForwardN = true;
            		for (int i=0; i<fastforwardNValue; i++) {
            			ModifyTree.addNewEdge(tree);
            			//TODO: Display label informing the user about no redraws. 
            		}
                	fastForwardN = false;
                	DrawingUtils.drawTree(tree, nodeCanvas, edgeCanvas);
            		ffButton1.setText("FastForward(" + fastforwardNValue + ")");
            	} else {toolBarButtonsAllFalse();}
            }
        });
		ffButton2.setOnMouseClicked( new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
            	//TODO: This will need fixing since the coputer tends to overwork itself...
            	if (!fastForward) {
            		toolBarButtonsAllFalse();
            		ffButton2.setText("FastForward [ON]");
            		fastForward = true; 
            		while (fastForward) {
            			ModifyTree.addNewEdge(tree);
            			//TODO: Display label informing user about no redraws. 
            		}
            		DrawingUtils.drawTree(tree, nodeCanvas, edgeCanvas); 
            	} else {toolBarButtonsAllFalse();}
            }
        });
		toolbar.getChildren().addAll(nextButton,timedstepButton,ffButton1,ffButton2); 
	}
	
	private void toolBarButtonsAllFalse() {
		timedstepButton.setText("Timed-step");
		ffButton1.setText("FastForward(" + fastforwardNValue + ")");
		ffButton2.setText("FastForward");
    	fastForwardN = false;
    	fastForward = false; 
    	timedStep = false; 
    	if (timer!=null) timer.cancel();
    	DrawingUtils.drawTree(tree, nodeCanvas, edgeCanvas); 
	}
	
}