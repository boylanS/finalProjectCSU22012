public class busStopTimes {

    private int trip_id;
    private String arrival_time;
    private String departure_time;
    private int stop_id;
    private int stop_sequence;
    private String stop_headsign;
    private int pickup_type;
    private int drop_off_type;
    private double shape_dist_traveled;

    public busStopTimes(int trip_id, String arrival_time,
                   String departure_time, int stop_id,
                   int stop_sequence, String stop_headsign,
                   int pickup_type, int drop_off_type)

    {
        this.trip_id = trip_id;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.stop_id = stop_id;
        this.stop_sequence = stop_sequence;
        this.stop_headsign = stop_headsign;
        this.pickup_type = pickup_type;
        this.drop_off_type = drop_off_type;

    }

    public busStopTimes(int trip_id, String arrival_time,
                        String departure_time, int stop_id,
                        int stop_sequence, String stop_headsign,
                        int pickup_type, int drop_off_type,
                        double shape_dist_traveled)

    {
        this.trip_id = trip_id;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.stop_id = stop_id;
        this.stop_sequence = stop_sequence;
        this.stop_headsign = stop_headsign;
        this.pickup_type = pickup_type;
        this.drop_off_type = drop_off_type;
        this.shape_dist_traveled = shape_dist_traveled;


    }

    public int getTrip_id()
    {
        return trip_id;
    }

    public void setTrip_id(int id)
    {
        this.trip_id = id;
    }

    public int getStop_id()
    {
        return stop_id;
    }

    public void setStop_id(int id)
    {
        this.stop_id = id;
    }

    public String getArrival_time()
    {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time)
    {
        this.arrival_time = arrival_time;
    }

    public String getDeparture_time()
    {
        return departure_time;
    }

    public void setDeparture_time(String departure_time)
    {
        this.departure_time = departure_time;
    }

    public int getStop_sequence()
    {
        return stop_sequence;
    }

    public void setStop_sequence(int seq)
    {
        this.stop_sequence = seq;
    }

    public String getStop_headsign()
    {
        return stop_headsign;
    }

    public void setStop_headsign(String headsign)
    {
        this.stop_headsign = headsign;
    }

    public int getPickup_type()
    {
        return pickup_type;
    }

    public void setPickup_type(int pickup_type)
    {
        this.pickup_type = pickup_type;
    }

    public int getDrop_off_type()
    {
        return drop_off_type;
    }

    public void setDrop_off_type(int drop_off_type)
    {
        this.drop_off_type = drop_off_type;
    }

    public double getShape_dist_traveled()
    {
        return shape_dist_traveled;
    }

    public void setShape_dist_traveled(double shape_dist_traveled)
    {
        this.shape_dist_traveled = shape_dist_traveled;
    }

    public String toString()
    {
        String stringForm = "Trip ID: "+trip_id+"\n"+
                "Arrival Time: "+arrival_time+"\n"+"Departure Time:"+departure_time+"\n"+
                "Stop ID: "+stop_id+"\n"+"Stop Sequence: "+stop_sequence+"\n"+
                "Stop Headsign: "+stop_headsign+"\n"+"Pickup Type: "+pickup_type+"\n"+
                "Drop off Type: "+drop_off_type+"\n"+"Shpe Dist Traveled: "+shape_dist_traveled+"\n";

        return stringForm;
    }
}
