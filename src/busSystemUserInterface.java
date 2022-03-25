import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class busSystemUserInterface {

    public static void main(String[] args) {

        ArrayList<busStop> busStopList = readBusStopInput();
        ArrayList<busTransfers> busTransfersList = readBusTransfers();
        ArrayList<busStopTimes> busStopTimesList = readBusStopTimes();
        int numberOfStops = busStopList.size();
        System.out.println(numberOfStops);
        /*EdgeWeightedDigraph graphRep = graphConstructor(busTransfersList,
                busStopTimesList, numberOfStops);*/

        System.out.println("Welcome to the Vancouver bus information hub!" +
                "\n Would you like to: \n" +
                "(1) Find the shortest path between two stops \n" +
                "(2) Search for a bus stop by name \n" +
                "(3) Search for trips by arrival time \n");

        Scanner inputScanner = new Scanner(System.in);
        boolean validInput = false;
        int userSelection = 0;

        while (!validInput) {
            System.out.println("Enter number corresponding to desired action: ");
            if (inputScanner.hasNextInt()) {
                userSelection = inputScanner.nextInt();

                if (userSelection < 1 || userSelection > 3) {
                    System.out.println("Error: input does not correspond to action.");
                    inputScanner.nextLine();
                } else {
                    validInput = true;
                }
            } else {
                System.out.println("Error: input must be an integer.");
                inputScanner.nextLine();
            }
        }

        if (userSelection == 1)
        {
            //ArrayList<busStop> busStopList = readBusStopInput();
            //System.out.println(busStopList.get(0).toString());
        }

    }

    public static ArrayList<busStop> readBusStopInput() {

        File inputFile = new File("stops.txt");

        BufferedReader inputBR;
        ArrayList<busStop> busStopList = new ArrayList<busStop>();

        {
            try {
                inputBR = new BufferedReader(new FileReader(inputFile));

                Scanner fileReader = new Scanner(inputBR);

                int stop_id;
                int stop_code;
                String stop_name;
                String stop_desc;
                Double stop_lat;
                Double stop_lon;
                String stop_url;
                String location_type;
                int parent_station;
                String[] lineElements = new String[9];
                String readInLine;
                fileReader.nextLine();

                while (fileReader.hasNextLine()) {
                    try {
                        readInLine = fileReader.nextLine();
                        lineElements = readInLine.split(",");

                        stop_id = Integer.parseInt(lineElements[0]);
                        if (lineElements[1].equals(" "))
                        {
                            stop_code = -1;
                        }
                        else
                        {
                            stop_code = Integer.parseInt(lineElements[1]);
                        }
                        stop_name = lineElements[2];
                        stop_desc = lineElements[3];
                        stop_lat = Double.parseDouble(lineElements[4]);
                        stop_lon = Double.parseDouble(lineElements[5]);
                        stop_url = lineElements[6];
                        location_type = lineElements[7];
                        parent_station = Integer.parseInt(lineElements[8]);

                        busStop newStop = new busStop(stop_id, stop_code, stop_name, stop_desc,
                                stop_lat, stop_lon, stop_url, location_type, parent_station);

                        busStopList.add(newStop);

                    } catch (NoSuchElementException ex) {
                        ex.printStackTrace();
                    }

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return busStopList;
    }

    public static ArrayList<busTransfers> readBusTransfers()
    {
        File inputFile = new File("transfers.txt");

        BufferedReader inputBR;
        ArrayList<busTransfers> busTransfersList = new ArrayList<busTransfers>();

        {
            try {
                inputBR = new BufferedReader(new FileReader(inputFile));

                Scanner fileReader = new Scanner(inputBR);

                int from_stop_id;
                int to_stop_id;
                int transfer_type;
                int min_transfer_time;
                String[] lineElements = new String[4];
                String readInLine;
                fileReader.nextLine();

                while (fileReader.hasNextLine()) {
                    try {
                        readInLine = fileReader.nextLine();
                        lineElements = readInLine.split(",");

                        from_stop_id = Integer.parseInt(lineElements[0]);
                        to_stop_id = Integer.parseInt(lineElements[1]);
                        transfer_type = Integer.parseInt(lineElements[2]);
                        if (lineElements.length > 3)
                        {
                            min_transfer_time = Integer.parseInt(lineElements[3]);
                        }
                        else
                        {
                            min_transfer_time = 0;
                        }

                        busTransfers newTransfer = new busTransfers(from_stop_id,
                                to_stop_id, transfer_type, min_transfer_time);

                        busTransfersList.add(newTransfer);

                    } catch (NoSuchElementException ex) {
                        ex.printStackTrace();
                    }

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return busTransfersList;

    }

    public static ArrayList<busStopTimes> readBusStopTimes()
    {
        File inputFile = new File("stop_times.txt");

        BufferedReader inputBR;
        ArrayList<busStopTimes> busStopTimes = new ArrayList<busStopTimes>();

        {
            try {
                inputBR = new BufferedReader(new FileReader(inputFile));

                Scanner fileReader = new Scanner(inputBR);

                int trip_id;
                String arrival_time;
                String departure_time;
                int stop_id;
                int stop_sequence;
                String stop_headsign;
                int pickup_type;
                int drop_off_type;
                double shape_dist_traveled;
                String[] lineElements = new String[9];
                String readInLine;
                fileReader.nextLine();

                while (fileReader.hasNextLine()) {
                    try {
                        readInLine = fileReader.nextLine();
                        lineElements = readInLine.split(",");

                        trip_id = Integer.parseInt(lineElements[0]);
                        arrival_time = lineElements[1];
                        departure_time = lineElements[2];
                        stop_id = Integer.parseInt(lineElements[3]);
                        stop_sequence = Integer.parseInt(lineElements[4]);
                        stop_headsign = lineElements[5];
                        pickup_type = Integer.parseInt(lineElements[6]);
                        drop_off_type = Integer.parseInt(lineElements[7]);

                        if (lineElements.length == 9)
                        {
                            shape_dist_traveled = Double.parseDouble(lineElements[8]);
                        }
                        else
                        {
                            shape_dist_traveled = 0;
                        }



                        busStopTimes newStopTime = new busStopTimes(trip_id, arrival_time,
                                departure_time, stop_id,
                                stop_sequence, stop_headsign,
                                pickup_type, drop_off_type,
                                shape_dist_traveled);

                        busStopTimes.add(newStopTime);

                    } catch (NoSuchElementException ex) {
                        ex.printStackTrace();
                    }

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return busStopTimes;

    }

    public static EdgeWeightedDigraph graphConstructor(ArrayList<busTransfers> busTransfers, ArrayList<busStopTimes> stopTimes,
                                                       int numberOfStops)
    {
        busTransfers tempTransfer;
        DirectedEdge tempEdge;
        int weight = 0;
        EdgeWeightedDigraph graphRep = new EdgeWeightedDigraph(numberOfStops);

        for (int count = 0; count < busTransfers.size(); count++)
        {
            tempTransfer = busTransfers.get(count);
            if (tempTransfer.getTransfer_type() == 0)
            {
                weight = 2;
            }
            else if (tempTransfer.getTransfer_type() == 2)
            {
                weight = (tempTransfer.getMin_transfer_time())/100;
            }

            tempEdge = new DirectedEdge(tempTransfer.getFrom_stop_id(),
                    tempTransfer.getTo_stop_id(),
                    weight);

            graphRep.addEdge(tempEdge);

        }

        for (int count2 = 0; count2 < stopTimes.size()-1; count2 ++ )
        {
            if ((stopTimes.get(count2)).getTrip_id() ==
                    (stopTimes.get(count2+1).getTrip_id()))
            {
                weight = 1;
                tempEdge = new DirectedEdge(stopTimes.get(count2).getStop_id(),
                stopTimes.get(count2+1).getStop_id(),weight);

                graphRep.addEdge(tempEdge);

            }

        }

        return graphRep;
    }

}

