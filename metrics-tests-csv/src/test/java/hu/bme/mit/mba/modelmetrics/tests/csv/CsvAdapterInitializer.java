package hu.bme.mit.mba.modelmetrics.tests.csv;

import hu.bme.mit.mba.modeladapters.csv.CsvGraphAdapter;
import hu.bme.mit.mba.tests.model.TestGraph;
import hu.bme.mit.mba.tests.model.csv.TestGraphToCsvConverter;
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
