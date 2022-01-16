import java.util.Arrays;

public class NearestNeighbor {
    public static float nearestNeighborTSP(City[] cities, City starting) {
        float[] currentState = Helper.nearestNeighbor(cities, starting);
        float trip = 0;
        while (!Arrays.equals(currentState, new float[]{0, 1000000000})) {
            trip = trip + currentState[1];
            currentState = Helper.nearestNeighbor(cities, cities[(int) currentState[0]]);
        }
        return trip;
    }

    public static void main(String[] args) {
        City[] cities = Helper.loadFile();
        System.out.println("Printing the cities!!" +
                " \n########################################################################");
        for(int i = 0; i < cities.length; i++){
            System.out.println(i + 1 + "-- " + cities[i].toString2());
        }

        // Using the Nearest Neighbor Algorithm starting from a random index
        int startingIndex = 2421;
        long start = System.currentTimeMillis();
        float result = nearestNeighborTSP(cities, cities[startingIndex]);
        long end = System.currentTimeMillis();
        System.out.println("########################################################################");
        System.out.println("The tour's length using Nearest Neighbor Algorithm is " + result);
        System.out.println("########################################################################\n");
        System.out.println("Elapsed Time in milli seconds: "+ (end-start));


        /*To verify that all the cities are visited, uncomment the following for loop to print the cities
          and search in the console text for the word "true". It will appear as 4663 which is the total
          number of the cities given.
         */

//        for(int i = 0; i < cities.length; i++){
//            System.out.println(i + 1 + "-- " + cities[i].toString2());
//        }
    }
}
