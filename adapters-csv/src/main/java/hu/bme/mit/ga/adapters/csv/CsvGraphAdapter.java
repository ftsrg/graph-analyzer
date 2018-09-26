package hu.bme.mit.ga.adapters.csv;

import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.adapters.GraphIndexer;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
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
        File file = new File(nodeCsv);
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
        lineNumberReader.skip(Long.MAX_VALUE);
        int numberOfNodes = lineNumberReader.getLineNumber();
        lineNumberReader.close();
        indexer = new GraphIndexer<>(numberOfNodes);

        final CsvMapReader edgeMapReader = new CsvMapReader(new FileReader(relsCsv), PREF);
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
