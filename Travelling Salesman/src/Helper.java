import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Helper {
    public static City[] loadFile(){
        int index = 0;
        City[] cities = new City[4663];
        BufferedReader reader;
        String file = "";
        try {
            reader = new BufferedReader(new FileReader(
                    "ca4663.tsp"));
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                file = file + line + "\n";
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file = file.substring(186, file.length() - 5);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] city = line.split(" ");
            City newCity = new City();
            newCity.x = Float.parseFloat(city[1]);
            newCity.y = Float.parseFloat(city[2]);
            cities[index] = newCity;
            index++;
        }
        scanner.close();
        return cities;
    }

    public static float distance(City first, City second){
        return (float) Math.sqrt((second.y - first.y) * (second.y - first.y) + (second.x - first.x) * (second.x - first.x));
    }

    public static float[] nearestNeighbor(City[] cities, City current){
        current.isVisited = true;
        float[] result = new float[2];
        result[1] = 1000000000;
        for(int i = 0; i < cities.length; i++){
            if(!cities[i].isVisited && distance(current, cities[i]) < result[1] && distance(current, cities[i]) != 0){
                result[0] = i;
                result[1] = distance(current, cities[i]);
                continue;
            }
            else
                continue;
        }
        return result;
    }

    public static HashMap<Integer, float[]> segmentTable(City[] cities){

        HashMap<Integer, float[]> result = new HashMap<>();

        for(int i = 0; i < cities.length; i++){
            float[] distances = new float[4663];
            for (int j = 0; j < cities.length; j++){
                float distance = distance(cities[i],cities[j]);
                if(distance <= 0){
                    distance = 1000000000;
                }
                distances[j] = distance;
            }
            result.put(i, distances);
        }
        return result;
    }

    public static List<Integer> ExhaustiveTSP(List<City> c) {
        List<Integer> list = new ArrayList<>();
        if (c.size() == 2) {
            list.add(c.get(0).index);
            list.add(c.get(1).index);
            return list;
        }
        else {
            if (distance(c.get(0), c.get(1)) > distance(c.get(0), c.get(2))) {
                list.add(c.get(0).index);
                list.add(c.get(2).index);
                list.add(c.get(1).index);
            }
            else {
                list.add(c.get(0).index);
                list.add(c.get(1).index);
                list.add(c.get(2).index);
            }
        }

        return list;
    }

    public static ArrayList<City> loadFile2(){
        int index = 0;
        ArrayList<City> cities = new ArrayList<>();
        BufferedReader reader;
        String file = "";
        try {
            reader = new BufferedReader(new FileReader(
                    "ca4663.tsp"));
            String line = reader.readLine();
            while (line != null) {
                file = file + line + "\n";
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file = file.substring(186, file.length() - 5);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] city = line.split(" ");
            City newCity = new City();
            newCity.x = Float.parseFloat(city[1]);
            newCity.y = Float.parseFloat(city[2]);
            newCity.index = index;
            cities.add(newCity);
            index++;
        }
        scanner.close();
        return cities;
    }
}
