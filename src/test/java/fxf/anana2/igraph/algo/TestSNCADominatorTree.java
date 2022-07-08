package fxf.anana2.igraph.algo;

import org.junit.Test;
import fxf.anana2.igraph.ContiguousGraph;
import fxf.anana2.igraph.FlowGraph;
import fxf.anana2.igraph.ParentTree;
import fxf.anana2.igraph.ReverseFlowGraph;
import fxf.anana2.igraph.VSet;

public class TestSNCADominatorTree {

    static class TestGraph implements FlowGraph, ReverseFlowGraph, ContiguousGraph {
        final int[] vertices = {0, 1, 2, 3};
        final VSet[] succ = {VSet.of(1, 2), VSet.of(3), VSet.of(3), VSet.of(0)};
        final VSet[] pred = {VSet.of(3), VSet.of(0), VSet.of(0), VSet.of(1, 2)};

        @Override
        public int size() {
            return vertices.length;
        }

        @Override
        public int source() {
            return 0;
        }

        @Override
        public VSet succ(int v) {
            return succ[v];
        }

        @Override
        public VSet pred(int v) {
            return pred[v];
        }
    }
    
    @Test
    public void construct() {
        DominatorTrees.snca(new TestGraph());
    }
}
