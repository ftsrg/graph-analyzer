package hu.bme.mit.ga.metrics.tests.csv;

import hu.bme.mit.ga.adapters.csv.CsvGraphAdapter;
import hu.bme.mit.ga.tests.graph.TestGraph;
import hu.bme.mit.ga.tests.graph.csv.TestGraphToCsvConverter;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;

public class CsvAdapterInitializer {

    public static CsvGraphAdapter getAdapter(TestGraph testGraph) throws IOException {
        final Pair<String, String> csvFiles = new TestGraphToCsvConverter().convert(testGraph);
        CsvGraphAdapter adapter = new CsvGraphAdapter();
        adapter.init(csvFiles.getLeft(), csvFiles.getRight());
        return adapter;
    }

}
