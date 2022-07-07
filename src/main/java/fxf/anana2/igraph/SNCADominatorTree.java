package fxf.anana2.igraph;

import it.unimi.dsi.fastutil.booleans.BooleanArrayList;
import it.unimi.dsi.fastutil.booleans.BooleanList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntStack;

public class SNCADominatorTree implements DominatorTree, BidirectionalFlowGraph {
    private final BidirectionalFlowGraph graph;
    private final IntArrayList buffer;

    private SNCADominatorTree(BidirectionalFlowGraph graph) {
        this.graph = graph;
        this.buffer = IntArrayList.wrap(new int[6 * graph.size()]);
    }

    public static SNCADominatorTree from(BidirectionalFlowGraph graph, int root) {
        var instance = new SNCADominatorTree(graph).run();
        return instance;
    }

    @Override
    public boolean dominates(int u, int v) {
        while (v != -1) {
            if (v == u) {
                return true;
            }
            v = parent(v);
        }
        return false;
    }
    @Override
    public int parent(int v) {
        return buffer.getInt(0 * size() + v);
    }

    private int parent(int v, int p) {
        return buffer.set(0 * size() + v, p);
    }

    @Override
    public int source() {
        return graph.source();
    }


    @Override
    public VSet succ(int v) {
        return graph.succ(v);
    }

    @Override
    public VSet pred(int v) {
        return graph.pred(v);
    }

    @Override
    public int size() {
        return size();
    }

    private final int ancestor(int v) {
        return buffer.getInt(1 * size() + v);
    }

    private final int ancestor(int v, int u) {
        return buffer.set(1 * size() + v, u);
    }

    private final int label(int v) {
        return buffer.getInt(2 * size() + v);
    }

    private final int label(int v, int u) {
        return buffer.set(2 * size() + v, u);
    }

    private final int semi(int v) {
        return buffer.getInt(3 * size() + v);
    }

    private final int semi(int v, int u) {
        return buffer.set(3 * size() + v, u);
    }

    private final int ith(int v) {
        return buffer.getInt(4 * size() + v);
    }

    private final int ith(int v, int number) {
        return buffer.set(4 * size() + v, number);
    }

    private final int pre(int v) {
        return buffer.getInt(5 * size() + v);
    }

    private final int pre(int v, int number) {
        return buffer.set(5 * size() + v, number);
    }

    private SNCADominatorTree run() {
        // initialize buffer
        for (int v = 0; v < size(); v++) {
            ancestor(v, -1);
            label(v, v);
            semi(v, v);
        }

        // step 1
        // dfs

        var r = source();

        IntStack stack = new IntArrayList();
        stack.push(r);

        BooleanList seen = BooleanArrayList.wrap(new boolean[size()]); // default initialized to all false;
        seen.set(r, true);

        int probe = 0;

        while (!stack.isEmpty()) {
            int v = stack.popInt(); // pop node to be visited

            // visit
            ith(probe, v);
            pre(v, probe);
            probe++;

            var succs = graph.succ(v).iterator();
            while (succs.hasNext()) {
                int w = succs.nextInt();
                if (!seen.getBoolean(w)) {
                    ancestor(w, v);
                    // mark as seen here to avoid pushing the parent
                    seen.set(v, true);
                    stack.push(w);
                }
            }
        }


        // step 2
        // compute semi dominators

        for (int i = size() - 1; i > 0; i--) {
            int w = ith(i);
            parent(w, ancestor(w));

            var succs = pred(w).iterator();
            while (succs.hasNext()) {
                int v = succs.nextInt();
                int j = pre(v);

                if (v == 0) {
                    continue;
                }

                int u;

                if (j <= i) {
                    u = v;
                } else {
                    // TODO compress
                    u = label(v);
                }

                if (semi(u) < semi(w)) {
                    semi(w, semi(u));
                }

            }
        }

        return this;
    }
}
