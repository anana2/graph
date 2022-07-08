package fxf.anana2.igraph.algs;

import fxf.anana2.igraph.ParentTree;

public interface ParentTrees {
    static boolean isancestor(ParentTree tree, int v, int u) {
        while (v != tree.root()) {
            if (v == u) {
                return true;
            }
            v = tree.parent(v);
        }
        return false;
    }
}
