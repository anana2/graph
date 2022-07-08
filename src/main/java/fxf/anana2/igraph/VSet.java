package fxf.anana2.igraph;

import fxf.anana2.igraph.impl.OpenHashVSet;
import it.unimi.dsi.fastutil.ints.IntIterable;

public interface VSet extends IntIterable {
    int size();

    boolean contains(int v);

    static VSet of(int... vs) {
        return new OpenHashVSet(vs);
    }
}
