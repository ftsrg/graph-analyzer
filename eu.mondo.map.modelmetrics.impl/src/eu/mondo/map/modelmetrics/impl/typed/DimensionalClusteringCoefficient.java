// package eu.mondo.map.modelmetrics.impl.typed;
//
// import java.util.HashSet;
// import java.util.Set;
//
// import eu.mondo.map.base.data.ListData;
// import eu.mondo.map.modeladapters.ModelAdapter;
// import eu.mondo.map.modelmetrics.AbstractModelMetric;
//
// public class DimensionalClusteringCoefficient extends
// AbstractModelMetric<ListData<Double>> {
//
// protected int maxNeighbours = 1000;
// protected boolean useHeuristic = false;
//
// public DimensionalClusteringCoefficient() {
// super("DimensionalClusteringCoefficient", new ListData<>());
// }
//
// public int getMaxNeighbours() {
// return maxNeighbours;
// }
//
// public void setMaxNeighbours(int maxNeighbours) {
// this.maxNeighbours = maxNeighbours;
// }
//
// public boolean isUseHeuristic() {
// return useHeuristic;
// }
//
// public void setUseHeuristic(boolean useHeuristic) {
// this.useHeuristic = useHeuristic;
// }
//
// public <N> void calculateFirstDefinition(final Network<N> network) {
// clear();
// for (Node<N> node : network.getNodes()) {
// calculateFirstDefinition(network, node);
// }
// }
//
// public <N> void calculateSecondDefinition(final Network<N> network) {
// clear();
// for (Node<N> node : network.getNodes()) {
// calculateSecondDefinition(network, node);
// }
// }
//
// public <N> double calculateFirstDefinition(final Network<N> network, final
// Node<N> node) {
// long interConnected = 0;
// long numberOfNeighbors = 0;
// long numberOfPossibleConnections = 0;
// double coef = 0.0;
//
// for (String dimension1 : node.getDimensionsAsSet()) {
// numberOfNeighbors = node.getNumberOfDisjunctNeighbors(dimension1);
// if (useHeuristic && numberOfNeighbors > maxNeighbours) {
// coef = 0.0;
// values.add(coef);
// return coef;
// }
// for (Node<N> neighbor1 : node.getDisjunctNeighbors(dimension1)) {
// for (Node<N> neighbor2 : node.getDisjunctNeighbors(dimension1)) {
// if (neighbor1 != neighbor2) {
// Set<String> dimensions = new HashSet<String>();
// dimensions.addAll(neighbor1.getDimensionsAsSet());
// dimensions.addAll(neighbor2.getDimensionsAsSet());
// for (String dimension2 : dimensions) {
// if (!dimension1.equals(dimension2)) {
// numberOfPossibleConnections++;
// if (network.isAdjacentUndirected(neighbor1, neighbor2, dimension2)) {
// interConnected++;
// }
// }
//
// }
// }
// }
// }
// }
// if (numberOfPossibleConnections == 0) {
// coef = 0.0;
// } else {
// coef = interConnected / (double) numberOfPossibleConnections;
// }
// values.add(coef);
// return coef;
// }
//
// public <N> double calculateSecondDefinition(final Network<N> network, final
// Node<N> node) {
// long interConnected = 0;
// long numberOfPossibleConnections = 0;
// double coef = 0.0;
// int numberOfNeighbors = 0;
//
// for (String dimension1 : node.getDimensionsAsSet()) {
// for (String dimension2 : node.getDimensionsAsSet()) {
// if (!dimension1.equals(dimension2)) {
// numberOfNeighbors = node.getNumberOfDisjunctNeighbors(dimension1);
// if (useHeuristic && numberOfNeighbors > maxNeighbours) {
// coef = 0.0;
// values.add(coef);
// return coef;
// }
// numberOfNeighbors = node.getNumberOfDisjunctNeighbors(dimension2);
// if (useHeuristic && numberOfNeighbors > maxNeighbours) {
// coef = 0.0;
// values.add(coef);
// return coef;
// }
// for (Node<N> neighbor1 : node.getNeighbors(dimension1)) {
// for (Node<N> neighbor2 : node.getNeighbors(dimension2)) {
// if (neighbor1 != neighbor2) {
// Set<String> dimensions = new HashSet<String>();
// dimensions.addAll(neighbor1.getDimensionsAsSet());
// dimensions.addAll(neighbor2.getDimensionsAsSet());
// // System.out.println(dimensions);
//
// for (String dimension3 : dimensions) {
// // System.out.println("-----");
// // System.out.println(dimension1);
// // System.out.println(dimension2);
// // System.out.println(dimension3);
// if (!dimension1.equals(dimension3) && !dimension2.equals(dimension3)) {
// numberOfPossibleConnections++;
// if (neighbor1.hasNeighbor(neighbor2, dimension3)) {
// // System.out.println(neighbor1);
// // System.out.println(neighbor2);
// // System.out.println(dimension3);
// interConnected++;
// }
// }
// }
// }
// }
// }
// }
// }
// }
//
// if (numberOfPossibleConnections == 0) {
// coef = 0.0;
// } else {
// coef = interConnected / (double) numberOfPossibleConnections;
// }
// values.add(coef);
//
// return coef;
// }
//
// public <N> double calculateThirdDefinition(final Network<N> network, final
// Node<N> node) {
// long interConnected = 0;
// long numberOfPossibleConnections = 0;
// double coef = 0.0;
// int numberOfNeighbors = 0;
//
// for (String dimension1 : node.getDimensionsAsSet()) {
// for (String dimension2 : node.getDimensionsAsSet()) {
// if (!dimension1.equals(dimension2)) {
// numberOfNeighbors = node.getNumberOfDisjunctNeighbors(dimension1);
// if (useHeuristic && numberOfNeighbors > maxNeighbours) {
// coef = 0.0;
// values.add(coef);
// return coef;
// }
// numberOfNeighbors = node.getNumberOfDisjunctNeighbors(dimension2);
// if (useHeuristic && numberOfNeighbors > maxNeighbours) {
// coef = 0.0;
// values.add(coef);
// return coef;
// }
// for (Node<N> neighbor1 : node.getNeighbors(dimension1)) {
// for (Node<N> neighbor2 : node.getNeighbors(dimension2)) {
// if (neighbor1 != neighbor2) {
// Set<String> dimensions = new HashSet<String>();
// dimensions.addAll(neighbor1.getDimensionsAsSet());
// dimensions.addAll(neighbor2.getDimensionsAsSet());
// for (String dimension3 : dimensions) {
// // System.out.println(neighbor1);
// // System.out.println(neighbor2);
// // System.out.println(dimension3);
// numberOfPossibleConnections++;
// if (neighbor1.hasNeighbor(neighbor2, dimension3)) {
// interConnected++;
// // System.out.println(neighbor1);
// // System.out.println(neighbor2);
// // System.out.println(dimension3);
// }
// }
// }
// }
// }
// }
// }
// }
//
// if (numberOfPossibleConnections == 0) {
// coef = 0.0;
// } else {
// coef = interConnected / (double) numberOfPossibleConnections;
// }
// values.add(coef);
//
// return coef;
// }
//
// @Override
// public <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
// // TODO Auto-generated method stub
//
// }
//
// @Override
// public <N, T> void evaluate(ModelAdapter<N, T> adapter, N element) {
// // TODO Auto-generated method stub
//
// }
//
// }
