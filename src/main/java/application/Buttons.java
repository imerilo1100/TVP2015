package application;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import tree.ModifyTree;

public class Buttons {

	//TODO: Buttons should have graphics and tooltips?
	protected void addToolbarButtons(Main main){
		main.nextButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
          		modificationButtonsAllFalse(main);
				ModifyTree.addNewEdge(main.tree); 
				DrawingUtils.redrawEdges(main.tree, main.edgeCanvas); 
				main.stats.collectData(main.tree, main.statsBase.getStatsContent()); 
			}
        });
		main.timedstepButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent event) {
          	if (!main.timedStep) {
          		modificationButtonsAllFalse(main);
          		main.timedstepButton.setText("Timed-step [ON]");
          		main.timedStep = true; 
          		main.timer = new Timer(); 
          		main.timer.scheduleAtFixedRate(new TimerTask() {
          	        @Override
          	        public void run() {
          	        	Platform.runLater(new Runnable() {
          	        		public void run() {
          	        			ModifyTree.addNewEdge(main.tree); 
          	        			DrawingUtils.redrawEdges(main.tree, main.edgeCanvas);
          	        			main.stats.collectData(main.tree, main.statsBase.getStatsContent()); 
          	        		}
          	        	});
          	        }
          	    }, 0, main.timedStepInterval); 
          	} else {modificationButtonsAllFalse(main);}
          }
      });
		main.ffButton1.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent event) {
          	if (!main.fastForward) {
          		modificationButtonsAllFalse(main);
          		main.ffButton1.setText("FastForward(" + main.fastforwardNValue + ") [ON]");
          		main.fastForwardN = true;
              	//TODO: Display label informing user about no redraws.
          		for (int i=0; i<main.fastforwardNValue; i++) {
          			ModifyTree.addNewEdge(main.tree);
    				main.stats.collectData(main.tree, main.statsBase.getStatsContent()); 
          		}
          		main.fastForwardN = false;
              	DrawingUtils.redrawEdges(main.tree, main.edgeCanvas); 
              	main.ffButton1.setText("FastForward(" + main.fastforwardNValue + ")");
          	} else {modificationButtonsAllFalse(main);}
          }
      });
		main.ffButton2.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent event) {
          	if (!main.fastForward) {
          		modificationButtonsAllFalse(main);
          		main.ffButton2.setText("FastForward [ON]");
          		main.fastForward = true; 
          		main.timer = new Timer();
          		//TODO: Display label informing user about no redraws. 
          		main.timer.scheduleAtFixedRate(new TimerTask() {
          	        @Override
          	        public void run() {
          	        	Platform.runLater(new Runnable() {
          	        		public void run() {
          	        			ModifyTree.addNewEdge(main.tree);
          	        			main.stats.collectData(main.tree, main.statsBase.getStatsContent()); 
          	        		}
          	        	});
          	        }
          	    }, 0, 1); 
          	    DrawingUtils.redrawEdges(main.tree, main.edgeCanvas); 
          	} else {modificationButtonsAllFalse(main);}
          }
      });
		main.toggleNodeDragAndDropButton.setOnMouseClicked( new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent event) {
          	if (!DrawingUtils.nodeDragAndDropEnabled) {
          		main.toggleNodeDragAndDropButton.setText("Node drag-and-drop [ON]");
          		DrawingUtils.nodeDragAndDropEnabled=true;
          	} else {
          		main.toggleNodeDragAndDropButton.setText("Node drag-and-drop [OFF]");
          		DrawingUtils.nodeDragAndDropEnabled=false; 
          	}
          }
      });
		main.toolbar.getChildren().addAll(main.nextButton,
										  main.timedstepButton,
										  main.ffButton1,
										  main.ffButton2,
										  main.toggleNodeDragAndDropButton); 
	}
	
	private void modificationButtonsAllFalse(Main main) {
		main.timedstepButton.setText("Timed-step");
		main.ffButton1.setText("FastForward(" + main.fastforwardNValue + ")");
		main.ffButton2.setText("FastForward");
		main.fastForwardN = false;
		main.fastForward = false; 
		main.timedStep = false; 
  		if (main.timer!=null) main.timer.cancel();
  		DrawingUtils.redrawEdges(main.tree, main.edgeCanvas); 
		main.stats.collectData(main.tree, main.statsBase.getStatsContent()); 
	}
	
}