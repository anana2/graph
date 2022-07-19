package fxf.anana2.intgraph.algo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fxf.anana2.intgraph.FlowGraph;

public class TestSNCADominatorTree {

    static class TestGraph implements FlowGraph {
        final int[][] succ;

        TestGraph(int[][] succ) {
            this.succ = succ;
        }

        @Override
        public int size() {
            return this.succ.length;
        }

        @Override
        public int source() {
            return 0;
        }

        @Override
        public int[] succ(int v) {
            return succ[v];
        }
    }

    @Test
    public void lozange() {
        assertDoesNotThrow(() -> {
            var snca = SNCADominatorTree
                    .from(new TestGraph(new int[][] { { 1 }, { 2, 3 }, { 4 }, { 4 }, { 5 }, {} }));
            assertEquals("[(0,-1),(1,0),(2,1),(3,1),(4,1),(5,4)]", snca.toString());
        });
    }

    @Test
    public void sncaworst3() {
        assertDoesNotThrow(() -> {
            var snca = SNCADominatorTree.from(
                    new TestGraph(new int[][] { { 1, 4, 5, 6 }, { 2 }, { 3 }, { 4, 5, 6 }, {}, {}, {} }));
            assertEquals("[(0,-1),(1,0),(2,1),(3,2),(4,0),(5,0),(6,0)]", snca.toString());
        });
    }

    @Test
    public void itworst3() {
        assertDoesNotThrow(() -> {
            var snca = SNCADominatorTree.from(
                    new TestGraph(
                            new int[][] {
                                    { 1, 9, 10 }, // r
                                    { 2 }, // x1
                                    { 3 }, // x2
                                    { 4 }, // x3
                                    { 5, 10, 11, 12 }, // y1
                                    { 6, 10, 11, 12 }, // y2
                                    { 7, 10, 11, 12 }, // y3
                                    { 8 }, // z1
                                    { 7, 9 }, // z2
                                    { 8 }, // z3
                                    { 11 }, // w1
                                    { 12 }, // w2
                                    {} // w3
                            }));
            assertEquals("[(0,-1),(1,0),(2,1),(3,2),(4,3),(5,4),(6,5),(7,0),(8,0),(9,0),(10,0),(11,0),(12,0)]",
                    snca.toString());
        });
    }
}
