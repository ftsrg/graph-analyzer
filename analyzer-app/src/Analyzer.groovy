import analyzer.AnalyzerUtil
import hu.bme.mit.ga.adapters.csv.CsvGraphAdapter
import hu.bme.mit.ga.metrics.impl.typed.*
import org.supercsv.cellprocessor.constraint.NotNull

println('Graph metric analyzer')
println('=====================')

def graphs = ['test']

// Set the reportUrl if you would like to receive a Slack notification when the analysis finished.
// The default configuration points to our research group's Slack.
//reportUrl = "https://hooks.slack.com/services/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"

def reps = 3

def metrics = [
    new TypedClusteringCoefficientDef1(TypedClusteringCoefficientDef1.Implementation.EDGELIST),
    new TypedClusteringCoefficientDef1(TypedClusteringCoefficientDef1.Implementation.OJALGO),
    new TypedClusteringCoefficientDef1(TypedClusteringCoefficientDef1.Implementation.OJALGO_EW),
    new TypedClusteringCoefficientDef1(TypedClusteringCoefficientDef1.Implementation.OJALGO_EW_STREAM),
    new TypedClusteringCoefficientDef1(TypedClusteringCoefficientDef1.Implementation.UJMP),
    new TypedClusteringCoefficientDef1(TypedClusteringCoefficientDef1.Implementation.UJMP_EW),
    new TypedClusteringCoefficientDef2(TypedClusteringCoefficientDef2.Implementation.EDGELIST),
    new TypedClusteringCoefficientDef2(TypedClusteringCoefficientDef2.Implementation.OJALGO_EW),
    new TypedClusteringCoefficientDef2(TypedClusteringCoefficientDef2.Implementation.OJALGO_MMM),
    new TypedClusteringCoefficientDef2(TypedClusteringCoefficientDef2.Implementation.OJALGO_EW_STREAM),
    new EdgeOverlap(EdgeOverlap.Implementation.OJALGO),
    new EdgeOverlap(EdgeOverlap.Implementation.EDGELIST)
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
        reps.times {
            metric.evaluate(adapter)
        }
        calculatedMetrics += metric
    }

    AnalyzerUtil.writeToTsv(calculatedMetrics, "../reporting/tsvs/${graph}.tsv");
    AnalyzerUtil.writePerformanceToTsv(calculatedMetrics, graph, "../reporting/perf-tsvs/${graph}.tsv")
}
