package fxf.anana2.igraph;

public interface ModifiableFlowGraph extends FlowGraph {
    int source(int s);
    ModifiableVSet succ(int v);
}
