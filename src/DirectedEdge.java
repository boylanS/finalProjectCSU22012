public class DirectedEdge {
    private int source_id;
    private int destination_id;
    private double weight;

    DirectedEdge(int v, int w, double weight)
    {
        this.source_id = v;
        this.destination_id = w;
        this.weight = weight;
    }

    double weight()
    {
        return weight;
    }

    int from()
    {
        return source_id;

    }

    int to()
    {
        return destination_id;
    }

    public String toString() {
        return source_id + "->" + destination_id + " " + String.format("%5.2f", weight);
    }

}

