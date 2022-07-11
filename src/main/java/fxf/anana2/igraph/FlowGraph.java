package fxf.anana2.igraph;

public interface FlowGraph extends Graph {
    int source();

    int[] succ(int v);
}
