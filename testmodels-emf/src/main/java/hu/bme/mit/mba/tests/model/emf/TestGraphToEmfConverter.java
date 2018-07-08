package hu.bme.mit.mba.tests.model.emf;

import hu.bme.mit.mba.tests.model.TestGraph;
import hu.bme.mit.mba.tests.model.TestGraphConstants;
import hu.bme.mit.mba.tests.model.TestGraphToConcreteFormatConverter;
import hu.bme.mit.mba.tests.model.emf.network.NetworkFactory;
import hu.bme.mit.mba.tests.model.emf.network.NetworkPackage;
import hu.bme.mit.mba.tests.model.emf.network.Node;
import hu.bme.mit.mba.tests.model.emf.network.NodeContainer;

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
                for (String dimensionName : testGraph.getAdjacency().get(nodeName, neighborName)) {
                    Node node = nodeMapping.get(nodeName);
                    Node neighbor = nodeMapping.get(neighborName);
                    switch (dimensionName) {
                        case TestGraphConstants.dim1:
                            node.getDim1().add(neighbor);
                            break;
                        case TestGraphConstants.dim2:
                            node.getDim2().add(neighbor);
                            break;
                        case TestGraphConstants.dim3:
                            node.getDim3().add(neighbor);
                            break;
                        default:
                            throw new IllegalArgumentException(
                                "The TestGraph " + testGraph + " contains an unrecognized dimension " + dimensionName);
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
