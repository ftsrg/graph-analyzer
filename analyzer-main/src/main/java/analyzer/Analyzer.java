package analyzer;

import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;
import hu.bme.mit.mba.modelmetrics.ModelMetric;
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

public class Analyzer {

    public static final CellProcessor[] processors = new CellProcessor[]{
        new NotNull(),
        new Optional(),
        new Optional(),
        new NotNull()
    };

    public static void showResult(ModelMetric metric) {
        System.out.println(String.format("Data of %s : %s", metric.getName(), metric.getData().toString()));
    }

    public static void writeToTsv(List<AbstractModelMetric> metrics, String[] header, String filename) throws IOException {
        ICsvMapWriter mapWriter = null;

        try {
            mapWriter = new CsvMapWriter(new FileWriter(filename), CsvPreference.TAB_PREFERENCE);

            // write the header
            mapWriter.writeHeader(header);

            for (AbstractModelMetric metric : metrics) {
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

}
