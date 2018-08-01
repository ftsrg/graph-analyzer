package my;

import org.testng.annotations.Test;
import org.ujmp.core.Matrix;
import org.ujmp.core.SparseMatrix;

public class MyTest {

    @Test
    public void test() {
        int n = 200*1000*1000;
        SparseMatrix m = SparseMatrix.Factory.zeros(n, n);

        m.setAsInt(1, 0, 0);
        m.setAsInt(1, 0, 1);
        m.setAsInt(1, 1, 0);
        m.setAsInt(1, 1, 1);
        final Matrix m1m1 = m.mtimes(m);

        System.out.println(m1m1);
    }

}
