public class busStop {


    private int stop_id;
    private int stop_code;
    private String stop_name;
    private String stop_desc;
    private Double stop_lat;
    private Double stop_lon;
    private String stop_url;
    private String location_type;
    private int parent_station;

    public busStop(int stop_id, int stop_code,
                   String stop_name, String stop_desc,
                   Double stop_lat, Double stop_lon,
                   String stop_url, String location_type,
                   int parent_station
                   )

    {
        this.stop_id = stop_id;
        this.stop_code = stop_code;
        this.stop_name = stop_name;
        this.stop_desc = stop_desc;
        this.stop_lat = stop_lat;
        this.stop_lon = stop_lon;
        this.stop_url = stop_url;
        this.location_type = location_type;
        this.parent_station = parent_station;

    }

    public int getStop_code()
    {
        return stop_code;
    }

    public void setStop_code(int code)
    {
        this.stop_code = code;
    }

    public int getStop_id()
    {
        return stop_id;
    }

    public void setStop_id(int id)
    {
        this.stop_id = id;
    }

    public String getStop_name()
    {
        return stop_name;
    }

    public void setStop_name(String name)
    {
        this.stop_name = name;
    }

    public String getStop_desc()
    {
        return stop_desc;
    }

    public void setStop_desc(String desc)
    {
        this.stop_desc = desc;
    }

    public double getStop_lat()
    {
        return stop_lat;
    }

    public void setStop_lat(Double lat)
    {
        this.stop_lat = lat;
    }

    public double getStop_lon()
    {
        return stop_lon;
    }

    public void setStop_lon(Double lon)
    {
        this.stop_lon = lon;
    }

    public String getStop_url()
    {
        return stop_url;
    }

    public void setStop_url(String url)
    {
        this.stop_url = url;
    }

    public String getStop_locationType()
    {
        return location_type;
    }

    public void setStop_locationType(String location_type)
    {
        this.location_type = location_type;
    }

    public int getParent_station()
    {
        return parent_station;
    }

    public void setParent_Station(int station)
    {
        this.parent_station = station;
    }

    public String toString()
    {
        String stringForm = "Stop ID: "+stop_id+"\n"+
                "Stop Code: "+stop_code+"\n"+"Stop Name: "+stop_name+"\n"+
                "Stop Desc: "+stop_desc+"\n"+"Stop Lat: "+stop_lat+"\n"+
                "Stop Lon: "+stop_lon+"\n"+"Stop URL: "+stop_url+"\n"+
                "Location Type: "+location_type+"\n"+"Parent Station: "+parent_station+"\n";

        return stringForm;
    }
}
