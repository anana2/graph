package fxf.anana2.intgraph;

public interface ModifiableParentTree extends ParentTree {
    int root(int r);

    int parent(int v, int p);
}
