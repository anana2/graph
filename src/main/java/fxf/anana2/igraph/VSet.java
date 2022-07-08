package fxf.anana2.igraph;

import it.unimi.dsi.fastutil.ints.IntIterable;

public interface VSet extends IntIterable {
    int size();

    boolean contains(int v);
}
