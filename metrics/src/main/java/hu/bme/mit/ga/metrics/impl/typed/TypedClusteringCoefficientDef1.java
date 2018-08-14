package hu.bme.mit.ga.metrics.impl.typed;

import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.adapters.GraphIndexer;
import org.ujmp.core.Matrix;
import org.ujmp.core.SparseMatrix;
import org.ujmp.core.calculation.Calculation;

import java.util.*;

public class TypedClusteringCoefficientDef1 extends TypedClusteringCoefficient {

    public TypedClusteringCoefficientDef1() {
        super("TypedClusteringCoefficientDef1");
    }

    @Override
    public <N, T> void evaluate(final GraphAdapter<N, T> adapter, final N node) {
        long interConnected = 0;
        long numberOfNeighbors = 0;
        long numberOfPossibleConnections = 0;
        double coef = 0.0;

        for (T type1 : adapter.getIndexer().getTypes(node)) {
            numberOfNeighbors = adapter.getIndexer().getNeighbors(node, type1).size();
            if (useHeuristic && numberOfNeighbors > maxNeighbours) {
                coef = 0.0;
                data.add(coef);
                return;
            }
            numberOfPossibleConnections += numberOfNeighbors * (numberOfNeighbors - 1);
            for (N neighbor1 : adapter.getIndexer().getNeighbors(node, type1)) {
                for (N neighbor2 : adapter.getIndexer().getNeighbors(node, type1)) {
                    if (neighbor1 != neighbor2) {
                        Set<T> types = adapter.getIndexer().getTypes(neighbor1);
                        for (T type2 : types) {
                            if (type1 != type2) {
                                if (adapter.getIndexer().isAdjacentUndirected(neighbor1, neighbor2, type2)) {
                                    interConnected++;
                                }
                            }

                        }
                    }
                }
            }
        }
        if (numberOfPossibleConnections == 0) {
            coef = 0.0;
        } else {
            coef = (double) interConnected / (double) numberOfPossibleConnections;
        }
        data.add(coef);
    }

    @Override
    protected <N, T> void evaluateAll(final GraphAdapter<N, T> adapter) {
        GraphIndexer indexer = adapter.getIndexer();
        Matrix productSum = SparseMatrix.Factory.zeros(indexer.getSize(), indexer.getSize());
        Matrix degrees = SparseMatrix.Factory.zeros(indexer.getSize(), 1);
        for (T type1 : adapter.getIndexer().getTypes()) {
            for (T type2 : adapter.getIndexer().getTypes()) {
                if (type1 != type2) {
                    Matrix A = (Matrix) indexer.getAdjacencyMatrix().get(type1);
                    Matrix B = (Matrix) indexer.getAdjacencyMatrix().get(type2);
                    productSum = productSum.plus(A.mtimes(B).mtimes(A));
                    Matrix degreeVector = A.sum(Calculation.Ret.NEW, 1, false);
                    degrees = degrees.plus(degreeVector.times(degreeVector.minus(1)));
                }
            }
        }
        for (int i = 0; i < indexer.getSize(); i++) {
            double numerator = productSum.getAsDouble(i, i);
            double denominator = degrees.getAsDouble(i, 0) * (indexer.getTypes().size() - 1);
            if (denominator == 0) {
                data.add(0.0);
            } else {
                data.add(numerator / denominator);
            }
        }
    }

//    @Override
//    public <N, T> void evaluate(final GraphAdapter<N, T> adapter) {
//        evaluateAll(adapter);
//    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        int index = 0;
        for (Double v : data.getValues()) {
            Map<String, Object> value = new HashMap<>();
            value.put(header[0], "TypedClusteringCoefficientDef1");
            value.put(header[1], null);
            value.put(header[2], index);
            value.put(header[3], v);
            values.add(value);
            index++;
        }
        return values;
    }


}
