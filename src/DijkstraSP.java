import java.util.Stack;

public class DijkstraSP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> pq;
    private int[] busStopIndex;

    public DijkstraSP(EdgeWeightedDigraph G, int source,
                      int[] busStopIndex)
    {
       distTo = new double[G.getNumberOfStops()];
       edgeTo = new DirectedEdge[G.getNumberOfStops()];
       this.busStopIndex = busStopIndex;

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
        int indexFrom = BinarySearch.indexOf(this.busStopIndex,v);
        int indexTo = BinarySearch.indexOf(this.busStopIndex,w);
        if (distTo[indexTo] > distTo[indexFrom] + e.weight()) {
            distTo[indexTo] = distTo[indexFrom] + e.weight();
            edgeTo[indexTo] = e;
            if (pq.contains(indexTo)) pq.decreaseKey(indexTo, distTo[indexTo]);
            else                pq.insert(indexTo, distTo[indexTo]);
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

    public Iterable<DirectedEdge> pathTo(int v) {
        //validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[BinarySearch.indexOf(this.busStopIndex,e.from())]) {
            path.push(e);
        }
        return path;
    }










}
