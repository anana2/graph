package fxf.anana2.igraph;

import it.unimi.dsi.fastutil.ints.IntSet;

public interface ModifiableVSet extends VSet {
    boolean add(int v);

    boolean add(VSet vs);

    boolean remove(int v);

    boolean remove(VSet vs);

    boolean retain(VSet vs);

    void clear();

    IntSet asModifiableIntSet();
}
