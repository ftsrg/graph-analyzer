package analyzer;

import hu.bme.mit.ga.metrics.AbstractGraphMetric;
import hu.bme.mit.ga.metrics.GraphMetric;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AnalyzerUtil {

    public static final CellProcessor[] processors = new CellProcessor[]{
        new NotNull(),
        new Optional(),
        new Optional(),
        new NotNull()
    };

    public static final CellProcessor[] perfProcessors = new CellProcessor[]{
        new NotNull(),
        new NotNull(),
        new Optional(),
        new Optional()
    };


    public static void showResult(GraphMetric metric) {
        System.out.println(String.format("Data of %s : %s", metric.getName(), metric.getData().toString()));
    }

    public static void writeToTsv(List<AbstractGraphMetric> metrics, String filename) throws IOException {
        final String[] header = new String[] {"Category", "Instance", "Index", "Value"};
        ICsvMapWriter mapWriter = null;

        try {
            mapWriter = new CsvMapWriter(new FileWriter(filename), CsvPreference.TAB_PREFERENCE);

            // write the header
            mapWriter.writeHeader(header);

            for (AbstractGraphMetric metric : metrics) {
                List<Map<String, Object>> mapList = metric.getTsvMaps(header);
                for (Map<String, Object> map : mapList) {
                    mapWriter.write(map, header, processors);
                }
            }

        } finally {
            if (mapWriter != null) {
                mapWriter.close();
            }
        }

    }

    public static void writePerformanceToTsv(List<AbstractGraphMetric> metrics, String graph, String filename) throws IOException {
        final String[] header = new String[] {"graph", "metric", "algo", "t"};
        ICsvMapWriter mapWriter = null;

        try {
            mapWriter = new CsvMapWriter(new FileWriter(filename), CsvPreference.TAB_PREFERENCE);

            // write the header
            mapWriter.writeHeader(header);

            for (AbstractGraphMetric metric : metrics) {
                List<Map<String, Object>> mapList = metric.getPerformanceData();
                for (Map<String, Object> map : mapList) {
                    map.put("graph", graph);
                    mapWriter.write(map, header, perfProcessors);
                }
            }

        } finally {
            if (mapWriter != null) {
                mapWriter.close();
            }
        }

    }


}
