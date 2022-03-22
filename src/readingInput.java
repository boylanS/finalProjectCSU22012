import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class readingInput {

    File inputFile = new File("stops.txt");


    BufferedReader inputBR = null;


    try {
        inputBR = new BufferedReader(new FileReader(inputFile));
    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    }
    }
    /*try {
        inputBR = new BufferedReader(new FileReader(inputFile));
        int numberOfNodes = Integer.parseInt(inputBR.readLine());
        int numberOfStreets = Integer.parseInt(inputBR.readLine());
        this.vertices = numberOfNodes;
        this.edges = numberOfStreets;

        this.ewd = new EdgeWeightedDigraph(numberOfNodes);
        Scanner fileReader = new Scanner(inputBR);
        int startNode;
        int endNode;
        double weight;



        while (fileReader.hasNextLine())
        {
            try{
                startNode = fileReader.nextInt();
                endNode = fileReader.nextInt();
                weight = fileReader.nextDouble();

                DirectedEdge de = new DirectedEdge(startNode, endNode, weight);
                ewd.addEdge(de);
            }catch(NoSuchElementException ex)
            {
                ex.printStackTrace();
            }


        }

        return ewd;

    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }*/
}
