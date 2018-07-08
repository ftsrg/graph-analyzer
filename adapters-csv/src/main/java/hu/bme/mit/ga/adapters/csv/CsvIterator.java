package hu.bme.mit.ga.adapters.csv;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvMapReader;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

public class CsvIterator<N> implements Iterator<N> {

    protected ICsvMapReader reader;
    protected String[] header;
    protected CellProcessor[] processors;
    protected Map<String, Object> nodeMap;

    public CsvIterator(ICsvMapReader reader, String[] header, CellProcessor[] processors) {
        this.reader = reader;
        this.header = header;
        this.processors = processors;
    }

    @Override
    public boolean hasNext() {
        try {
            return (this.nodeMap = reader.read(header, processors)) != null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public N next() {
        return (N) nodeMap.get("id");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super N> action) {
        throw new UnsupportedOperationException();
    }

}
