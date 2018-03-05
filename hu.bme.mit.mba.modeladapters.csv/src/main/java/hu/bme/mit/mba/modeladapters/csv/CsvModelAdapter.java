package hu.bme.mit.mba.modeladapters.csv;

import hu.bme.mit.mba.modeladapters.ModelIndexer;
import hu.bme.mit.mba.modeladapters.TypedModelAdapter;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class CsvModelAdapter extends TypedModelAdapter<Long, String> {
    private ICsvMapReader edgeMapReader;
    private ICsvMapReader nodeMapReader;

	@Override
	public Iterator<Long> getModelIterator() {
	    final String[] header = {"id"};
        final CellProcessor[] processors = getProcessors("nodes");
        return new CsvIterator(nodeMapReader, header,processors);
	}

	public void init(String nodeCsv, String relsCsv) {
        try {
            nodeMapReader = new CsvMapReader(new FileReader(nodeCsv), CsvPreference.STANDARD_PREFERENCE);
            edgeMapReader = new CsvMapReader(new FileReader(relsCsv), CsvPreference.STANDARD_PREFERENCE);
            init();
            }
        catch (java.io.IOException e) {
        }
    }
    protected void init() throws IOException {
        final CellProcessor[] processors = getProcessors("edges");
        final String[] header = {"source_id", "type", "target_id"};
        indexer = new ModelIndexer<>();
        Map<String, Object> edgeMap;
        while (((edgeMap = edgeMapReader.read(header,processors)) != null)){
            Long sourceId = (Long) edgeMap.get("source_id");
            Long targetId = (Long)edgeMap.get("target_id");
            String relType = (String) edgeMap.get("type");
            addEdge(sourceId, relType, targetId);
    }}
	protected void addEdge(final Long node, final String relationshipType, final Long neighbor) {
		if (neighbor != null && relationshipType != null) {
			indexer.addEdge(relationshipType, node, neighbor);
		}
	}
    private static CellProcessor[] getProcessors(String type) {
        CellProcessor[] processors;
        if (type.equals("nodes")) {
            processors = new CellProcessor[]{
                new NotNull(new ParseLong())};
        } else {
            processors = new CellProcessor[]{
                new NotNull(new ParseLong()),
                new NotNull(),
                new NotNull(new ParseLong())
            };
        }

        return processors;
    }

}
