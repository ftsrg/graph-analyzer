package eu.mondo.map.modelmetrics.tests;

import org.junit.Before;

import eu.mondo.map.core.graph.Network;

public abstract class ModelMetricTest {

	protected Network<String> network;
	protected String dim1;
	protected String dim2;
	protected String dim3;
	protected String node1;
	protected String node2;
	protected String node3;
	protected String node4;
	protected String node5;
	protected String node6;

	@Before
	public void init() {
		network = new Network<String>();
		node1 = "node1";
		node2 = "node2";
		node3 = "node3";
		node4 = "node4";
		node5 = "node5";
		node6 = "node6";
		dim1 = "dim1";
		dim2 = "dim2";
		dim3 = "dim3";
		initMetric();
	}

	public abstract void initMetric();
}
