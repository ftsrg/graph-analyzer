package hu.bme.mit.mba.tests.model.emf;

import java.util.HashMap;
import java.util.Map;

import hu.bme.mit.mba.tests.model.ModelContext;
import hu.bme.mit.mba.tests.model.TestModel;
import hu.bme.mit.mba.tests.model.emf.network.NetworkFactory;
import hu.bme.mit.mba.tests.model.emf.network.NetworkPackage;
import hu.bme.mit.mba.tests.model.emf.network.Node;
import hu.bme.mit.mba.tests.model.emf.network.NodeContainer;

public class EmfTestModelToNetworkConverter {

    private Map<String, Node> nodeMapping;

    public NodeContainer convert(TestModel testModel) {
        NetworkPackage.eINSTANCE.eClass();

        nodeMapping = new HashMap<>();
        NetworkFactory factory = NetworkFactory.eINSTANCE;
        NodeContainer container = factory.createNodeContainer();

        for (String nodeName : testModel.getAdjacency().rowKeySet()) {
            addNode(nodeMapping, factory, container, nodeName);

            for (String neighborName : testModel.getAdjacency().row(nodeName).keySet()) {
                addNode(nodeMapping, factory, container, neighborName);
            }

        }

        for (String nodeName : testModel.getAdjacency().rowKeySet()) {
            for (String neighborName : testModel.getAdjacency().row(nodeName).keySet()) {
                for (String dimensionName : testModel.getAdjacency().get(nodeName, neighborName)) {
                    Node node = nodeMapping.get(nodeName);
					Node neighbor = nodeMapping.get(neighborName);
					switch (dimensionName) {
                    case ModelContext.dim1:
                        node.getDim1().add(neighbor);
                        break;
                    case ModelContext.dim2:
                        node.getDim2().add(neighbor);
                        break;
                    case ModelContext.dim3:
                        node.getDim3().add(neighbor);
                        break;
                    default:
                        throw new IllegalArgumentException(
                                "The TestModel " + testModel + " contains an unrecognized dimension " + dimensionName);
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

    public Map<String, Node> getNodeMapping() {
        return nodeMapping;
    }

}
