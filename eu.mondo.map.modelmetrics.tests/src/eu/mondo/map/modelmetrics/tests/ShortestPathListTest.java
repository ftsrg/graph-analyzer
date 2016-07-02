package eu.mondo.map.modelmetrics.tests;

import static eu.mondo.map.modelmetrics.tests.ModelContext.dim1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.dim2;
import static eu.mondo.map.modelmetrics.tests.ModelContext.dim3;
import static eu.mondo.map.modelmetrics.tests.ModelContext.network;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node2;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node3;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node4;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node5;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node6;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node7;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node8;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import eu.mondo.map.base.graph.Node;
import eu.mondo.map.base.tests.metrics.ListMetricTest;
import eu.mondo.map.modelmetrics.impl.simple.Path;
import eu.mondo.map.modelmetrics.impl.simple.ShortestPathList;

public class ShortestPathListTest extends ListMetricTest<Integer, ShortestPathList<String>> {

	protected ModelContext mc;

	public ShortestPathListTest() {
		mc = new ModelContext();
	}

	@Override
	public ShortestPathList<String> initMetric() {
		return new ShortestPathList<>();
	}

	@Override
	public void clear() {
		network.clear();
	}

	protected void checkDepth(int expected, List<Path<String>> paths) {
		for (Path<String> path : paths) {
			Assert.assertEquals(expected, path.getDepth());
		}
	}

	protected void checkPath(Path<String> path, List<String> vertices) {
		List<Node<String>> nodes = new ArrayList<>();
		for (String vertex : vertices) {
			nodes.add(network.getNode(vertex));
		}

		Assert.assertArrayEquals(nodes.toArray(), path.getPath().toArray());

	}

	protected void checkPathNumber(int expected, List<Path<String>> paths) {
		Assert.assertEquals(expected, paths.size());

	}

	protected void containsPath(List<Path<String>> paths, ArrayList<String> vertices) {
		List<Node<String>> nodes = new ArrayList<>();
		for (String vertex : vertices) {
			nodes.add(network.getNode(vertex));
		}
		boolean contains = false;
		for (Path<String> p : paths) {
			if (p.getPath().equals(nodes)) {
				contains = true;
			}
		}
		Assert.assertTrue(contains);

	}

	protected List<Path<String>> calculate(String source, String target) {
		Node<String> sourceNode = network.getNode(source);
		Node<String> targetNode = network.getNode(target);

		List<Path<String>> paths = metric.calculate(sourceNode, targetNode);
		return paths;
	}

	protected void calculate() {
		metric.calculate(network);
	}

	protected void calculate(int numberOfRandomPairs) {
		metric.calculate(network, numberOfRandomPairs);
	}

	@Test
	public void nullPath() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);

		List<Path<String>> paths = calculate(node3, node2);
		Assert.assertTrue(paths.isEmpty());

	}

	@Test
	public void nullPath2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node3, node4);
		network.addEdge(dim1, node2, node4);

		List<Path<String>> paths = calculate(node1, node3);
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

		List<Path<String>> paths = calculate(node4, node3);
		Assert.assertTrue(paths.isEmpty());
	}

	@Test
	public void absentTarget() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);
		network.addNode(node4);

		List<Path<String>> paths = calculate(node1, node4);
		Assert.assertTrue(paths.isEmpty());
	}

	@Test
	public void singlePath1() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);

		List<Path<String>> paths = calculate(node1, node3);
		checkDepth(2, paths);
		checkPathNumber(1, paths);
		checkPath(paths.get(0), Lists.newArrayList(node3, node2, node1));

	}

	@Test
	public void singlePath2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);

		List<Path<String>> paths = calculate(node2, node3);
		checkDepth(1, paths);
		checkPathNumber(1, paths);
		checkPath(paths.get(0), Lists.newArrayList(node3, node2));
	}

	@Test
	public void singlePath3() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);
		network.addEdge(dim1, node1, node3);

		List<Path<String>> paths = calculate(node1, node3);
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

		List<Path<String>> paths = calculate(node1, node3);
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

		List<Path<String>> paths = calculate(node1, node8);
		checkDepth(2, paths);

		checkPathNumber(1, paths);
		checkPath(paths.get(0), Lists.newArrayList(node8, node2, node1));
	}

	@Test
	public void singlePathMultidimensional() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node1);
		network.addEdge(dim2, node2, node3);

		List<Path<String>> paths = calculate(node1, node3);
		checkDepth(2, paths);
		checkPathNumber(1, paths);
		checkPath(paths.get(0), Lists.newArrayList(node3, node2, node1));
	}

	@Test
	public void singlePathMultidimensional2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);
		network.addEdge(dim2, node2, node3);

		List<Path<String>> paths = calculate(node1, node3);
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

		List<Path<String>> paths = calculate(node1, node3);
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

		List<Path<String>> paths = calculate(node1, node6);
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

		List<Path<String>> paths = calculate(node1, node6);
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

		List<Path<String>> paths = calculate(node1, node6);
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

		List<Path<String>> paths = calculate(node1, node6);
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
		network.addEdge(dim1, node5, node6);

		List<Path<String>> paths = calculate(node1, node6);
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

		List<Path<String>> paths = calculate(node1, node8);
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

		List<Path<String>> paths = calculate(node1, node8);
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

		List<Path<String>> paths = calculate(node1, node6);
		checkDepth(2, paths);

		checkPathNumber(3, paths);
		containsPath(paths, Lists.newArrayList(node6, node2, node1));
		containsPath(paths, Lists.newArrayList(node6, node3, node1));
		containsPath(paths, Lists.newArrayList(node6, node4, node1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRandomSampleException1() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		calculate(-2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRandomSampleException2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		calculate(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRandomSampleException3() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		calculate(7);
	}

	@Test
	public void testRandomSample2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);

		calculate(6);
		checkSize(2);
		checkAppearance(2, 1);
	}

	@Test
	public void testRandomSample3() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);
		network.addEdge(dim1, node2, node4);
		network.addEdge(dim1, node2, node5);
		network.addEdge(dim1, node4, node5);

		calculate(20);
		checkSize(8);
		checkAppearance(5, 1);
		checkAppearance(3, 2);
		checkAppearance(0, 3);
	}

	@Test
	public void testCalculationforAll1() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);

		calculate();

		checkSize(2);
		checkAppearance(2, 1);
	}

	@Test
	public void testCalculationforAll2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node2, node3);
		network.addEdge(dim1, node2, node4);
		network.addEdge(dim1, node2, node5);
		network.addEdge(dim1, node4, node5);

		calculate();

		checkSize(8);
		checkAppearance(5, 1);
		checkAppearance(3, 2);
		checkAppearance(0, 3);
	}

}
