package csv;

import hu.bme.mit.mba.modeladapters.csv.CsvModelAdapter;
import hu.bme.mit.mba.modelmetrics.ModelMetric;
import hu.bme.mit.mba.modelmetrics.impl.typed.DimensionalDegree;
import hu.bme.mit.mba.modelmetrics.impl.typed.MultiplexParticipationCoefficient;
import hu.bme.mit.mba.modelmetrics.impl.typed.PairwiseMultiplexity;
import org.junit.Test;

public class CsvTest {

    @Test
    public void test() {
        System.out.println("Example for using concrete metric");
        // init metrics
        DimensionalDegree dd = new DimensionalDegree();
        MultiplexParticipationCoefficient mpc = new MultiplexParticipationCoefficient();
        PairwiseMultiplexity pm = new PairwiseMultiplexity();

        // the adapter represents a bridge between model and metrics
        CsvModelAdapter adapter = new CsvModelAdapter();
        // adapter must be initialized by a container node, this will create
        // an index which is necessary during the evaluation
        adapter.init("nodes.csv", "edges.csv");

        // calculate metrics
        dd.evaluate(adapter);
        mpc.evaluate(adapter);
        pm.evaluate(adapter);

        System.out.println("\nResults:");

        // get the results
        showResult(dd);
        showResult(mpc);
        showResult(pm);
    }

    private void showResult(ModelMetric metric) {
        System.out.println(String.format("Data of %s : %s", metric.getName(), metric.getData().toString()));
    }

}
