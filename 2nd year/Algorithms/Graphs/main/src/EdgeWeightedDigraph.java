import java.util.HashSet;

class EdgeWeightedDigraph {
    public int V;
    public int E;
    public HashSet<DirectedEdge>[] adj;
    public int[] indegree;

    EdgeWeightedDigraph(int V, int E) {
        this.V = V;
        this.E = E;
        this.indegree = new int[V];

        adj = (HashSet<DirectedEdge>[]) new HashSet[V];
        for (int v = 0; v < V; v++)
            adj[v] = new HashSet<DirectedEdge>();
    }

    void addIntersection(int a, int b, double weight){
        try {
            validateVertex(a);
            validateVertex(b);

            DirectedEdge e = new DirectedEdge(a, b, weight);
            adj[a].add(e);
            indegree[b]++;
        }
        catch(IllegalArgumentException ignore) {}
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
