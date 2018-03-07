package csv;

import com.google.common.collect.ImmutableList;
import hu.bme.mit.mba.modeladapters.csv.CsvModelAdapter;
import hu.bme.mit.mba.modelmetrics.ModelMetric;
import org.testng.annotations.Test;

import java.io.IOException;

public class CsvTest {

    @Test
    public void test() throws IOException {
        System.out.println("Example for using concrete metric");
        // the adapter represents a bridge between model and metrics
        CsvModelAdapter adapter = new CsvModelAdapter(ImmutableList.of("intermediary_of", "officer_of", "registered_address"));
        // adapter must be initialized by a container node, this will create
        // an index which is necessary during the evaluation
        adapter.init("nodes.csv", "edges.csv");
        System.out.println("initialized");

//        // calculate metrics
//        System.out.println("dd");
//        DimensionalDegree dd = new DimensionalDegree();
//        dd.evaluateT(adapter);
//        showResult(dd);
//
//        System.out.println("pm");
//        PairwiseMultiplexity pm = new PairwiseMultiplexity();
//        pm.evaluateT(adapter);
//        showResult(pm);
//
//        MultiplexParticipationCoefficient mpc = new MultiplexParticipationCoefficient();
//        System.out.println("mpc");
//        mpc.evaluateT(adapter);
////        showResult(mpc);
        }

    private void showResult(ModelMetric metric) {
        System.out.println(String.format("Data of %s : %s", metric.getName(), metric.getData().toString()));
    }

}
