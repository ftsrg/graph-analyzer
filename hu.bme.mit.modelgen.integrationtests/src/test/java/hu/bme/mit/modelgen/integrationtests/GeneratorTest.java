package hu.bme.mit.modelgen.integrationtests;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import hu.bme.mit.modelgen.ModelGeneratorRunner;
import hu.bme.mit.modelgen.model.TestEvolvingModelProvider;

public class GeneratorTest {

    private TestEvolvingModelProvider sourceModelProvider;
    private TestEvolvingModelProvider targetModelProvider;

    @Before
    public void setUp() {
        sourceModelProvider = new TestEvolvingModelProvider();
        targetModelProvider = new TestEvolvingModelProvider();
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
