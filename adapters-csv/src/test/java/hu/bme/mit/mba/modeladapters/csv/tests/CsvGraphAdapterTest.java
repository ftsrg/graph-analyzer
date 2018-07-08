package hu.bme.mit.mba.modeladapters.csv.tests;

import hu.bme.mit.mba.modeladapters.GraphAdapter;
import hu.bme.mit.mba.modeladapters.csv.CsvGraphAdapter;
import hu.bme.mit.mba.modeladapters.tests.GraphAdapterTest;
import hu.bme.mit.mba.tests.model.TestGraph;
import hu.bme.mit.mba.tests.model.TestGraphInstances;
import hu.bme.mit.mba.tests.model.csv.TestGraphToCsvConverter;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.Map;

public class CsvGraphAdapterTest extends GraphAdapterTest {

    private CsvGraphAdapter csvModelAdapter;
    private Map<String, Long> nodeMapping;

    @Override
    public void runTests(final TestGraphInstances modelType, final Runnable checker) throws IOException {
        final TestGraph testGraph = modelType.init();
        final TestGraphToCsvConverter converter = new TestGraphToCsvConverter();
        final Pair<String, String> csvFiles = converter.convert(testGraph);
        nodeMapping = converter.getNodeMapping();

        csvModelAdapter = new CsvGraphAdapter();
        csvModelAdapter.init(csvFiles.getLeft(), csvFiles.getRight());
        checker.run();
    }

    @Override
    protected <N, T> void degree(final GraphAdapter<N, T> adapter, final N element, final int indegree, final int outdegree) {
        super.degree(csvModelAdapter, nodeMapping.get(element.toString()), indegree, outdegree);
    }

    @Override
    protected <N, T> void nodes(final GraphAdapter<N, T> adapter, final int expected) {
        super.nodes(csvModelAdapter, expected);
    }

    @Override
    protected <N, T> void edges(final GraphAdapter<N, T> adapter, final int expected) {
        super.edges(csvModelAdapter, expected);
    }

    @Override
    protected <N, T> void neighbor(final GraphAdapter<N, T> adapter, final N source, final N target) {
        super.neighbor(csvModelAdapter, nodeMapping.get(source.toString()), nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void degree(final GraphAdapter<N, T> adapter, final N element, final T type, final int indegree, final int outdegree) {
        super.degree(csvModelAdapter, nodeMapping.get(element), type.toString(), indegree, outdegree);
    }

    @Override
    protected <N, T> void neighbor(final GraphAdapter<N, T> adapter, final T type, final N source, final N target) {
        super.neighbor(csvModelAdapter, type.toString(), nodeMapping.get(source.toString()),
            nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void notNeighbor(final GraphAdapter<N, T> adapter, final T type, final N source, final N target) {
        super.notNeighbor(csvModelAdapter, type.toString(), nodeMapping.get(source.toString()),
            nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void types(final GraphAdapter<N, T> adapter, final int expected, final N node) {
        super.types(csvModelAdapter, expected, nodeMapping.get(node));
    }

    @Override
    protected <N, T> void types(final GraphAdapter<N, T> adapter, final int expected) {
        super.types(csvModelAdapter, expected);
    }

    @Override
    protected <N, T> void nodes(final GraphAdapter<N, T> adapter, final T type, final int expected) {
        super.nodes(csvModelAdapter, type.toString(), expected);
    }
}
