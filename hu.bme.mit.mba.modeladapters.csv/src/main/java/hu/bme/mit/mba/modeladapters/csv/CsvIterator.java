package hu.bme.mit.mba.modeladapters.csv;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvMapReader;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

public class CsvIterator implements Iterator<Long> {
    ICsvMapReader reader;
    String[] header;
    CellProcessor[] processors;
    Map<String, Object> nodeMap;
    public CsvIterator(ICsvMapReader reader, String[] header, CellProcessor[] processors){
        this.reader = reader;
        this.header = header;
        this.processors=processors;

    }
    @Override
    public boolean hasNext() {
        boolean hasNext=false;
        try {
            hasNext= (this.nodeMap=reader.read(header, processors))!=null;
        } catch (IOException e) {
        }
        return hasNext;
    }

    @Override
    public Long next() {
        return (Long) nodeMap.get("id");
    }

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer<? super Long> action) {

    }
}
