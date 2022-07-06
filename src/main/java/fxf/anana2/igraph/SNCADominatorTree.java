package fxf.anana2.igraph;

import java.util.Deque;
import fr.inria.graph.DominatorTree;
import fr.inria.graph.FlowGraph;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntArrays;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;
import it.unimi.dsi.fastutil.ints.IntStack;

public class SNCADominatorTree implements DominatorTree {
    private final IntList buffer;
    private final int size;

    private SNCADominatorTree(int size) {
        this.buffer = new IntArrayList(size);
        this.size = size;
    }

    @Override
    public boolean dominates(int u, int v) {
        // TODO 
        return false;
    }

    @Override
    public int parent(int v) {
        return buffer.getInt(0 * size + v);
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    private int ancestor(int v) {
        return buffer.getInt(1 * size + v);
    }

    private int label(int v) {
        return buffer.getInt(2 * size + v);
    }

    private int semi(int v) {
        return buffer.getInt(3 * size + v);
    }

    ModifiableParentTree ancestors() {
        return ListParentTreeView.view(buffer.subList(1 * size, 2 * size));
    }
    
    private void run(FlowGraph graph, int r) {
        // step 1: dfs

        IntStack stack = new IntArrayList();
        stack.push(root);

        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (label(v) == 0) {
                label(v, 1);
                stack.push(v);
                for (int w : graph.succ(v)) {
                    stack.push(w);
                }
            }
        }

    }

    public static SNCADominatorTree from(FlowGraph graph, int root) {
        var instance = new SNCADominatorTree(graph.size());
        instance.run(graph, root);
        return instance;
    }
}
