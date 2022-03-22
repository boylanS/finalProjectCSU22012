import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class busSystemUserInterface {

    public static void main(String[] args) {

        ArrayList<busStop> busStopList = readBusStopInput();


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

}

