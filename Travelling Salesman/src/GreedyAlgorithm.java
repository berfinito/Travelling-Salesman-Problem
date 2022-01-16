import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GreedyAlgorithm {
    static List<Integer> GreedyAlgorithm(HashMap<Integer, float[]> tsp)
    {
        float tourLength = 0;
        int counter = 0;
        int j = 0, i = 0;
        float min = Float.MAX_VALUE;
        List<Integer> visitedRouteList = new ArrayList<>();
        visitedRouteList.add(0);
        int[] route = new int[tsp.size()];
        while (i < tsp.size() && j < tsp.get(i).length) {
            if (counter >= tsp.get(i).length - 1) {
                break;
            }
            if (j != i && !(visitedRouteList.contains(j))) {
                if (tsp.get(i)[j] < min) {
                    min = tsp.get(i)[j];
                    route[counter] = j + 1;
                }
            }
            j++;
            if (j == tsp.get(i).length) {
                tourLength += min;
                min = Integer.MAX_VALUE;
                visitedRouteList.add(route[counter] - 1);
                j = 0;
                i = route[counter] - 1;
                counter++;
            }
        }
        i = route[counter - 1] - 1;

        for (j = 0; j < tsp.size(); j++) {

            if ((i != j) && tsp.get(i)[j] < min) {
                min = tsp.get(i)[j];
                route[counter] = j + 1;
            }
        }
        tourLength += min;
        System.out.println("#############################################################################");
        System.out.println("The tour's length using Greedy Algorithm is " + tourLength);
        System.out.println("#############################################################################");
        return visitedRouteList;
    }

    public static void main(String[] args){
        City[] sample = Helper.loadFile();
        HashMap<Integer, float[]> segmenttable = new HashMap<>();
        segmenttable = Helper.segmentTable(sample);

        long start = System.currentTimeMillis();
        List<Integer> a = GreedyAlgorithm(segmenttable);
        long end = System.currentTimeMillis();
        System.out.println(a);
        System.out.println("Elapsed Time in seconds: "+ (end-start)/1000);
    }
}
