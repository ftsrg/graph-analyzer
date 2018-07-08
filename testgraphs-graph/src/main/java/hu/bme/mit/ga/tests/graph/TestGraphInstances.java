package hu.bme.mit.ga.tests.graph;

public enum TestGraphInstances implements TestGraphInitializer {

    Loop_1T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node1);
            return model;
        }

    },

    Loop_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node1, TestGraphConstants.node1);
            return model;
        }

    },
    Motif3N_1 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            return model;
        }

    },
    Motif3N_2 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node1);
            return model;
        }

    },
    Motif3N_3 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node1);
            return model;
        }

    },
    Motif3N_4 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node3);
            return model;
        }

    },
    Motif3N_5 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node3);
            return model;
        }

    },
    Motif3N_6 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node1);
            return model;
        }

    },
    Motif3N_7 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node3, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node1);
            return model;
        }

    },
    Motif3N_8 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node3, TestGraphConstants.node1);
            return model;
        }

    },
    Motif3N_9 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node3, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node3);
            return model;
        }

    },
    Motif3N_10 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node3, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node3);
            return model;
        }

    },
    Motif3N_11 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node3, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node3);
            return model;
        }

    },
    Motif3N_12 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node3, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node3);
            return model;
        }

    },
    Motif3N_13 {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node3, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node3, TestGraphConstants.node2);
            return model;
        }

    },
    Motif3N_3_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node2, TestGraphConstants.node1);
            return model;
        }
    },
    Motif3N_6_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node2, TestGraphConstants.node1);
            return model;
        }
    },
    Motif3N_7_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node3, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node2, TestGraphConstants.node1);
            return model;
        }
    },
    Motif3N_8_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node2, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node3, TestGraphConstants.node1);
            return model;
        }

    },
    Motif3N_10_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node3, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node3);
            return model;
        }

    },
    Motif3N_11_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node3, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node3);
            return model;
        }

    },
    Motif3N_12_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node2, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node3, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node3);
            return model;
        }

    },
    Motif3N_13_2T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node2, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node3, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node3, TestGraphConstants.node2);
            return model;
        }
    },

    Motif5N_1_3T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node2, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim3, TestGraphConstants.node3, TestGraphConstants.node4);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node4, TestGraphConstants.node5);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node5, TestGraphConstants.node2);
            return model;
        }

    },

    Motif5N_2_3T {
        @Override
        public TestGraph init() {
            TestGraph model = new TestGraph();
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node1, TestGraphConstants.node2);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node2, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node2, TestGraphConstants.node3);
            model.addEdge(TestGraphConstants.dim3, TestGraphConstants.node3, TestGraphConstants.node1);
            model.addEdge(TestGraphConstants.dim1, TestGraphConstants.node3, TestGraphConstants.node4);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node3, TestGraphConstants.node4);
            model.addEdge(TestGraphConstants.dim3, TestGraphConstants.node4, TestGraphConstants.node5);
            model.addEdge(TestGraphConstants.dim2, TestGraphConstants.node5, TestGraphConstants.node2);
            return model;
        }

    },
}
