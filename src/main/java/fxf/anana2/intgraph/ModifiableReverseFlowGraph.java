package fxf.anana2.intgraph;

public interface ModifiableReverseFlowGraph extends ReverseFlowGraph, ModifiableGraph {
    int[] pred(int v);
}
