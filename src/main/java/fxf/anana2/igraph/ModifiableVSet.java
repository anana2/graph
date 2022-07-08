package fxf.anana2.igraph;

public interface ModifiableVSet extends VSet {
    boolean add(int v);

    boolean add(VSet vs);

    boolean remove(int v);

    boolean remove(VSet vs);

    boolean retain(VSet vs);

    void clear();
}
