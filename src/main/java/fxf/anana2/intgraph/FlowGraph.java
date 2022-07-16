package fxf.anana2.intgraph;

public interface FlowGraph extends Graph {
    int source();

    int[] succ(int v);
}
