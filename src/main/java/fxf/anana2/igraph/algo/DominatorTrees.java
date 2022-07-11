package fxf.anana2.igraph.algo;

import fxf.anana2.igraph.FlowGraph;
import fxf.anana2.igraph.ParentTree;

public interface DominatorTrees extends ParentTree {
    public static ParentTree snca(FlowGraph graph) {
        return SNCADominatorTree.from(graph);
    }
}
