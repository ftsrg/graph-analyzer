import analyzer.AnalyzerUtil
import hu.bme.mit.ga.adapters.csv.CsvGraphAdapter
import hu.bme.mit.ga.metrics.impl.simple.ClusteringCoefficient
import hu.bme.mit.ga.metrics.impl.typed.*
import org.supercsv.cellprocessor.constraint.NotNull

println('Graph metric analyzer')
println('=====================')

def graphs = ['paradise']

// Set the reportUrl if you would like to receive a Slack notification when the analysis finished.
// The default configuration points to our research group's Slack.
//reportUrl = "https://hooks.slack.com/services/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"

def reps = 1

def metrics = [
new ClusteringCoefficient(),
new TypedClusteringCoefficientDef1(),
new TypedClusteringCoefficientDef2(),
new EdgeOverlap(),
new TypedDegreeEntropy()
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
