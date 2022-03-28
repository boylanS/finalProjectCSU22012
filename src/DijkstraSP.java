public class DijkstraSP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int source)
    {
       distTo = new double[G.getNumberOfStops()];
       edgeTo = new DirectedEdge[G.getNumberOfStops()];

        for (int v = 0; v < G.getNumberOfStops(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[source] = 0.0;

        pq = new IndexMinPQ<Double>(G.getNumberOfStops());
        pq.insert(source, distTo[source]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }

    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }

    public double distTo(int v) {
        //validateVertex(v);
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        //validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }









}
