package fxf.anana2.igraph.algs.impl;

import fxf.anana2.igraph.ContiguousGraph;
import fxf.anana2.igraph.FlowGraph;
import fxf.anana2.igraph.ParentTree;
import fxf.anana2.igraph.ReverseFlowGraph;
import it.unimi.dsi.fastutil.booleans.BooleanArrayList;
import it.unimi.dsi.fastutil.booleans.BooleanList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntStack;

public class SNCADominatorTree<G extends FlowGraph & ReverseFlowGraph> implements ParentTree, ContiguousGraph {
    private final G graph;
    private int[] idom;
    private int[] ancestor;
    private int[] semi;
    private int[] label;
    private int[] pre;

    public static <G extends FlowGraph> SNCADominatorTree<?> from(G graph) {
        return new SNCADominatorTree<>((FlowGraph & ReverseFlowGraph) graph).run();
    }

    private SNCADominatorTree(G graph) {
        this.graph = graph;
        this.idom = new int[graph.size()];
        this.ancestor = new int[graph.size()];
        this.semi = new int[graph.size()];
        this.label = new int[graph.size()];
        this.pre = new int[graph.size()];
    }

    @Override
    public int size() {
        return graph.size();
    }

    @Override
    public int root() {
        return graph.source();
    }

    @Override
    public final int parent(int v) {
        return idom[v];
    }

    private SNCADominatorTree<G> run() {
        // initialize buffers
        for (int v = 0; v < size(); v++) {
            label[v] = semi[v] = v;
        }

        // step 1
        // dfs

        dfs();

        // step 2
        // compute semi dominators

        // for v in vertices in reverse preorder
        for (int i = size() - 1; i > 0; i--) {
            int v = pre[i];

            var succs = graph.pred(v).iterator();
            while (succs.hasNext()) {
                int u = succs.nextInt();

                compress(u);

                if (label[u] < semi[v]) {
                    semi[v] = label[u];
                }
            }

            label[v] = semi[v];
        }

        // step 3
        // compute idom

        // for v in vertices in preorder
        for (int i = 0; i < size(); i++) {
            int v = pre[i];

            while (idom[v] > semi[v]) {
                idom[v] = idom[idom[v]];
            }
        }

        return this;
    }

    void dfs() {
        IntStack stack = new IntArrayList();
        stack.push(root());

        BooleanList seen = BooleanArrayList.wrap(new boolean[size()]); // default initialized to all
                                                                       // false;
        seen.set(root(), true);

        while (!stack.isEmpty()) {
            int v = stack.popInt(); // pop node to be visited

            // for each successors w of v in G
            var succs = graph.succ(v).iterator();
            while (succs.hasNext()) {
                int w = succs.nextInt();

                if (seen.getBoolean(w)) {
                    continue;
                }

                ancestor[w] = v;

                // mark as seen here to avoid pushing the parent
                seen.set(v, true);
                stack.push(w);
            }
        }
    }

    void compress(int v) {
        if (v == root()) {
            return;
        }

        IntStack stack = new IntArrayList();

        do {
            stack.push(v);
            v = ancestor[v];
        } while (v != root());

        v = stack.popInt();
        while (!stack.isEmpty()) {
            int u = v;
            v = stack.popInt();
            if (label[u] < label[v]) {
                label[v] = label[u];
            }
            ancestor[v] = ancestor[u];
        } ;
    }
}
