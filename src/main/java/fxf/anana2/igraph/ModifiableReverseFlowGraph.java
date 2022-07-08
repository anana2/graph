package fxf.anana2.igraph;

public interface ModifiableReverseFlowGraph extends ReverseFlowGraph, ModifiableGraph {
    ModifiableVSet pred(int v);
}
