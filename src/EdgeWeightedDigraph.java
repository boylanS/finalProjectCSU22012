import java.util.ArrayList;

public class EdgeWeightedDigraph {

    private int numberOfStops;
    private int numberOfTrips;
    private ArrayList<DirectedEdge>[] adj;
    private static final String NEWLINE = System.getProperty("line.separator");
    private ArrayList<busStop>[] vertices;
    private int[] stopIDs;

    public EdgeWeightedDigraph(int V, ArrayList<busStop> busStops)
    {
        this.numberOfStops = V;
        this.numberOfTrips = 0;
        adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
        vertices = (ArrayList<busStop>[]) new ArrayList[V];
        stopIDs =  new int[V];

        for (int v = 0; v < V; v ++)
        {
            adj[v] = new ArrayList<DirectedEdge>();
            stopIDs[v] = (busStops.get(v)).getStop_id();
        }

        stopIDs = BinarySearch.quickSort(stopIDs);

    }


    public int getNumberOfStops()
    {
        return numberOfStops;
    }

    public int getNumberOfTrips() {return numberOfTrips; }

    public int[] stopIDs(){
        return stopIDs;
    }

    void addEdge(DirectedEdge e)
    {
        int v = e.from();
        int w = e.to();
        int index = BinarySearch.indexOf(stopIDs,v);

        if (index == -1)
        {
            System.out.println(v);
        }
        else
        {
            adj[index].add(e);
            numberOfTrips++;
        }


    }

    Iterable<DirectedEdge> adj(int v)
    {
        return adj[v];

    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(numberOfStops + " " + numberOfTrips + NEWLINE);
        for (int v = 0; v < numberOfStops; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e.toString() + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


}
