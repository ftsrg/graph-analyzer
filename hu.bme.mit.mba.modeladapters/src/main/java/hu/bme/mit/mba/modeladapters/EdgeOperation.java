package hu.bme.mit.mba.modeladapters;

import com.google.common.base.Preconditions;

public class EdgeOperation<N, T> {

    private final N sourceNode;
    private final N targetNode;
    private final T edgeType;

    public EdgeOperation(N sourceNode, N targetNode, T edgeType) {
        checkNull(sourceNode, "sourceNode");
        checkNull(targetNode, "targetNode");
        checkNull(edgeType, "edgeType");
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
        this.edgeType = edgeType;
    }

    public N getSourceNode() {
        return sourceNode;
    }

    public N getTargetNode() {
        return targetNode;
    }

    public T getEdgeType() {
        return edgeType;
    }

    private void checkNull(Object sourceNode, String name) {
        Preconditions.checkArgument(sourceNode != null, "The " + name + " cannot be null");
    }

}
