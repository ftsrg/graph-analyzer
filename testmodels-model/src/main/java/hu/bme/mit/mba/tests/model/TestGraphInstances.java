package hu.bme.mit.mba.tests.model;

import static hu.bme.mit.mba.tests.model.TestGraphConstants.dim1;
import static hu.bme.mit.mba.tests.model.TestGraphConstants.dim2;
import static hu.bme.mit.mba.tests.model.TestGraphConstants.dim3;
import static hu.bme.mit.mba.tests.model.TestGraphConstants.node1;
import static hu.bme.mit.mba.tests.model.TestGraphConstants.node2;
import static hu.bme.mit.mba.tests.model.TestGraphConstants.node3;
import static hu.bme.mit.mba.tests.model.TestGraphConstants.node4;
import static hu.bme.mit.mba.tests.model.TestGraphConstants.node5;

public enum TestGraphInstances implements TestGraphInitializer {

    Loop_1T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node1);
            return model;
        }

    },

    Loop_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node1);
            model.addEdge(dim2, node1, node1);
            return model;
        }

    },
    Motif3N_1 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            return model;
        }

    },
    Motif3N_2 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node2, node1);
            return model;
        }

    },
    Motif3N_3 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node2, node1);
            return model;
        }

    },
    Motif3N_4 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_5 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_6 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node2, node3);
            model.addEdge(dim1, node2, node1);
            return model;
        }

    },
    Motif3N_7 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node2, node1);
            return model;
        }

    },
    Motif3N_8 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node2, node1);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node3, node1);
            return model;
        }

    },
    Motif3N_9 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_10 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_11 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node2, node1);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_12 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
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
        public TestGraph init() {
            TestGraph model = new TestGraph();
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
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim2, node2, node1);
            return model;
        }
    },
    Motif3N_6_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim1, node2, node3);
            model.addEdge(dim2, node2, node1);
            return model;
        }
    },
    Motif3N_7_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim2, node2, node1);
            return model;
        }
    },
    Motif3N_8_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim2, node2, node1);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim2, node3, node1);
            return model;
        }

    },
    Motif3N_10_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim1, node1, node2);
            model.addEdge(dim1, node1, node3);
            model.addEdge(dim2, node3, node1);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_11_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(dim2, node1, node3);
            model.addEdge(dim1, node3, node1);
            model.addEdge(dim1, node2, node1);
            model.addEdge(dim1, node2, node3);
            return model;
        }

    },
    Motif3N_12_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
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
        public TestGraph init() {
            TestGraph model = new TestGraph();
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
        public TestGraph init() {
            TestGraph model = new TestGraph();
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
        public TestGraph init() {
            TestGraph model = new TestGraph();
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
