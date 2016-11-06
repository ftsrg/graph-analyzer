package hu.bme.mit.mba.tests.model.neo4j;

import java.util.Map;

import org.neo4j.graphdb.Node;

public class TestModelToNetworkConverter {

    private Map<String, Node> nodeMapping;

//    public NodeContainer convert(final TestModel testModel) {
//        NetworkPackage.eINSTANCE.eClass();
//
//        nodeMapping = new HashMap<>();
//        final NetworkFactory factory = NetworkFactory.eINSTANCE;
//        final NodeContainer container = factory.createNodeContainer();
//
//        for (final String nodeName : testModel.getAdjacency().rowKeySet()) {
//            addNode(nodeMapping, factory, container, nodeName);
//
//            for (final String neighborName : testModel.getAdjacency().row(nodeName).keySet()) {
//                addNode(nodeMapping, factory, container, neighborName);
//            }
//
//        }
//
//        for (final String nodeName : testModel.getAdjacency().rowKeySet()) {
//            for (final String neighborName : testModel.getAdjacency().row(nodeName).keySet()) {
//                for (final String dimensionName : testModel.getAdjacency().get(nodeName, neighborName)) {
//                    switch (dimensionName) {
//                    case "dim1":
//                        nodeMapping.get(nodeName).getDim1().add(nodeMapping.get(neighborName));
//                        break;
//                    case "dim2":
//                        nodeMapping.get(nodeName).getDim2().add(nodeMapping.get(neighborName));
//                        break;
//                    case "dim3":
//                        nodeMapping.get(nodeName).getDim3().add(nodeMapping.get(neighborName));
//                        break;
//                    default:
//                        throw new IllegalArgumentException(
//                                "The TestModel " + testModel + " contains an unrecognized dimension " + dimensionName);
//                    }
//
//                }
//            }
//
//        }
//        return container;
//    }

//    protected void addNode(final Map<String, Node> nodeMapping, final NetworkFactory factory, final NodeContainer container,
//            final String nodeName) {
//        if (!nodeMapping.containsKey(nodeName)) {
//            final Node node = factory.createNode();
//            node.setName(nodeName);
//            nodeMapping.put(nodeName, node);
//            container.getNodes().add(node);
//        }
//    }

    public Map<String, Node> getNodeMapping() {
        return nodeMapping;
    }

}
