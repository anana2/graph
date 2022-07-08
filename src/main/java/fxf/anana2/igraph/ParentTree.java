package fxf.anana2.igraph;

public interface ParentTree extends Tree {
    int parent(int v);

    @Override
    default boolean has(int u, int v) {
        return has(u) && has(v) && u == parent(v);
    }
}
