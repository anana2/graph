package fxf.anana2.igraph.impl;

import java.util.Objects;
import fxf.anana2.igraph.ContiguousGraph;
import fxf.anana2.igraph.ModifiableParentTree;

public class ArrayParentTreeView implements ModifiableParentTree, ContiguousGraph {
    private int[] array;
    private final int offset;
    private final int size;
    private int root;

    private ArrayParentTreeView(int[] array, int offset, int size, int root) {
        Objects.checkFromIndexSize(offset, size, array.length);

        this.array = array;
        this.offset = offset;
        this.size = size;
        this.root = root;
        
        array[root] = -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int root() {
        return root;
    }

    @Override
    public int root(int r) {
        int out = root;
        root = r;

        return out;
    }

    @Override
    public int parent(int v) {
        return array[index(v)];
    }

    @Override
    public int parent(int v, int p) {
        int out = array[index(v)];
        array[index(v)] = p;
        return out;
    }

    private final int index(final int v) {
        return Objects.checkIndex(v, size) + offset;
    }
}
