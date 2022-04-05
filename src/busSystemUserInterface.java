import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.time.*;
import java.time.format.*;


public class busSystemUserInterface {

    public static void main(String[] args) {

        System.out.println("Welcome to the Vancouver Bus Information Hub!");
        System.out.println("Loading in bus data..");
        ArrayList<busStop> busStopList = readBusStopInput();
        ArrayList<busTransfers> busTransfersList = readBusTransfers();
        ArrayList<busStopTimes> busStopTimesList = readBusStopTimes();
        System.out.println("Almost there..");
        boolean userQuit = false;

        ArrayList<Integer> stopIDListToSort = new ArrayList<Integer>();
        int numberOfStops = busStopList.size();

        for (int stopCount = 0; stopCount < numberOfStops; stopCount++)
        {
            stopIDListToSort.add(busStopList.get(stopCount).getStop_id());
        }

        Object[] stopIDListSortedObj = stopIDListToSort.toArray();
        int stopIDListSorted[] = new int[numberOfStops];

        for (int i = 0; i < stopIDListSortedObj.length; i++)
        {
            stopIDListSorted[i] = (int) stopIDListSortedObj[i];
        }

        stopIDListSorted = BinarySearch.quickSort(stopIDListSorted);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Ready to go!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!userQuit)
        {
            System.out.println("\nWould you like to: \n" +
                    "(1) Find the shortest path between two stops \n" +
                    "(2) Search for a bus stop by name \n" +
                    "(3) Search for trips by arrival time \n"
            );

            Scanner inputScanner = new Scanner(System.in);
            boolean validInput = false;
            int userSelection = 0;

            while (!validInput) {
                System.out.println("Enter number corresponding to desired action (or enter QUIT): ");
                if (inputScanner.hasNextInt()) {
                    userSelection = inputScanner.nextInt();

                    if (userSelection < 1 || userSelection > 3) {
                        System.out.println("Error: input does not correspond to action.");
                    } else {
                        validInput = true;
                    }
                    inputScanner.nextLine();
                } else {
                    String tempString = inputScanner.nextLine();
                    if (tempString.equalsIgnoreCase("QUIT"))
                    {
                        userQuit = true;
                        validInput = true;
                        System.out.println("Thank you! Have a nice day :)");
                        break;
                    }
                    else
                    {
                        System.out.println("Error: input must be an integer.");
                        //inputScanner.nextLine();
                    }
                }
            }

            if (userSelection == 1)
            {
                shortestPath(busTransfersList, busStopTimesList, busStopList,
                        stopIDListSorted);

            }
            else if (userSelection == 2)
            {

                searchByStop(busStopList);

            }
            else if (userSelection == 3)
            {
                tripsByTime(busStopTimesList);
            }

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
                        arrival_time = validTimeInput(arrival_time);
                        departure_time = lineElements[2];
                        departure_time = validTimeInput(departure_time);
                        if (!(arrival_time == "error") || !(departure_time == "error"))
                        {
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

                        }


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
                                                       ArrayList<busStop> busStopList,
                                                       int numberOfStops)
    {
        busTransfers tempTransfer;
        DirectedEdge tempEdge;
        int weight = 0;
        EdgeWeightedDigraph graphRep = new EdgeWeightedDigraph(numberOfStops, busStopList);

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

    public static String validTimeInput(String timeInput)
    {

        if (timeInput!= null)
        {
            char firstCharacter = timeInput.charAt(0);
            if (firstCharacter == ' ')
            {
                timeInput = timeInput.replaceFirst(" ", "0");

            }

            try{
                LocalTime.parse(timeInput);
            }catch (DateTimeParseException | NullPointerException e){
                return "error";
            }

        }
        return timeInput;
    }

    public static ArrayList<busStopTimes> stopTimesGivenArrival(String arrivalTime,
                                                                ArrayList<busStopTimes> totalBusStops)
    {
        busStopTimes tempStop;
        ArrayList<busStopTimes> timesByArrival = new ArrayList<busStopTimes>();
        String tempStopArr;

        for (int count = 0; count < totalBusStops.size(); count ++)
        {
            tempStop = totalBusStops.get(count);
            tempStopArr = tempStop.getArrival_time();

            if (tempStopArr.equals(arrivalTime))
            {
                timesByArrival.add(tempStop);
                timesByArrival = insertionSortStopTimes(timesByArrival);
            }

        }

        return timesByArrival;

    }

    public static ArrayList<busStopTimes> insertionSortStopTimes (ArrayList<busStopTimes> a){

        busStopTimes[] tempArray = new busStopTimes[a.size()];
        int tempID1;
        int tempID2;
        busStopTimes tempStop;

        for (int i = 1; i < a.size(); i ++)
        {
            for (int j = i; j > 0; j --)
            {
                tempID1 = a.get(j).getTrip_id();
                tempID2 = a.get(j-1).getTrip_id();

                if (tempID1 < tempID2)
                {
                    tempArray[i] = a.get(j);
                    a.set(j,a.get(j-1));
                    a.set(j-1,tempArray[i]);
                }
            }
        }

        return a;
    }

    public static String[] moveToEnd(String[] stringArray)
    {
        String tempString = stringArray[0];

        for (int count = 1; count < stringArray.length; count++)
        {
            stringArray[count-1] = stringArray[count];
        }

        stringArray[stringArray.length-1] = tempString;

        return stringArray;
    }

    public static void shortestPath(ArrayList<busTransfers> busTransfersList,
                                    ArrayList<busStopTimes>busStopTimesList,
                                    ArrayList<busStop>busStopList,
                                    int[] stopIDListSorted)
    {
        Scanner inputScanner = new Scanner(System.in);

        EdgeWeightedDigraph graphRep = graphConstructor(busTransfersList,busStopTimesList,
                busStopList, busStopList.size());
        int[] stopIDs = graphRep.stopIDs();
        boolean validDesiredSource = false;
        boolean validDesiredDestination = false;
        int tempSource;
        int sourceID = 0;
        int destinationID = 0;
        boolean userQuit = false;

        if (!userQuit) {

            while (!validDesiredSource) {
                System.out.println("Enter desired source bus stop ID (or enter QUIT): ");
                if (inputScanner.hasNextInt()) {
                    tempSource = inputScanner.nextInt();
                    int index = BinarySearch.indexOf(stopIDListSorted, tempSource);

                    if (index == -1) {
                        System.out.println("Error: ID is invalid.");
                        //inputScanner.nextLine();

                    } else {
                        sourceID = tempSource;
                        validDesiredSource = true;
                    }
                    inputScanner.nextLine();
                } else {
                    String tempString = inputScanner.nextLine();
                    if (tempString.equalsIgnoreCase("QUIT")) {
                        userQuit = true;
                        validDesiredSource = true;
                        break;
                    } else {

                        System.out.println("Error: stop ID must be an integer.");
                        inputScanner.nextLine();
                    }
                }

            }

                if (!userQuit) {
                    while (!validDesiredDestination) {
                        System.out.println("Enter desired destination bus stop ID (or enter QUIT): ");
                        if (inputScanner.hasNextInt()) {
                            tempSource = inputScanner.nextInt();
                            int index = BinarySearch.indexOf(stopIDListSorted, tempSource);

                            if (index == -1) {
                                System.out.println("Error: ID is invalid.");
                            } else {
                                destinationID = tempSource;
                                validDesiredDestination = true;
                            }
                            inputScanner.nextLine();
                        } else {
                            String tempString = inputScanner.nextLine();
                            if (tempString.equalsIgnoreCase("QUIT")) {
                                userQuit = true;
                                validDesiredSource = true;
                                break;
                                //System.out.println("Thank you! Have a nice day :)");
                            }
                            else
                            {
                                System.out.println("Error: stop ID must be an integer.");
                            }   inputScanner.nextLine();


                        }
                    }
                }

        }

        if (sourceID != 0 && destinationID != 0)
        {
            int sourceIDIndex = BinarySearch.indexOf(stopIDs,sourceID);

            DijkstraSP shortestPath = new DijkstraSP(graphRep, sourceIDIndex,
                    stopIDs);

            int destinationIDIndex = BinarySearch.indexOf(stopIDs,destinationID);

            if (shortestPath.hasPathTo(destinationIDIndex) == false)
            {
                System.out.println("No path found :(");
            }
            else
            {
                Iterable<DirectedEdge> SPuserInput = shortestPath.pathTo(destinationIDIndex);
                double totalWeight = 0;
                System.out.println("Route: ");
                for(DirectedEdge path : SPuserInput)
                {
                    System.out.print(path.to()+" <- ("+path.weight()+") - ");
                    totalWeight = totalWeight + path.weight();
                }
                System.out.print(sourceID);
                System.out.println("\nTotal cost: "+totalWeight);
            }
        }else
        {
            System.out.println("Returning to main menu :)");
        }

    }

    public static void searchByStop(ArrayList<busStop> busStopList)
    {
        TST<Integer> busStopTrie = new TST<Integer>();
        ArrayList<Integer> stopIDList = new ArrayList<Integer>();
        ArrayList<String> busStopsAmended = new ArrayList<String>();
        for (int busStopLoop = 0; busStopLoop < busStopList.size(); busStopLoop++)
        {
            String busStop = busStopList.get(busStopLoop).getStop_name();
            int busStopID = busStopList.get(busStopLoop).getStop_id();
            String[] busStopName = busStop.split(" ");
            stopIDList.add(busStopID);
            String stringToMove;


            switch(busStopName[0]){
                case "FLAGSTOP":

                    busStopName = moveToEnd(busStopName);

                case "WB":
                    busStopName = moveToEnd(busStopName);
                    break;

                case "NB":
                    busStopName = moveToEnd(busStopName);
                    break;

                case "SB":
                    busStopName = moveToEnd(busStopName);
                    break;

                case "EB":
                    busStopName = moveToEnd(busStopName);
                    break;
                default:
            }

            String busStopClear = String.join(" ",busStopName);

            busStopTrie.put(busStopClear,busStopLoop);
            busStopsAmended.add(busStopClear);

        }

        System.out.println("Search for bus stop by name (enter in UPPERCASE only, or enter QUIT to exit): ");
        Scanner busStopScanner = new Scanner(System.in);
        String searchStop = busStopScanner.nextLine();

        if (searchStop.equalsIgnoreCase("QUIT"))
        {
            System.out.println("Returning to main menu :)");
        }
        else
        {
            Iterable<String> searchKeys = busStopTrie.keysWithPrefix(searchStop);

            ArrayList<busStop> searchReturns = new ArrayList<busStop>();
            ArrayList<Integer> searchReturnIDs = new ArrayList<Integer>();
            ArrayList<Integer> searchReturnIndex = new ArrayList<Integer>();

            for (String s: searchKeys)
            {
                //System.out.println(s);
                int searchIndex = busStopsAmended.indexOf(s);
                searchReturnIndex.add(searchIndex);
            }

            if (searchReturnIndex.size() == 0)
            {
                System.out.println("No bus stops found :(");
            }

            for (int idCount = 0; idCount < searchReturnIndex.size(); idCount++)
            {
                int tempIndex = searchReturnIndex.get(idCount);
                int tempStopID = stopIDList.get(tempIndex);
                searchReturnIDs.add(tempStopID);
            }

            for (int stopTotal = 0; stopTotal < busStopList.size(); stopTotal++)
            {
                int tempStopID = busStopList.get(stopTotal).getStop_id();

                if (searchReturnIDs.contains(tempStopID))
                {
                    searchReturns.add(busStopList.get(stopTotal));

                }
            }

            for (int returnedStops = 0; returnedStops < searchReturns.size(); returnedStops ++)
            {
                System.out.println(searchReturns.get(returnedStops).toString());
            }
        }


    }

    public static void tripsByTime(ArrayList<busStopTimes> busStopTimesList)
    {
        boolean validTime = false;
        Scanner inputScanner = new Scanner(System.in);
        String userTime = "";

        while (!validTime)
        {
            System.out.println("Enter arrival time in form HH:MM:SS (or QUIT to exit): ");
            userTime = inputScanner.nextLine();

            if (userTime.equalsIgnoreCase("QUIT"))
            {
                validTime = true;

            }
            else
            {
                try{
                    LocalTime.parse(userTime);
                    validTime = true;
                }catch (DateTimeParseException | NullPointerException e){
                    System.out.println("Invalid time string- must be in form HH:MM:SS");
                }

            }

        }

        if (userTime.equalsIgnoreCase("QUIT"))
        {
            System.out.println("Returning to main menu :)");

        }
        else
        {
            ArrayList<busStopTimes> arrivalBusStopTimes = stopTimesGivenArrival(userTime,
                    busStopTimesList);
            if (arrivalBusStopTimes.size() == 0)
            {
                System.out.println("No bus stop times found with specified arrival time :(");
            }
            else
            {
                for (int countStops = 0; countStops < arrivalBusStopTimes.size(); countStops++)
                {
                    System.out.println(arrivalBusStopTimes.get(countStops).toString());
                }
            }
        }

    }

}

