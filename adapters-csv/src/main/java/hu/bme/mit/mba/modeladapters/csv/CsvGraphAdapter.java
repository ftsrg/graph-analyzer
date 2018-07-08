package hu.bme.mit.mba.modeladapters.csv;

import hu.bme.mit.mba.modeladapters.GraphAdapter;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvGraphAdapter<N> extends GraphAdapter<N, String> {

    public static final CsvPreference PREF = new CsvPreference.Builder('\0', '\t', "\n").build();
    private final CellProcessor cellProcessor;

    public CsvGraphAdapter() {
        this.cellProcessor = new ParseLong();
    }

    public CsvGraphAdapter(CellProcessor cellProcessor) {
        this.cellProcessor = cellProcessor;
    }

    public void init(String nodeCsv, String relsCsv) throws IOException {
        final CsvMapReader nodeMapReader = new CsvMapReader(new FileReader(nodeCsv), PREF);
        final CsvMapReader edgeMapReader = new CsvMapReader(new FileReader(relsCsv), PREF);

        // nodes
        final String[] nodeHeader = {"id"};
        final CellProcessor[] nodeProcessors = getProcessors("nodes");

        final CsvIterator<N> iterator = new CsvIterator<>(nodeMapReader, nodeHeader, nodeProcessors);
        List<N> nodes = new ArrayList<>();
        while (iterator.hasNext()) {
            final N id = iterator.next();
            nodes.add(id);
        }

        // edges
        final CellProcessor[] edgeProcessors = getProcessors("edges");
        final String[] edgeHeader = {"source_id", "type", "target_id"};
        Map<String, Object> edgeMap;
        while (((edgeMap = edgeMapReader.read(edgeHeader, edgeProcessors)) != null)) {
            N sourceId = (N) edgeMap.get("source_id");
            N targetId = (N) edgeMap.get("target_id");
            String relType = (String) edgeMap.get("type");
            addEdge(sourceId, relType, targetId);
        }
    }

    protected void addEdge(final N node, final String relationshipType, final N neighbor) {
        if (neighbor != null && relationshipType != null) {
            indexer.addEdge(relationshipType, node, neighbor);
        }
    }

    private CellProcessor[] getProcessors(String type) {
        CellProcessor[] processors;
        if (type.equals("nodes")) {
            processors = new CellProcessor[]{
                new NotNull(cellProcessor)};
        } else {
            processors = new CellProcessor[]{
                new NotNull(cellProcessor),
                new NotNull(),
                new NotNull(cellProcessor)
            };
        }
        return processors;
    }

}
