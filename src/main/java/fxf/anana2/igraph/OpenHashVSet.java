package fxf.anana2.igraph;

import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.ints.IntSets;

public class OpenHashVSet implements ModifiableVSet {
    private final IntOpenHashSet delegate;

    public static OpenHashVSet view(OpenHashVSet vs) {
        return new OpenHashVSet(vs.delegate);
    }

    public static OpenHashVSet intersect(VSet a, VSet... vss) {
        OpenHashVSet out = new OpenHashVSet(a);
        for (VSet vs : vss) {
            out.retain(vs);
        }
        return out;
    }

    public static OpenHashVSet union(VSet... vss) {
        OpenHashVSet out = new OpenHashVSet();
        for (VSet vs : vss) {
            out.add(vs);
        }
        return out;
    }

    private OpenHashVSet(IntOpenHashSet vs) {
        delegate = vs;
    }

    public OpenHashVSet(int size) {
        delegate = new IntOpenHashSet(size);
    }

    public OpenHashVSet(VSet vs) {
        delegate = new IntOpenHashSet(vs.asIntSet());
    }

    public OpenHashVSet() {
        delegate = new IntOpenHashSet();
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean contains(int v) {
        return delegate.contains(v);
    }

    @Override
    public IntIterator iterator() {
        return delegate.iterator();
    }

    @Override
    public boolean add(int v) {
        return delegate.add(v);
    }

    @Override
    public boolean add(VSet vs) {
        return delegate.addAll(vs.asIntSet());
    }

    @Override
    public boolean remove(int v) {
        return delegate.remove(v);
    }

    @Override
    public boolean remove(VSet vs) {
        return delegate.addAll(vs.asIntSet());
    }

    @Override
    public boolean retain(VSet vs) {
        return delegate.retainAll(vs.asIntSet());
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public IntSet asIntSet() {
        return IntSets.unmodifiable(asModifiableIntSet());
    }

    @Override
    public IntSet asModifiableIntSet() {
        return delegate;
    }
}
