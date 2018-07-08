package hu.bme.mit.mba.tests.model;

import java.io.IOException;
import java.util.Map;

public abstract class TestGraphToConcreteFormatConverter<N, TGraph> {

    protected Map<String, N> nodeMapping;

    public abstract TGraph convert(TestGraph testGraph) throws IOException;

    public Map<String, N> getNodeMapping() {
        return nodeMapping;
    }

}
