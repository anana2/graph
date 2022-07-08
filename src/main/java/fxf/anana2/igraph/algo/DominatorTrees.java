package fxf.anana2.igraph.algo;

import fxf.anana2.igraph.FlowGraph;
import fxf.anana2.igraph.ParentTree;
import fxf.anana2.igraph.ReverseFlowGraph;
import fxf.anana2.igraph.algo.impl.SNCADominatorTree;

public interface DominatorTrees extends ParentTree {
    public static <G extends FlowGraph & ReverseFlowGraph> ParentTree snca(G graph) {
        return SNCADominatorTree.from(graph);
    }
}
