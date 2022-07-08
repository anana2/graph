package fxf.anana2.igraph.algs;

import fxf.anana2.igraph.ContiguousGraph;
import fxf.anana2.igraph.FlowGraph;
import fxf.anana2.igraph.ParentTree;
import fxf.anana2.igraph.PreOrderGraph;
import fxf.anana2.igraph.ReverseFlowGraph;
import fxf.anana2.igraph.algs.impl.SNCADominatorTree;

public interface DominatorTrees extends ParentTree {
    public static <G extends FlowGraph & ReverseFlowGraph & ContiguousGraph & PreOrderGraph> ParentTree snca(G graph) {
        return SNCADominatorTree.from(graph);
    }
}
