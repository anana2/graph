package fxf.anana2.igraph;

public interface OrderderedGraph extends Graph {
    int first();
    
    boolean hasnext(int v);
    
    int next(int v);
}
