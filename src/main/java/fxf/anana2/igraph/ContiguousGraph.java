package fxf.anana2.igraph;

public interface ContiguousGraph extends Graph {
    @Override
    default boolean has(int v) {
        return 0 <= v && v < size();
    }
}
