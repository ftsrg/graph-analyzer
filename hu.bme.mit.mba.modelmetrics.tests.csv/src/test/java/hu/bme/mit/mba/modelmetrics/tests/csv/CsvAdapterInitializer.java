package hu.bme.mit.mba.modelmetrics.tests.csv;

import hu.bme.mit.mba.modeladapters.csv.CsvModelAdapter;
import hu.bme.mit.mba.tests.model.TestModel;
import hu.bme.mit.mba.tests.model.csv.CsvTestModelToNetworkConverter;
import org.neo4j.graphdb.GraphDatabaseService;

public class CsvAdapterInitializer {

	public static CsvModelAdapter getAdapter(TestModel testModel) {
		GraphDatabaseService graph = new CsvTestModelToNetworkConverter().convert(testModel);
		CsvModelAdapter adapter = new CsvModelAdapter();
		adapter.init(graph);
		return adapter;
	}

}
