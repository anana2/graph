package fxf.anana2.igraph;

public interface ModifiableFlowGraph extends FlowGraph, ModifiableGraph {
    int source(int s);

    int[] succ(int v);
    
    int[] succ(int v, int[] ss);
}
