package fxf.anana2.intgraph.algo;

import fxf.anana2.intgraph.FlowGraph;
import fxf.anana2.intgraph.ParentTree;

public interface DominatorTrees extends ParentTree {
    public static ParentTree snca(FlowGraph graph) {
        return SNCADominatorTree.from(graph);
    }
}
