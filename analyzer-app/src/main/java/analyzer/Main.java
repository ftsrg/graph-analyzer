package analyzer;

import hu.bme.mit.ga.adapters.csv.CsvGraphAdapter;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;
import hu.bme.mit.ga.metrics.impl.simple.Density;
import hu.bme.mit.ga.metrics.impl.simple.NumberOfEdges;
import hu.bme.mit.ga.metrics.impl.simple.NumberOfNodes;
import hu.bme.mit.ga.metrics.impl.typed.DimensionActivity;
import hu.bme.mit.ga.metrics.impl.typed.DimensionalClusteringCoefficient;
import hu.bme.mit.ga.metrics.impl.typed.DimensionalDegree;
import hu.bme.mit.ga.metrics.impl.typed.DimensionalDegreeEntropy;
import hu.bme.mit.ga.metrics.impl.typed.EdgeDimensionConnectivity;
import hu.bme.mit.ga.metrics.impl.typed.EdgeOverlap;
import hu.bme.mit.ga.metrics.impl.typed.MultiplexParticipationCoefficient;
import hu.bme.mit.ga.metrics.impl.typed.NodeActivity;
import hu.bme.mit.ga.metrics.impl.typed.NumberOfTypedEdges;
import hu.bme.mit.ga.metrics.impl.typed.PairwiseMultiplexity;
import org.supercsv.cellprocessor.constraint.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        final String graph = args[0];
        final String metrics = graph + ".tsv";
        final String nodes = graph + "-nodes.csv";
        final String edges = graph + "-edges.csv";

        final String[] header = new String[]{"Category", "Instance", "Index", "Value"};
        List<AbstractGraphMetric> modelMetrics = new ArrayList<>();

        // the adapter represents a bridge between model and metrics
        CsvGraphAdapter adapter = new CsvGraphAdapter(new NotNull());

        // adapter must be initialized, this will create an index which is necessary during the evaluation
        adapter.init(nodes, edges);
        System.out.println("initialized");

        // calculate metrics
//        System.out.println("degree");
//        Degrees dd = new Degrees();
//        dd.evaluate(adapter);
//        modelMetrics.add(dd);

        System.out.println("density");
        Density d = new Density();
        d.evaluate(adapter);
        modelMetrics.add(d);

        System.out.println("dimension activity");
        DimensionActivity dimensionActivity = new DimensionActivity();
        dimensionActivity.evaluate(adapter);
        modelMetrics.add(dimensionActivity);

        System.out.println("dimensional degree");
        DimensionalDegree dimensionalDegree = new DimensionalDegree();
        dimensionalDegree.evaluate(adapter);
        modelMetrics.add(dimensionalDegree);

        System.out.println("dimensional clustering coefficient");
        DimensionalClusteringCoefficient dcc = new DimensionalClusteringCoefficient();
        dcc.evaluate(adapter);
        modelMetrics.add(dcc);

        System.out.println("dimensional degree entropy");
        DimensionalDegreeEntropy dde = new DimensionalDegreeEntropy();
        dde.evaluate(adapter);
        modelMetrics.add(dde);

        System.out.println("edge dimension connectivity");
        EdgeDimensionConnectivity edgeDimensionConnectivity = new EdgeDimensionConnectivity();
        edgeDimensionConnectivity.evaluate(adapter);
        modelMetrics.add(edgeDimensionConnectivity);

        System.out.println("edge overlap");
        EdgeOverlap eo = new EdgeOverlap();
        eo.evaluate(adapter);
        modelMetrics.add(eo);

        MultiplexParticipationCoefficient mpc = new MultiplexParticipationCoefficient();
        System.out.println("mpc");
        mpc.evaluate(adapter);
        modelMetrics.add(mpc);

        System.out.println("node activity");
        NodeActivity nodeActivity = new NodeActivity();
        nodeActivity.evaluate(adapter);
        modelMetrics.add(nodeActivity);

        System.out.println("number of edges");
        NumberOfEdges numberOfEdges = new NumberOfEdges();
        numberOfEdges.evaluate(adapter);
        modelMetrics.add(numberOfEdges);

        System.out.println("number of nodes");
        NumberOfNodes numberOfNodes = new NumberOfNodes();
        numberOfNodes.evaluate(adapter);
        modelMetrics.add(numberOfNodes);

        System.out.println("number of typed edges");
        NumberOfTypedEdges numberOfTypedEdges = new NumberOfTypedEdges();
        numberOfEdges.evaluate(adapter);
        modelMetrics.add(numberOfTypedEdges);

        System.out.println("pairwise multiplexity");
        PairwiseMultiplexity pm = new PairwiseMultiplexity();
        pm.evaluate(adapter);
        modelMetrics.add(pm);

        Analyzer.writeToTsv(modelMetrics, header, metrics);
    }

}
