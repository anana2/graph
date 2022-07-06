package fxf.anana2.igraph;

public interface DominatorTree extends ParentTree {
    boolean dominates(int u, int v);
}
