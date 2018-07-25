import analyzer.AnalyzerUtil
import hu.bme.mit.ga.adapters.csv.CsvGraphAdapter
import hu.bme.mit.ga.metrics.impl.simple.Density
import hu.bme.mit.ga.metrics.impl.simple.NumberOfEdges
import hu.bme.mit.ga.metrics.impl.simple.NumberOfNodes
import hu.bme.mit.ga.metrics.impl.typed.*
import org.supercsv.cellprocessor.constraint.NotNull

println('Graph metric analyzer')
println('=====================')

def graphs = ['test']

// Set the reportUrl if you would like to receive a Slack notification when the analysis finished.
// The default configuration points to our research group's Slack.
//reportUrl = "https://hooks.slack.com/services/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"

def metrics = [
    new Density(),
    new TypedActivity(),
    new TypedDegree(),
    new TypedClusteringCoefficientDef1(),
    new TypedClusteringCoefficientDef2(),
    new TypedClusteringCoefficientDef3(),
    new TypedDegreeEntropy(),
    new NodeTypedConnectivity(),
    new EdgeTypedConnectivity(),
    new EdgeOverlap(),
    new MultiplexParticipationCoefficient(),
    new NodeActivity(),
    new NumberOfEdges(),
    new NumberOfNodes(),
    new NumberOfTypedEdges(),
    new PairwiseMultiplexity(),
]

graphs.each { graph ->
    println()
    println("Analyzing graph: ${graph}")
    println("----------------------------------------")

    def calculatedMetrics = []
    def adapter = new CsvGraphAdapter(new NotNull())
    print("Loading graph...")
    adapter.init("graphs/${graph}-nodes.csv", "graphs/${graph}-edges.csv")
    println(" loaded")
    println()
    println("Calculating metrics:")

    metrics.each { metric ->
        println("- ${metric.getName()}")
        metric.evaluate(adapter)
        calculatedMetrics += metric
    }

    AnalyzerUtil.writeToTsv(calculatedMetrics, "../reporting/tsvs/${graph}.tsv");
}
