package hu.bme.mit.modelgen.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hu.bme.mit.mba.modeladapters.ModelProvider;
import hu.bme.mit.mba.modelanalyzer.ModelAnalyzer;
import hu.bme.mit.mba.modelmetrics.impl.simple.Degrees;
import hu.bme.mit.mba.modelmetrics.impl.simple.NumberOfEdges;
import hu.bme.mit.mba.modelmetrics.impl.simple.NumberOfNodes;

public class DomainAnalyzer {

    private ModelAnalyzer modelAnalyzer;

    public DomainAnalyzer(ModelAnalyzer modelAnalyzer) {
        this.modelAnalyzer = modelAnalyzer;
    }

    public DomainAnalyzer() {
        this.modelAnalyzer = new ModelAnalyzer();
    }

    public <N, T> DomainAnalysisResult<T> analyze(List<ModelProvider<N, T>> modelProviders) {
        NumberOfNodes nodes = new NumberOfNodes();
        nodes.getData().setValue(50);
        NumberOfEdges edges = new NumberOfEdges();
        edges.getData().setValue(7);
        Set<T> types = new HashSet<>();
        DomainDescriptor<T> descriptor = new DomainDescriptor<T>(types, nodes, edges);

        DomainAnalysisResult<T> result = new DomainAnalysisResult<>(descriptor);

        Degrees degrees = new Degrees();
        degrees.getData().getValues().add(1);
        degrees.getData().getValues().add(2);
        degrees.getData().getValues().add(3);
        degrees.getData().getValues().add(3);
        degrees.getData().getValues().add(2);
        degrees.getData().getValues().add(1);
        result.addMetricEquality(new MetricEquality(degrees, 0.0));

        return result;
    }

    public ModelAnalyzer getModelAnalyzer() {
        return modelAnalyzer;
    }

}
