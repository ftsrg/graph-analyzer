package eu.mondo.map.modelmetrics.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import eu.mondo.map.core.graph.Node;
import eu.mondo.map.modelmetrics.composite.ShortestPathList;
import eu.mondo.map.modelmetrics.composite.ShortestPathList.Path;

public class ShortestPathListTest extends ModelMetricTest {

	protected ShortestPathList shortestPathList;

	@Override
	public void initMetric() {
		shortestPathList = new ShortestPathList();
	}

	protected void checkDepth(int expected, List<Path> paths) {
		for (Path path : paths) {
			Assert.assertEquals(expected, path.getDepth());
		}
	}

	protected void checkPath(Path path, List<String> vertices) {
		List<Node<String>> nodes = new ArrayList<Node<String>>();
		for (String vertex : vertices) {
			nodes.add(network.getNode(vertex));
		}

		Assert.assertArrayEquals(nodes.toArray(), path.getPath().toArray());

	}

	protected void checkPathNumber(int expected, List<Path> paths) {
		Assert.assertEquals(expected, paths.size());

	}

	protected void containsPath(List<Path> paths, ArrayList<String> vertices) {
		List<Node<String>> nodes = new ArrayList<Node<String>>();
		for (String vertex : vertices) {
			nodes.add(network.getNode(vertex));
		}
		boolean contains = false;
		for (Path p : paths) {
			if (p.getPath().equals(nodes)) {
				contains = true;
			}
		}
		Assert.assertTrue(contains);

	}

	protected List<Path> calculate(String source, String target) {
		Node<String> sourceNode = network.getNode(source);
		Node<String> targetNode = network.getNode(target);

		List<Path> paths = shortestPathList.calculate(sourceNode, targetNode);
		return paths;
	}

	@Test
	public void nullPath() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);

		List<Path> paths = calculate(node3, node2);
		Assert.assertTrue(paths.isEmpty());
	}

	@Test
	public void nullPath2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node3, node4);
		network.addEdge(dim1, node2, node4);

		List<Path> paths = calculate(node1, node3);
		Assert.assertTrue(paths.isEmpty());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullSource() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);

		calculate(node4, node2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullTarget() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);

		calculate(node1, node4);
	}

	@Test
	public void absentSource() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);
		network.addNode(node4);

		List<Path> paths = calculate(node4, node3);
		Assert.assertTrue(paths.isEmpty());
	}

	@Test
	public void absentTarget() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);
		network.addNode(node4);

		List<Path> paths = calculate(node1, node4);
		Assert.assertTrue(paths.isEmpty());
	}

	@Test
	public void singlePath1() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);

		List<Path> paths = calculate(node1, node3);
		checkDepth(2, paths);
		checkPathNumber(1, paths);
		checkPath(paths.get(0), Lists.newArrayList(node3, node2, node1));

	}

	@Test
	public void singlePath2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);

		List<Path> paths = calculate(node2, node3);
		checkDepth(1, paths);
		checkPathNumber(1, paths);
		checkPath(paths.get(0), Lists.newArrayList(node3, node2));
	}

	@Test
	public void singlePath3() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);
		network.addEdge(dim1, node1, node3);

		List<Path> paths = calculate(node1, node3);
		checkDepth(1, paths);
		checkPathNumber(1, paths);
		checkPath(paths.get(0), Lists.newArrayList(node3, node1));
	}

	@Test
	public void singlePath4() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node3, node2);
		network.addEdge(dim1, node3, node1);
		network.addEdge(dim1, node2, node1);
		network.addEdge(dim1, node2, node3);

		List<Path> paths = calculate(node1, node3);
		checkDepth(2, paths);
		checkPathNumber(1, paths);
		checkPath(paths.get(0), Lists.newArrayList(node3, node2, node1));
	}

	@Test
	public void singlePath5() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node1, node4);
		network.addEdge(dim1, node2, node5);
		network.addEdge(dim1, node2, node8);
		network.addEdge(dim1, node2, node6);
		network.addEdge(dim1, node3, node6);
		network.addEdge(dim1, node4, node7);
		network.addEdge(dim1, node4, node6);
		network.addEdge(dim1, node5, node8);
		network.addEdge(dim1, node6, node8);
		network.addEdge(dim1, node7, node8);

		List<Path> paths = calculate(node1, node8);
		checkDepth(2, paths);

		checkPathNumber(1, paths);
		checkPath(paths.get(0), Lists.newArrayList(node8, node2, node1));
