package hu.bme.mit.modelgen.op;

import java.util.Optional;

import com.google.common.base.Preconditions;

public class ResolvedOperation<N, T> {

    private final Optional<N> sourceNode;
    private final N targetNode;
    private final T edgeType;

    public ResolvedOperation(N sourceNode, N targetNode, T edgeType) {
        checkNull(sourceNode, "sourceNode");
        checkNull(targetNode, "targetNode");
        checkNull(edgeType, "edgeType");
        this.sourceNode = Optional.of(sourceNode);
        this.targetNode = targetNode;
        this.edgeType = edgeType;
    }

    public ResolvedOperation(N targetNode, T edgeType) {
        checkNull(targetNode, "targetNode");
        checkNull(edgeType, "edgeType");
        this.sourceNode = Optional.empty();
        this.targetNode = targetNode;
        this.edgeType = edgeType;
    }

    public Optional<N> getSourceNode() {
        return sourceNode;
    }

    public N getTargetNode() {
        return targetNode;
    }

    public T getEdgeType() {
        return edgeType;
    }

    private void checkNull(Object obj, String name) {
        Preconditions.checkArgument(obj != null, "The " + name + " cannot be null");
    }

    @Override
    public String toString() {
        return "ResolvedOperation [sourceNode=" + sourceNode + ", targetNode=" + targetNode + ", edgeType=" + edgeType
                + "]";
    }

    public static class Builder<N, T> {

        private N sourceNode;
        private N targetNode;
        private T edgeType;

        public Builder<N, T> source(N sourceNode) {
            this.sourceNode = sourceNode;
            return this;
        }

        public Builder<N, T> target(N targetNode) {
            this.targetNode = targetNode;
            return this;
        }

        public Builder<N, T> type(T edgeType) {
            this.edgeType = edgeType;
            return this;
        }

        public ResolvedOperation<N, T> build() {
            if (sourceNode == null) {
                return new ResolvedOperation<>(targetNode, edgeType);
            }
            return new ResolvedOperation<>(sourceNode, targetNode, edgeType);
        }

    }

}
