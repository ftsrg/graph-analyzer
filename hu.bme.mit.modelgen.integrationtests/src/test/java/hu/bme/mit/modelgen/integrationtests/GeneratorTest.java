package hu.bme.mit.modelgen.integrationtests;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import hu.bme.mit.modelgen.ModelGeneratorRunner;
import hu.bme.mit.modelgen.model.FakeEvolvingModelProvider;

public class GeneratorTest {

    private FakeEvolvingModelProvider sourceModelProvider;
    private FakeEvolvingModelProvider targetModelProvider;

    @Before
    public void setUp() {
        sourceModelProvider = new FakeEvolvingModelProvider();
        targetModelProvider = new FakeEvolvingModelProvider();
    }

    @Test
    @Ignore
    public void testGeneration() {
        sourceModelProvider.addEdge("node1", "node2", "type1");
        sourceModelProvider.addEdge("node1", "node3", "type1");
        sourceModelProvider.addEdge("node2", "node3", "type1");

        ModelGeneratorRunner runner = new ModelGeneratorRunner();
        runner.run(Arrays.asList(sourceModelProvider), targetModelProvider);

        Assert.assertEquals(7, targetModelProvider.getAdjacency().size());
    }
}
