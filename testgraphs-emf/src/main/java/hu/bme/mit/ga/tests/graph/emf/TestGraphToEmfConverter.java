package hu.bme.mit.ga.tests.graph.emf;

import hu.bme.mit.ga.tests.graph.emf.network.NetworkFactory;
import hu.bme.mit.ga.tests.graph.emf.network.NetworkPackage;
import hu.bme.mit.ga.tests.graph.emf.network.Node;
import hu.bme.mit.ga.tests.graph.emf.network.NodeContainer;
import hu.bme.mit.ga.tests.graph.TestGraph;
import hu.bme.mit.ga.tests.graph.TestGraphConstants;
import hu.bme.mit.ga.tests.graph.TestGraphToConcreteFormatConverter;

import java.util.HashMap;
import java.util.Map;

public class TestGraphToEmfConverter extends TestGraphToConcreteFormatConverter<Node, NodeContainer> {

    @Override
    public NodeContainer convert(TestGraph testGraph) {
        NetworkPackage.eINSTANCE.eClass();

        nodeMapping = new HashMap<>();
        NetworkFactory factory = NetworkFactory.eINSTANCE;
        NodeContainer container = factory.createNodeContainer();

        for (String nodeName : testGraph.getAdjacency().rowKeySet()) {
            addNode(nodeMapping, factory, container, nodeName);

            for (String neighborName : testGraph.getAdjacency().row(nodeName).keySet()) {
                addNode(nodeMapping, factory, container, neighborName);
            }
        }

        for (String nodeName : testGraph.getAdjacency().rowKeySet()) {
            for (String neighborName : testGraph.getAdjacency().row(nodeName).keySet()) {
                for (String typeName : testGraph.getAdjacency().get(nodeName, neighborName)) {
                    Node node = nodeMapping.get(nodeName);
                    Node neighbor = nodeMapping.get(neighborName);
                    switch (typeName) {
                        case TestGraphConstants.type1:
                            node.getType1().add(neighbor);
                            break;
                        case TestGraphConstants.type2:
                            node.getType2().add(neighbor);
                            break;
                        case TestGraphConstants.type3:
                            node.getType3().add(neighbor);
                            break;
                        default:
                            throw new IllegalArgumentException(
                                "The TestGraph " + testGraph + " contains an unrecognized type " + typeName);
                    }

                }
            }

        }
        return container;
    }

    protected void addNode(Map<String, Node> nodeMapping, NetworkFactory factory, NodeContainer container,
                           String nodeName) {
        if (!nodeMapping.containsKey(nodeName)) {
            Node node = factory.createNode();
            node.setName(nodeName);
            nodeMapping.put(nodeName, node);
            container.getNodes().add(node);
        }
    }

}
