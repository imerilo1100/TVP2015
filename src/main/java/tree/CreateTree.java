package main.java.tree;

import java.util.ArrayList;
import java.util.List;

public class CreateTree {
	//TODO: Try to ensure that nodes do not overlap. Currently no check and not optimal node positioning.  

	/* Existing vatriations: 
	 * CreateCompleteTreeTriangle(int height, int branchesPerNode, double x, double y)
	 * CreateCompleteTreeOffSetTriangle(int height, int branchesPerNode, double x, double y)
	 * CreateCompleteTreeCircular(int height, int branchesPerNode, double x, double y)
	 * CreateCompleteTreeOffsetCircular(int height, int branchesPerNode, double x, double y)
	 * */
	
	public static Tree CreateCompleteTreeTriangle(int height, int branchesPerNode, double x, double y) {
		Tree tree = new Tree(new Node("X", x/2.0, 5.0)); 
		double heightStep = (y-15.0)/height; 
		double currentHeight = 5; 
		List<Node> nodes = new ArrayList<Node>(); 
		nodes.add(tree.getRoot());
		for (int i = 0; i < height; i++) {
			List<Node> temp = new ArrayList<Node>();
			temp.addAll(nodes);
			nodes.clear(); 
			currentHeight += heightStep;
			double widthStep = (x-5)/branchesPerNode/temp.size();
			double currentWidth = 0.5*widthStep;
			for (Node node : temp) {
				for (int j = 0; j<branchesPerNode; j++) {
					Node newnode = new Node(Integer.toString(i)+"-"+Integer.toString(j),node,currentWidth,currentHeight);
					currentWidth += widthStep;
					node.addChild(newnode);
					nodes.add(newnode);
				}
				
			}
		} 
		return tree; 
	}
	public static Tree CreateCompleteTreeOffSetTriangle(int height, int branchesPerNode, double x, double y) {
		Tree tree = new Tree(new Node("X", x/2.0, 5.0)); 
		double heightStep = (y-15.0)/height; 
		double currentHeight = 5; 
		List<Node> nodes = new ArrayList<Node>(); 
		nodes.add(tree.getRoot());
		for (int i = 0; i < height; i++) {
			List<Node> temp = new ArrayList<Node>();
			temp.addAll(nodes);
			nodes.clear(); 
			currentHeight += heightStep;
			double widthStep = (x-5)/branchesPerNode/temp.size();
			double currentWidth = 0.5*widthStep;
			for (Node node : temp) {
				for (int j = 0; j<branchesPerNode; j++) {
					Node newnode = new Node(Integer.toString(i)+"-"+Integer.toString(j),node,currentWidth,currentHeight-(j*16));
					currentWidth += widthStep;
					node.addChild(newnode);
					nodes.add(newnode);
				}
				
			}
		} 
		return tree; 
	}
	public static Tree CreateCompleteTreeCircular(int height, int branchesPerNode, double x, double y) {
		double centreX = x/2.0;
		double centreY = y/2.0; 
		double radiusStep = (Math.min(x, y)-16.0)/(2.0*height);
		double currentRadius = 0.0; 
		double currentDegreeStep = 360.0;
		Tree tree = new Tree(new Node("X", centreX, centreY)); 
		List<Node> nodes = new ArrayList<Node>(); 
		nodes.add(tree.getRoot());
		for (int i = 0; i < height; i++) {
			List<Node> temp = new ArrayList<Node>();
			temp.addAll(nodes);
			nodes.clear(); 
			currentRadius += radiusStep;
			currentDegreeStep = currentDegreeStep/branchesPerNode;
			double currentDegree = 0.0; 
			for (Node node : temp) {
				for (int j = 0; j<branchesPerNode; j++) {
					double newNodeX = currentRadius*Math.sin(Math.toRadians(currentDegree))+centreX;
					double newNodeY = currentRadius*Math.cos(Math.toRadians(currentDegree))+centreY;
					Node newnode = new Node(Integer.toString(i)+"-"+Integer.toString(j),node,newNodeX,newNodeY);
					currentDegree += currentDegreeStep;
					node.addChild(newnode);
					nodes.add(newnode);
				}
			}
		}
		return tree; 
	}
	public static Tree CreateCompleteTreeOffsetCircular(int height, int branchesPerNode, double x, double y) {
		double centreX = x/2.0;
		double centreY = y/2.0; 
		double radiusStep = (Math.min(x, y)-16.0)/(2.0*height);
		double currentRadius = 0.0; 
		double currentDegreeStep = 360.0;
		Tree tree = new Tree(new Node("X", centreX, centreY)); 
		List<Node> nodes = new ArrayList<Node>(); 
		nodes.add(tree.getRoot());
		for (int i = 0; i < height; i++) {
			List<Node> temp = new ArrayList<Node>();
			temp.addAll(nodes);
			nodes.clear(); 
			currentRadius += radiusStep;
			currentDegreeStep = currentDegreeStep/branchesPerNode;
			double currentDegree = 0.0; 
			for (Node node : temp) {
				for (int j = 0; j<branchesPerNode; j++) {
					double newNodeX = (currentRadius-(j*16))*Math.sin(Math.toRadians(currentDegree))+centreX;
					double newNodeY = (currentRadius-(j*16))*Math.cos(Math.toRadians(currentDegree))+centreY;
					Node newnode = new Node(Integer.toString(i)+"-"+Integer.toString(j),node,newNodeX,newNodeY);
					currentDegree += currentDegreeStep;
					node.addChild(newnode);
					nodes.add(newnode);
				}
			}
		}
		return tree; 
	}
	
}
