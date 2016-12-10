package hu.bme.mit.mba.modeladapters;

import java.util.Objects;

import com.google.common.base.Preconditions;

public class Triple<N, T> {

    private final N sourceNode;
    private final N targetNode;
    private final T edgeType;

    public Triple(N sourceNode, N targetNode, T edgeType) {
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

    @Override
    public String toString() {
        return "Triple [sourceNode=" + sourceNode + ", targetNode=" + targetNode + ", edgeType=" + edgeType + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceNode, targetNode, edgeType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Triple<?, ?> other = (Triple<?, ?>) obj;
        if (edgeType == null) {
            if (other.edgeType != null) {
                return false;
            }
        } else if (!edgeType.equals(other.edgeType)) {
            return false;
        }
        if (sourceNode == null) {
            if (other.sourceNode != null) {
                return false;
            }
        } else if (!sourceNode.equals(other.sourceNode)) {
            return false;
        }
        if (targetNode == null) {
            if (other.targetNode != null) {
                return false;
            }
        } else if (!targetNode.equals(other.targetNode)) {
            return false;
        }
        return true;
    }

}
