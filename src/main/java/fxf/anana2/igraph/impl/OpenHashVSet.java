package fxf.anana2.igraph.impl;

import fxf.anana2.igraph.ModifiableVSet;
import fxf.anana2.igraph.VSet;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;

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

    public OpenHashVSet(int... vs) {
        delegate = new IntOpenHashSet(vs);
    }

    private OpenHashVSet(IntOpenHashSet vs) {
        delegate = vs;
    }

    public OpenHashVSet(int size) {
        delegate = new IntOpenHashSet(size);
    }

    public OpenHashVSet(OpenHashVSet vs) {
        delegate = new IntOpenHashSet(vs.delegate);
    }

    public OpenHashVSet(VSet vs) {
        delegate = new IntOpenHashSet(vs.size());
        
        var iter = vs.iterator();
        while (iter.hasNext()) {
            delegate.add(iter.nextInt());
        }
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
        if (vs instanceof OpenHashVSet) {
            return delegate.addAll(((OpenHashVSet) vs).delegate);
        } else {
            var iter = vs.iterator();
            boolean changed = false;
            while (iter.hasNext()) {
                if (delegate.add(iter.nextInt())) {
                    changed = true;
                }
            }
            return changed;
        }
    }

    @Override
    public boolean remove(int v) {
        return delegate.remove(v);
    }

    @Override
    public boolean remove(VSet vs) {
        if (vs instanceof OpenHashVSet) {
            return delegate.removeAll(((OpenHashVSet) vs).delegate);
        } else {
            var iter = vs.iterator();
            boolean changed = false;
            while (iter.hasNext()) {
                if (delegate.remove(iter.nextInt())) {
                    changed = true;
                }
            }
            return changed;
        }
    }

    @Override
    public boolean retain(VSet vs) {
        if (vs instanceof OpenHashVSet) {
            return delegate.retainAll(((OpenHashVSet) vs).delegate);
        } else {
            var iter = iterator();
            boolean changed = false;
            while (iter.hasNext()) {
                int v = iter.nextInt();
                if (!vs.contains(v)) {
                    remove(v);
                    changed = true;
                }
            }
            return changed;
        }
    }

    @Override
    public void clear() {
        delegate.clear();
    }
}
