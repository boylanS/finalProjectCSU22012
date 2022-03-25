import java.util.ArrayList;

public class EdgeWeightedDigraph {

    private int V;
    private int E;
    private ArrayList<DirectedEdge>[] adj;
    private int[] indegree;
    private static final String NEWLINE = System.getProperty("line.separator");
    private ArrayList<busStop>[] vertices;

    public EdgeWeightedDigraph(int V)
    {
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
        vertices = (ArrayList<busStop>[]) new ArrayList[V];

        for (int v = 0; v < V; v ++)
        {
            adj[v] = new ArrayList<DirectedEdge>();
        }

    }


    public int V()
    {
        return V;
    }

    void addEdge(DirectedEdge e)
    {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
        indegree[w]++;
        E++;

    }

    Iterable<DirectedEdge> adj(int v)
    {
        return adj[v];

    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
