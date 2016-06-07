package eu.mondo.map.modelmetrics.tests;

import eu.mondo.map.core.graph.Network;

public class ModelContext {

	public static Network<String> network;

	public static String dim1;
	public static String dim2;
	public static String dim3;
	public static String dim4;
	public static String node1;
	public static String node2;
	public static String node3;
	public static String node4;
	public static String node5;
	public static String node6;
	public static String node7;
	public static String node8;
	public static String node9;

	static {
		network = new Network<String>();
		node1 = "node1";
		node2 = "node2";
		node3 = "node3";
		node4 = "node4";
		node5 = "node5";
		node6 = "node6";
		node7 = "node7";
		node8 = "node8";
		node9 = "node9";
		dim1 = "dim1";
		dim2 = "dim2";
		dim3 = "dim3";
		dim4 = "dim4";
	}

}
