package fxf.anana2.igraph;

public interface FlowGraph extends Graph {
    int source();
    VSet succ(int v);
}
