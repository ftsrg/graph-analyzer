package hu.bme.mit.ga.metrics.impl.typed;

import com.google.common.collect.Multimap;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.adapters.GraphIndexer;
import hu.bme.mit.ga.base.data.MapData;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;
import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.matrix.store.SparseStore;

import java.util.*;

import static org.ojalgo.function.PrimitiveFunction.ADD;
import static org.ojalgo.function.PrimitiveFunction.MULTIPLY;

public class EdgeOverlap extends AbstractGraphMetric<MapData<String, Double>> {
    protected Implementation implementation;

    public EdgeOverlap(Implementation implementation) {
        super("EdgeOverlap", new MapData<>());
        this.implementation = implementation;
    }

    public EdgeOverlap() {
        this(Implementation.EDGELIST);
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        switch (implementation) {
            case OJALGO:
                evaluateAllMatrix(adapter);
                break;
            case EDGELIST:
                evaluateAllEdgeList(adapter);
                break;
        }
    }

    protected <N, T> void evaluateAllEdgeList(GraphAdapter<N, T> adapter) {
        for (T condType : adapter.getIndexer().getTypes()) {
            for (T type : adapter.getIndexer().getTypes()) {
                if (type != condType) {
                    evaluate(adapter, condType, type);
                }
            }
        }

    }


    protected <N, T> void evaluateAllMatrix(GraphAdapter<N, T> adapter) {
        GraphIndexer<N, T> indexer = adapter.getIndexer();
        PrimitiveDenseStore ones = PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1);
        ones.operateOnAll(ADD, 1).supplyTo(ones);
        for (T condType : adapter.getIndexer().getTypes()) {
            double d = indexer.getAdjacencyMatrix2().get(condType).multiply(ones).transpose().multiply(ones).get(0, 0);
            for (T type : adapter.getIndexer().getTypes()) {
                if (type != condType) {
                    SparseStore A = adapter.getIndexer().getAdjacencyMatrix2().get(type);
                    SparseStore B = adapter.getIndexer().getAdjacencyMatrix2().get(condType);
                    SparseStore<Double> C = SparseStore.PRIMITIVE.make(indexer.getSize(), indexer.getSize());
                    A.operateOnMatching(MULTIPLY, B).supplyTo(C);
                    double n = C.multiply(ones).transpose().multiply(ones).get(0, 0);
                    data.put(getKey(type, condType), n / d);
                }
            }
        }

    }


    private <T, N> void evaluate(GraphAdapter<N, T> adapter, T condType, T type) {
        GraphIndexer indexer = adapter.getIndexer();
        Multimap<N, N> edgesCondType = indexer.getOutgoing(condType);
        Double edges = (double) edgesCondType.entries().size();
        Multimap<N, N> typeOutgoing = indexer.getOutgoing(type);
        double intersection = 0.0;
        for (N n : edgesCondType.keySet()) {
            Collection<N> nodes = edgesCondType.get(n);
            for (N n2 : nodes) {
                if (typeOutgoing.containsEntry(n, n2) | typeOutgoing.containsEntry(n2, n)) {
                    intersection += 1;
                }
            }
        }
        data.put(getKey(type, condType), intersection / edges);
    }

    protected <T> String getKey(T firstType, T secondType) {
        return String.format("%s-%s", firstType.toString(), secondType.toString());
    }


    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        for (String type : data.getValues().keySet()) {
            Double value = data.getValues().get(type);
            Map<String, Object> row = new HashMap<>();
            row.put(header[0], "EdgeOverlap");
            row.put(header[1], type);
            row.put(header[2], null);
            row.put(header[3], value);
            values.add(row);
        }
        return values;
    }

    public enum Implementation {OJALGO, EDGELIST}

}
