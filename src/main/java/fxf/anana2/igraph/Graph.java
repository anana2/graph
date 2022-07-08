package fxf.anana2.igraph;

public interface Graph {
    int size();

    boolean has(int v);

    boolean has(int u, int v);
}
