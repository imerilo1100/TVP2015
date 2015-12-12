package statistics;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
//Java imports
import java.util.LinkedList;
import java.util.List;

import application.StatisticsBase;
//java(fx) imports
import javafx.collections.ObservableList;
//Custom imports
import tree.Node;
import tree.Tree;

public class Statistics {
	public static LeafCount leafCount = new LeafCount(); // TODO: Check for
															// redundancy.
	private static NodeCount nodeCount = new NodeCount();
	private static DiameterCalculator diameterCalculator = new DiameterCalculator(); // TODO:
																						// Actually
																						// implement.
	private static MaximumDegreeCalculator maximumDegreeCalculator = new MaximumDegreeCalculator();

	// TODO: Refactor collectData and display refresh to be separate items for
	// power conservation.
	public void collectData(Tree tree, ObservableList<StatisticsInstance> statsContent) {
		Node root = tree.getRoot();
		double countNodes = 0.0;
		double countLeaves = 0.0;
		double maximumDegree = 0.0;
		if (root != null) {
			countNodes += 1.0;
			List<Node> nodes = new LinkedList<Node>();
			nodes.addAll(root.getChildren());
			maximumDegree = nodes.size();
			if (nodes.isEmpty()) {
				countLeaves = 1.0;
			} else
				while (!nodes.isEmpty()) {
					countNodes += nodes.size();
					List<Node> temp = new LinkedList<Node>();
					temp.addAll(nodes);
					nodes.clear();
					for (Node node : temp) {
						if (node.getChildren().isEmpty()) {
							countLeaves += 1.0;
						} else {
							nodes.addAll(node.getChildren());
							double nodeDegree = node.getChildren().size() + 1;
							if (nodeDegree > maximumDegree) {
								maximumDegree = nodeDegree;
							}
						}
					}
				}
		}
		leafCount.addValue(countLeaves);
		nodeCount.addValue(countNodes);
		maximumDegreeCalculator.addValue(maximumDegree);
		includeStatistics(statsContent); // TODO: Clear and readd is more of a
											// hack?
	}

	public void includeStatistics(ObservableList<StatisticsInstance> statsContent) {
		statsContent.clear();
		statsContent.add(nodeCount);
		statsContent.add(leafCount);
		statsContent.add(diameterCalculator);
		statsContent.add(maximumDegreeCalculator);

	}

	public static void generateCSVAllTrees(String location) throws IOException {
		ObservableList<StatisticsInstance> statisticsInstance = StatisticsBase.getStatsContent();
		LinkedList<LinkedList<Double>> statsValues = new LinkedList<LinkedList<Double>>();
		FileWriter writer = new FileWriter(location);

		// populate first row and get all statistics
		writer.append("iteration");
		for (int i = 0; i < statisticsInstance.size(); i++) {
			StatisticsInstance statsInstance = statisticsInstance.get(i);
			String propertyName = statsInstance.getPropertyName().substring(0,
					(statsInstance.getPropertyName().length() - 1));
			LinkedList<Double> propertyValues2 = statsInstance.getValues();
			int valueListSize = propertyValues2.size();
			statsValues.add(propertyValues2);
			writer.append(";" + propertyName);
		}
		writer.append("\n");

		// populate other rows
		for (int i = 0; i < statsValues.get(0).size(); i++) {
			writer.append(String.valueOf(i));
			for (int j = 0; j < statsValues.size(); j++) {
				try {
					writer.append(";" + statsValues.get(j).get(i).toString());
				} catch (IndexOutOfBoundsException e) {
					writer.append(";N/A");
				}
			}
			writer.append("\n");
		}

		// close writer
		writer.flush();
		writer.close();
	}

	public static void generateCSVCurrentTree(String location) throws IOException {
		ObservableList<StatisticsInstance> statsInstance = StatisticsBase.getStatsContent();

		// create FileWriter and append first row
		FileWriter writer = new FileWriter(location);
		writer.append("name");
		writer.append(";value");
		writer.append("\n");

		// grab statistics
		for (int i = 0; i < statsInstance.size(); i++) {
			StatisticsInstance statistics = statsInstance.get(i);
			String propertyName = statistics.getPropertyName().substring(0,
					(statistics.getPropertyName().length() - 1));
			writer.append(propertyName);
			try {
				LinkedList<Double> propertyValues2 = statistics.getValues();
				int valueListSize = propertyValues2.size();
				writer.append(";" + propertyValues2.get(valueListSize - 1));
			} catch (IndexOutOfBoundsException e) {
				writer.append(";N/A");
			}
			writer.append("\n");
		}
		// close writer
		writer.flush();
		writer.close();
	}
}
