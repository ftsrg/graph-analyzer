package hu.bme.mit.mba.tests.model;

import static hu.bme.mit.mba.tests.model.ModelContext.dim1;
import static hu.bme.mit.mba.tests.model.ModelContext.dim2;
import static hu.bme.mit.mba.tests.model.ModelContext.dim3;
import static hu.bme.mit.mba.tests.model.ModelContext.node1;
import static hu.bme.mit.mba.tests.model.ModelContext.node2;
import static hu.bme.mit.mba.tests.model.ModelContext.node3;
import static hu.bme.mit.mba.tests.model.ModelContext.node4;
import static hu.bme.mit.mba.tests.model.ModelContext.node5;

public enum TestModelTypes implements TestModelInitializer {

    Loop_1T {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node1);
            return model;
        }

    },

    Loop_2T {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node1);
            model.addEdge(dim2, node1, node1);
            return model;
        }

    },
    Motif3N_1 {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            return model;
        }

    },
    Motif3N_2 {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node2, node1);
            return model;
        }

    },
    Motif3N_3 {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node2, node1);
            return model;
        }

    },
    Motif3N_4 {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_5 {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_6 {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node2, node3);
            model.addEdge(dim1, node2, node1);
            return model;
        }

    },
    Motif3N_7 {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node2, node1);
            return model;
        }

    },
    Motif3N_8 {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node2, node1);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node3, node1);
            return model;
        }

    },
    Motif3N_9 {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_10 {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_11 {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node2, node1);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_12 {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node2, node1);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_13 {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node2, node1);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node2, node3);
            model.addEdge(dim1, node3, node2);
            return model;
        }

    },
    Motif3N_3_2T {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim2, node2, node1);
            return model;
        }
    },
    Motif3N_6_2T {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node2, node3);
            model.addEdge(dim2, node2, node1);
            return model;
        }
    },
    Motif3N_7_2T {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim2, node2, node1);
            return model;
        }
    },
    Motif3N_8_2T {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim2, node2, node1);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim2, node3, node1);
            return model;
        }

    },
    Motif3N_10_2T {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim2, node3, node1);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_11_2T {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim2, node1, node3);
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node2, node1);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_12_2T {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim2, node2, node1);
            model.addEdge(dim2, node1, node3);
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_13_2T {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim2, node2, node1);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim2, node3, node1);
            model.addEdge(dim1, node2, node3);
            model.addEdge(dim2, node3, node2);
            return model;
        }
    },

    Motif5N_1_3T {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim2, node2, node3);
            model.addEdge(dim3, node3, node4);
            model.addEdge(dim2, node4, node5);
            model.addEdge(dim1, node5, node2);
            return model;
        }

    },

    Motif5N_2_3T {

        @Override
        public TestModel init() {
            TestModel model = new TestModel();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim2, node2, node1);
            model.addEdge(dim1, node2, node3);
            model.addEdge(dim3, node3, node1);
            model.addEdge(dim1, node3, node4);
            model.addEdge(dim2, node3, node4);
            model.addEdge(dim3, node4, node5);
            model.addEdge(dim2, node5, node2);
            return model;
        }

    },
}
