public class busTransfers {
    private int from_stop_id;
    private int to_stop_id;
    private int transfer_type;
    private int min_transfer_time;


    public busTransfers(int from_stop_id, int to_stop_id,
                   int transfer_type, int min_transfer_time)

    {
        this.from_stop_id = from_stop_id;
        this.to_stop_id = to_stop_id;
        this.transfer_type = transfer_type;
        this.min_transfer_time = min_transfer_time;

    }

    public int getFrom_stop_id()
    {
        return from_stop_id;
    }

    public void setFrom_stop_id(int from_stop_id)
    {
        this.from_stop_id = from_stop_id;
    }

    public int getTo_stop_id()
    {
        return to_stop_id;
    }

    public void setTo_stop_id(int to_stop_id)
    {
        this.to_stop_id = to_stop_id;
    }
    public int getTransfer_type()
    {
        return transfer_type;
    }

    public void setTransfer_type(int type)
    {
        this.transfer_type = type;
    }

    public int getMin_transfer_time()
    {
        return min_transfer_time;
    }

    public void setMin_transfer_time(int time)
    {
        this.min_transfer_time = time;
    }


}
