package hu.bme.mit.ga.tests.graph.csv;

import com.google.common.collect.ImmutableMap;
import hu.bme.mit.ga.tests.graph.TestGraph;
import hu.bme.mit.ga.tests.graph.TestGraphToConcreteFormatConverter;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestGraphToCsvConverter extends TestGraphToConcreteFormatConverter<Long, Pair<String, String>> {

    Long i = 0L;

    public Pair<String, String> convert(final TestGraph testGraph) throws IOException {
        nodeMapping = new HashMap<>();

        for (final String nodeName : testGraph.getAdjacency().rowKeySet()) {
            addNode(nodeMapping, nodeName);

            for (final String neighborName : testGraph.getAdjacency().row(nodeName).keySet()) {
                addNode(nodeMapping, neighborName);
            }
        }

        ICsvMapWriter nodesCsvWriter = null;
        try {
            nodesCsvWriter = new CsvMapWriter(new FileWriter("nodes.csv"), CsvPreference.TAB_PREFERENCE);

            final CellProcessor[] processors = getProcessors("nodes");
            final String header[] = new String[]{"id"};

            for (Long node : nodeMapping.values()) {
                final Map<String, Long> line = ImmutableMap.of("id", node);
                nodesCsvWriter.write(line, header, processors);
            }
        } finally {
            if (nodesCsvWriter != null) {
                nodesCsvWriter.close();
            }
        }

        ICsvMapWriter edgesCsvWriter = null;
        try {
            edgesCsvWriter = new CsvMapWriter(new FileWriter("edges.csv"), CsvPreference.TAB_PREFERENCE);

            final CellProcessor[] processors = getProcessors("edges");
            final String header[] = new String[]{"source_id", "type", "target_id"};

            for (final String nodeName : testGraph.getAdjacency().rowKeySet()) {
                for (final String neighborName : testGraph.getAdjacency().row(nodeName).keySet()) {
                    for (final String dimensionName : testGraph.getAdjacency().get(nodeName, neighborName)) {
                        final Long node = nodeMapping.get(nodeName);
                        final Long neighbor = nodeMapping.get(neighborName);

                        final Map<String, Object> line = ImmutableMap.of(
                            "source_id", node,
                            "type", dimensionName,
                            "target_id", neighbor
                        );
                        edgesCsvWriter.write(line, header, processors);
                    }
                }
            }
        } finally {
            if (edgesCsvWriter != null) {
                edgesCsvWriter.close();
            }
        }

        return ImmutablePair.of("nodes.csv", "edges.csv");
    }

    private void addNode(final Map<String, Long> nodeMapping, final String nodeName) {
        if (!nodeMapping.containsKey(nodeName)) {
            nodeMapping.put(nodeName, i);
            i++;
        }
    }

    private static CellProcessor[] getProcessors(String type) {
        final CellProcessor[] processors;
        if (type.equals("nodes")) {
            processors = new CellProcessor[]{
                new NotNull(new ParseLong())
            };
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
