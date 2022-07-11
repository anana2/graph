package fxf.anana2.igraph;

public interface ModifiableReverseFlowGraph extends ReverseFlowGraph, ModifiableGraph {
    int[] pred(int v);
}
