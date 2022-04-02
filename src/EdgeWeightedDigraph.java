import java.util.ArrayList;
import java.util.Arrays;

public class EdgeWeightedDigraph {

    private int numberOfStops;
    private int numberOfTrips;
    private ArrayList<DirectedEdge>[] adj;
    //private int[] indegree;
    private static final String NEWLINE = System.getProperty("line.separator");
    private ArrayList<busStop>[] vertices;
    private int[] stopIDs;

    public EdgeWeightedDigraph(int V, ArrayList<busStop> busStops)
    {
        this.numberOfStops = V;
        this.numberOfTrips = 0;
        //this.indegree = new int[V];
        adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
        vertices = (ArrayList<busStop>[]) new ArrayList[V];
        stopIDs =  new int[V];

        for (int v = 0; v < V; v ++)
        {
            adj[v] = new ArrayList<DirectedEdge>();
            stopIDs[v] = (busStops.get(v)).getStop_id();
            //System.out.println(stopIDs[v]);
        }

        //System.out.println("First element: "+stopIDs[0]);
        stopIDs = BinarySearch.quickSort(stopIDs);
       // System.out.println("First element: "+stopIDs[0]);
        // System.out.println(BinarySearch.indexOf(stopIDs,1888));




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
            //indegree[w]++;
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
