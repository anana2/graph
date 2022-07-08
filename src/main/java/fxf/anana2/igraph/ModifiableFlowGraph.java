package fxf.anana2.igraph;

public interface ModifiableFlowGraph extends FlowGraph, ModifiableGraph {
    int source(int s);

    ModifiableVSet succ(int v);
    
    VSet succ(int v, VSet ss);
}
