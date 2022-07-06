package fxf.anana2.igraph;

import it.unimi.dsi.fastutil.ints.IntIterable;
import it.unimi.dsi.fastutil.ints.IntSet;

public interface VSet extends IntIterable {
    int size();

    boolean contains(int v);

    IntSet asIntSet();
}
