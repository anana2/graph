package fxf.anana2.igraph;

import it.unimi.dsi.fastutil.ints.IntList;

public class ListParentTreeView implements ModifiableParentTree {
    IntList delgate;

    public static ListParentTreeView view(IntList array) {
        return new ListParentTreeView(array);
    }

    private ListParentTreeView(IntList array) {
        delgate = array;
    }

    public int parent(int v) {
        return delgate.getInt(v);
    }

    public int size() {
        return delgate.size();
    }

    @Override
    public int parent(int v, int p) {
        return delgate.set(v, p);
    }
}
