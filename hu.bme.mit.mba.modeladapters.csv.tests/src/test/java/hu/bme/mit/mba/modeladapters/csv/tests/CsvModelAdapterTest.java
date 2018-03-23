package hu.bme.mit.mba.modeladapters.csv.tests;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.csv.CsvModelAdapter;
import hu.bme.mit.mba.modeladapters.tests.ModelAdapterTest;
import hu.bme.mit.mba.tests.model.TestModel;
import hu.bme.mit.mba.tests.model.TestModelTypes;
import hu.bme.mit.mba.tests.model.csv.CsvTestModelToNetworkConverter;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.Map;

public class CsvModelAdapterTest extends ModelAdapterTest {

    private CsvModelAdapter csvModelAdapter;
    private Map<String, Long> nodeMapping;

    @Override
    public void runTests(final TestModelTypes modelType, final Runnable checker) throws IOException {
        final TestModel testModel = modelType.init();
        final CsvTestModelToNetworkConverter converter = new CsvTestModelToNetworkConverter();
        final Pair<String, String> csvFiles = converter.convert(testModel);
        nodeMapping = converter.getNodeMapping();

        csvModelAdapter = new CsvModelAdapter();
        csvModelAdapter.init(csvFiles.getLeft(), csvFiles.getRight());
        checker.run();
    }

    @Override
    protected <N, T> void degree(final ModelAdapter<N, T> adapter, final N element, final int indegree, final int outdegree) {
        super.degree(csvModelAdapter, nodeMapping.get(element.toString()), indegree, outdegree);
    }

    @Override
    protected <N, T> void nodes(final ModelAdapter<N, T> adapter, final int expected) {
        super.nodes(csvModelAdapter, expected);
    }

    @Override
    protected <N, T> void edges(final ModelAdapter<N, T> adapter, final int expected) {
        super.edges(csvModelAdapter, expected);
    }

    @Override
    protected <N, T> void neighbor(final ModelAdapter<N, T> adapter, final N source, final N target) {
        super.neighbor(csvModelAdapter, nodeMapping.get(source.toString()), nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void degree(final ModelAdapter<N, T> adapter, final N element, final T type, final int indegree, final int outdegree) {
        super.degree(csvModelAdapter, nodeMapping.get(element), type.toString(), indegree, outdegree);
    }

    @Override
    protected <N, T> void neighbor(final ModelAdapter<N, T> adapter, final T type, final N source, final N target) {
        super.neighbor(csvModelAdapter, type.toString(), nodeMapping.get(source.toString()),
                nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void notNeighbor(final ModelAdapter<N, T> adapter, final T type, final N source, final N target) {
        super.notNeighbor(csvModelAdapter, type.toString(), nodeMapping.get(source.toString()),
                nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void types(final ModelAdapter<N, T> adapter, final int expected, final N node) {
        super.types(csvModelAdapter, expected, nodeMapping.get(node));
    }

    @Override
    protected <N, T> void types(final ModelAdapter<N, T> adapter, final int expected) {
        super.types(csvModelAdapter, expected);
    }

    @Override
    protected <N, T> void nodes(final ModelAdapter<N, T> adapter, final T type, final int expected) {
        super.nodes(csvModelAdapter, type.toString(), expected);
    }
}
