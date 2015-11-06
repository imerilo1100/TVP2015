package tree;

import static org.junit.Assert.*;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.*;

import com.google.common.collect.Sets;

public class ModifyTreeTest {
	Tree testTree;

	@Before
	public void setupTestData() {
		testTree = CreateTree.CreateCompleteTreeCircular(2, 2, 0, 0);
	}

	@Test
	public void testOneModification() {
		assertTrue(validAfterModification(testTree));
	}

	@Test
	public void test100Modifications() {
		boolean correct;
		for (int i = 0; i < 100; i++) {
			correct = validAfterModification(testTree);
			if (!correct)
				fail();
		}
		assertTrue(true);
	}

	private static Set<Map.Entry<Node, Node>> getNodeParentPairs(Tree tree) {
		Set<Map.Entry<Node, Node>> nodeParentPairs = new HashSet<Map.Entry<Node, Node>>();

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(tree.getRoot());

		Node currentNode, parent;
		while (!nodes.isEmpty()) {
			currentNode = nodes.remove(0);
			parent = currentNode.getParent();

			nodeParentPairs.add(new AbstractMap.SimpleEntry<Node, Node>(currentNode, parent));

			nodes.addAll(currentNode.getChildren());
		}

		return nodeParentPairs;
	}

	// Returns true iff exactly one node has changed its parent or the tree has
	// stayed the same.
	@SuppressWarnings({ "unchecked" })
	private static boolean validAfterModification(Tree tree) {
		Set<Map.Entry<Node, Node>> nodeParentPairs = getNodeParentPairs(tree);
		ModifyTree.addNewEdge(tree);
		Set<Map.Entry<Node, Node>> nodeParentPairsPostMod = getNodeParentPairs(tree);

		Sets.SetView<Map.Entry<Node, Node>> symDif = Sets.symmetricDifference(nodeParentPairs, nodeParentPairsPostMod);
		int size = symDif.size();

		if (size == 0)
			return true;
		if (size == 2) {
			Object[] pairs = symDif.toArray();
			Map.Entry<Node, Node> pair1, pair2;
			pair1 = (Map.Entry<Node, Node>) pairs[0];
			pair2 = (Map.Entry<Node, Node>) pairs[1];

			if (pair1.getKey() == pair2.getKey())
				return true;
		}

		return false;
	}
}
