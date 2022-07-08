package fxf.anana2.igraph;

public interface ModifiableParentTree extends ParentTree {
    int root(int r);

    int parent(int v, int p);
}
