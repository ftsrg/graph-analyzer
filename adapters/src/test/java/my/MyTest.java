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

//        DoubleMatrix2D m2 = new SparseDoubleMatrix2D(n, n);
//        m2.set(0, 0, 1);
//        m2.set(0, 1, 1);
//        m2.set(1, 0, 1);
//        m2.set(1, 1, 1);

//        OpenMapRealMatrix m3 = new OpenMapRealMatrix(n, n);
    }

}
