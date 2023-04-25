import java.util.Scanner;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class Main {
    public static ArrayList<Node> graph;
    public static void main(String[] args) throws FileNotFoundException {
        setupTree();
        BFS();
    }

    private static void BFS() {
        // Initialize the queue with the root node of the graph
        ArrayList<Node> queue = new ArrayList<>();
        Node startNode = graph.get(0);
        queue.add(startNode);

        // Continue the BFS while the queue is not empty
        while(!queue.isEmpty()){
            Node i = queue.get(0);
            queue.remove(0);

            //Check if this node has been visited
            if(i.isVisited()){
                continue;
            }
            i.setVisited(true);

            System.out.println(i.getKey());

            // Add the neighbors of this node to the queue
            List<Integer> next = i.getNodes();
            for(int x = 0; x < next.size(); x++){
                queue.add(graph.get(next.get(x) - 1));
            }
        }
    }

    private static void setupTree() throws FileNotFoundException {
        graph = new ArrayList<Node>();
        Scanner scan = new Scanner(new File("tree.txt"));
        while(scan.hasNext()){
            String line = scan.nextLine();
            parseLine(line);
        }
    }

    private static void parseLine(String line) {
        String[] keys = line.split(" ");
        int key = Integer.parseInt(keys[0]);
        ArrayList<Integer> points = new ArrayList<>();
        for(int i = 1; i < keys.length;i++){
            points.add(Integer.parseInt(keys[i]));
        }
        graph.add(new Node(key, points));
    }
}