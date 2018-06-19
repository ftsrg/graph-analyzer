package hu.bme.mit.mba.modelmetrics.tests.csv;

import hu.bme.mit.mba.modeladapters.csv.CsvModelAdapter;
import hu.bme.mit.mba.tests.model.TestModel;
import hu.bme.mit.mba.tests.model.csv.CsvTestModelToNetworkConverter;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;

public class CsvAdapterInitializer {

	public static CsvModelAdapter getAdapter(TestModel testModel) throws IOException {
        final Pair<String, String> csvFiles = new CsvTestModelToNetworkConverter().convert(testModel);
        CsvModelAdapter adapter = new CsvModelAdapter();
		adapter.init(csvFiles.getLeft(), csvFiles.getRight());
		return adapter;
	}

}