//		containsPath(paths, Lists.newArrayList(node8, node5, node2, node1));
//		containsPath(paths, Lists.newArrayList(node8, node6, node3, node1));
//		containsPath(paths, Lists.newArrayList(node8, node6, node2, node1));
//		containsPath(paths, Lists.newArrayList(node8, node6, node4, node1));
//		containsPath(paths, Lists.newArrayList(node8, node7, node4, node1));
	}

	@Test
	public void singlePathMultiDimensional() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node1);
		network.addEdge(dim2, node2, node3);

		List<Path> paths = calculate(node1, node3);
		checkDepth(2, paths);
		checkPathNumber(1, paths);
		checkPath(paths.get(0), Lists.newArrayList(node3, node2, node1));
	}

	@Test
	public void singlePathMultiDimensional2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);
		network.addEdge(dim2, node2, node3);

		List<Path> paths = calculate(node1, node3);
		checkDepth(2, paths);
		checkPathNumber(1, paths);
		checkPath(paths.get(0), Lists.newArrayList(node3, node2, node1));
	}

	@Test
	public void multiplePath2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node4);
		network.addEdge(dim1, node2, node3);
		network.addEdge(dim2, node2, node3);
		network.addEdge(dim1, node4, node3);

		List<Path> paths = calculate(node1, node3);
		checkDepth(2, paths);
		checkPathNumber(2, paths);
		containsPath(paths, Lists.newArrayList(node3, node2, node1));
		containsPath(paths, Lists.newArrayList(node3, node4, node1));
	}

	@Test
	public void multiplePath3() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node2, node4);
		network.addEdge(dim1, node3, node5);
		network.addEdge(dim1, node4, node6);
		network.addEdge(dim1, node5, node6);

		List<Path> paths = calculate(node1, node6);
		checkDepth(3, paths);
		checkPathNumber(2, paths);
		containsPath(paths, Lists.newArrayList(node6, node4, node2, node1));
		containsPath(paths, Lists.newArrayList(node6, node5, node3, node1));
	}

	@Test
	public void multiplePath4() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim2, node1, node3);
		network.addEdge(dim3, node1, node3);
		network.addEdge(dim1, node2, node4);
		network.addEdge(dim1, node3, node5);
		network.addEdge(dim1, node4, node6);
		network.addEdge(dim3, node4, node6);
		network.addEdge(dim1, node5, node6);
		network.addEdge(dim2, node5, node6);
		network.addEdge(dim3, node5, node6);

		List<Path> paths = calculate(node1, node6);
		checkDepth(3, paths);
		checkPathNumber(2, paths);
		containsPath(paths, Lists.newArrayList(node6, node4, node2, node1));
		containsPath(paths, Lists.newArrayList(node6, node5, node3, node1));
	}

	@Test
	public void multiplePath5() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node2, node4);
		network.addEdge(dim1, node2, node5);
		network.addEdge(dim1, node3, node5);
		network.addEdge(dim1, node4, node6);
		network.addEdge(dim1, node4, node3);
		network.addEdge(dim1, node5, node6);

		List<Path> paths = calculate(node1, node6);
		checkDepth(3, paths);

		checkPathNumber(3, paths);
		containsPath(paths, Lists.newArrayList(node6, node4, node2, node1));
		containsPath(paths, Lists.newArrayList(node6, node5, node3, node1));
		containsPath(paths, Lists.newArrayList(node6, node5, node2, node1));
	}

	@Test
	public void multiplePath6() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node2, node4);
		network.addEdge(dim1, node2, node5);
		network.addEdge(dim1, node3, node5);
		network.addEdge(dim1, node3, node2);
		network.addEdge(dim1, node4, node6);
		network.addEdge(dim1, node4, node3);
		network.addEdge(dim1, node5, node6);

		List<Path> paths = calculate(node1, node6);
		checkDepth(3, paths);

		checkPathNumber(3, paths);
		containsPath(paths, Lists.newArrayList(node6, node4, node2, node1));
		containsPath(paths, Lists.newArrayList(node6, node5, node3, node1));
		containsPath(paths, Lists.newArrayList(node6, node5, node2, node1));
	}

	@Test
	public void multiplePath7() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node2, node4);
		network.addEdge(dim1, node2, node5);
		network.addEdge(dim1, node3, node5);
		network.addEdge(dim1, node4, node3);
		network.addEdge(dim1, node4, node6);
		network.addEdge(dim1, node4, node3);
		network.addEdge(dim1, node5, node6);

		List<Path> paths = calculate(node1, node6);
		checkDepth(3, paths);

		checkPathNumber(3, paths);
		containsPath(paths, Lists.newArrayList(node6, node4, node2, node1));
		containsPath(paths, Lists.newArrayList(node6, node5, node3, node1));
		containsPath(paths, Lists.newArrayList(node6, node5, node2, node1));
	}

	@Test
	public void multiplePath8() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node1, node4);
		network.addEdge(dim1, node2, node5);
		network.addEdge(dim1, node2, node6);
		network.addEdge(dim1, node3, node6);
		network.addEdge(dim1, node4, node7);
		network.addEdge(dim1, node4, node6);
		network.addEdge(dim1, node5, node8);
		network.addEdge(dim1, node6, node8);
		network.addEdge(dim1, node7, node8);

		List<Path> paths = calculate(node1, node8);
		checkDepth(3, paths);

		checkPathNumber(5, paths);
		containsPath(paths, Lists.newArrayList(node8, node5, node2, node1));
		containsPath(paths, Lists.newArrayList(node8, node6, node3, node1));
		containsPath(paths, Lists.newArrayList(node8, node6, node2, node1));
		containsPath(paths, Lists.newArrayList(node8, node6, node4, node1));
		containsPath(paths, Lists.newArrayList(node8, node7, node4, node1));
	}

	@Test
	public void multiplePath9() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node1, node4);
		network.addEdge(dim1, node2, node5);
		network.addEdge(dim1, node2, node6);
		network.addEdge(dim1, node3, node6);
		network.addEdge(dim1, node4, node7);
		network.addEdge(dim1, node4, node6);
		network.addEdge(dim1, node5, node6);
		network.addEdge(dim1, node5, node8);
		network.addEdge(dim1, node6, node8);
		network.addEdge(dim1, node7, node8);

		List<Path> paths = calculate(node1, node8);
		checkDepth(3, paths);

		checkPathNumber(5, paths);
		containsPath(paths, Lists.newArrayList(node8, node5, node2, node1));
		containsPath(paths, Lists.newArrayList(node8, node6, node3, node1));
		containsPath(paths, Lists.newArrayList(node8, node6, node2, node1));
		containsPath(paths, Lists.newArrayList(node8, node6, node4, node1));
		containsPath(paths, Lists.newArrayList(node8, node7, node4, node1));
	}

	@Test
	public void multiplePath10() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node1, node4);
		network.addEdge(dim1, node2, node5);
		network.addEdge(dim1, node2, node6);
		network.addEdge(dim1, node3, node6);
		network.addEdge(dim1, node4, node7);
		network.addEdge(dim1, node4, node6);
		network.addEdge(dim1, node5, node6);
		network.addEdge(dim1, node5, node8);
		network.addEdge(dim1, node6, node8);
		network.addEdge(dim1, node7, node8);

		List<Path> paths = calculate(node1, node6);
		checkDepth(2, paths);

		checkPathNumber(3, paths);
		containsPath(paths, Lists.newArrayList(node6, node2, node1));
		containsPath(paths, Lists.newArrayList(node6, node3, node1));
		containsPath(paths, Lists.newArrayList(node6, node4, node1));
	}

}
