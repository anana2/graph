package fxf.anana2.intgraph.algo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import fxf.anana2.intgraph.FlowGraph;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntSet;

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

    class MapFlowGraph implements FlowGraph {

        private final int source;
        private final Int2ObjectMap<IntSet> succ;

        MapFlowGraph(int source, Int2ObjectMap<IntSet> succ) {
            this.source = source;
            this.succ = succ;
        }

        @Override
        public int size() {
            return succ.size();
        }

        @Override
        public int source() {
            return source;
        }

        @Override
        public int[] succ(int v) {
            return succ.get(v).toIntArray();
        }
    }

    @Test
    void triangle() {
        var map = new Int2ObjectOpenHashMap<IntSet>();
        map.put(1, IntSet.of());
        map.put(2, IntSet.of(3));
        map.put(3, IntSet.of(4));
        map.put(4, IntSet.of(5));
        map.put(5, IntSet.of(6));
        map.put(6, IntSet.of(7, 8));
        map.put(7, IntSet.of(11));
        map.put(8, IntSet.of(9));
        map.put(9, IntSet.of(10));
        map.put(10, IntSet.of(7));
        map.put(11, IntSet.of(12));
        map.put(12, IntSet.of(13));
        map.put(13, IntSet.of(1));
        var snca = assertDoesNotThrow(() -> {
            return SNCADominatorTree.from(new MapFlowGraph(2, map));
        });
        assertEquals("[(1,13),(2,-1),(3,2),(4,3),(5,4),(6,5),(7,6),(8,6),(9,8),(10,9),(11,7),(12,11),(13,12)]",
                snca.toString());
    }
}
