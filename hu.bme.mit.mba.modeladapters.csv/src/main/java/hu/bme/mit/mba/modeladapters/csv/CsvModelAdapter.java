package hu.bme.mit.mba.modeladapters.csv;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.ModelIndexer;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CsvModelAdapter extends ModelAdapter<Long, String> {

    public CsvModelAdapter(Collection<String> dimensions) {
        super(dimensions);
    }

	public void init(String nodeCsv, String relsCsv) throws IOException {
        final CsvMapReader nodeMapReader = new CsvMapReader(new FileReader(nodeCsv), CsvPreference.STANDARD_PREFERENCE);
        final CsvMapReader edgeMapReader = new CsvMapReader(new FileReader(relsCsv), CsvPreference.STANDARD_PREFERENCE);

	    // nodes
        final String[] nodeHeader = {"id"};
        final CellProcessor[] nodeProcessors = getProcessors("nodes");

        final CsvIterator iterator = new CsvIterator(nodeMapReader, nodeHeader, nodeProcessors);
        List<Long> nodes = new ArrayList<>();
        while (iterator.hasNext()) {
            final Long id = iterator.next();
            nodes.add(id);
        }

        // edges
        final CellProcessor[] edgeProcessors = getProcessors("edges");
        final String[] edgeHeader = {"source_id", "type", "target_id"};
        indexer = new ModelIndexer<>(nodes, dimensions);
        Map<String, Object> edgeMap;
        while (((edgeMap = edgeMapReader.read(edgeHeader, edgeProcessors)) != null)){
            Long sourceId = (Long) edgeMap.get("source_id");
            Long targetId = (Long) edgeMap.get("target_id");
            String relType = (String) edgeMap.get("type");
            addEdge(sourceId, relType, targetId);
        }
	}

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
