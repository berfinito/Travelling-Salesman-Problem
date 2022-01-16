import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DivideAndConquer {
    private static float distance = 0;
    public static List<Integer> DivideAndConquer(List<City> cities) {

        List<City> half1;
        List<City> half2;
        float min;
        char cases;
        Collections.sort(cities,new Comparator<City>() {

            public int compare(City c1, City c2) {
                return Float.compare(c1.y, c2.y);
            }
        });
        
        List<Integer> list = new ArrayList<>();

        if (cities.size() <= 3) {
            return Helper.ExhaustiveTSP(cities);
        }
        else {
            int half_size = cities.size() / 2;
            half1 = new ArrayList<City>(cities.subList(0, half_size));
            half2 = new ArrayList<City>(cities.subList(half_size, cities.size()));
            min = Helper.distance(half1.get(0), half2.get(0));
            cases = 'a';
            if (Helper.distance(half1.get(0), half2.get(half2.size()-1)) < min) {
                min = Helper.distance(half1.get(0), half2.get(half2.size()-1));
                distance = distance + min;
                cases = 'b';
            }
            if (Helper.distance(half2.get(0), half1.get(half1.size()-1)) < min) {
                min = Helper.distance(half1.get(half1.size()-1), half2.get(0));
                distance = distance + min;
                cases = 'd';
            }
            if (Helper.distance(half1.get(half1.size()-1), half2.get(half2.size()-1)) < min) {
                min = Helper.distance(half1.get(half1.size()-1), half2.get(half2.size()-1));
                distance = distance + min;
                cases = 'c';
            }
            switch (cases)
            {
                case 'a':
                    for (int i = half1.size() - 1; i >= 0; i--) {
                        list.add(half1.get(i).index);
                    }
                    for (int i = 0; i < half2.size(); i++)
                    {
                        list.add(half2.get(i).index);
                    }
                    break;
                case 'b':
                    for (int i = half1.size() - 1; i >= 0; i--)
                    {
                        list.add(half1.get(i).index);
                    }
                    for (int i = half2.size() - 1; i >= 0; i--)
                    {
                        list.add(half2.get(i).index);
                    }
                    break;
                case 'c':
                    for (int i = 0; i < half1.size(); i++)
                    {
                        list.add(half1.get(i).index);
                    }
                    for (int i = half2.size() - 1; i >= 0; i--)
                    {
                        list.add(half2.get(i).index);
                    }
                    break;
                case 'd':
                    for (int i = 0; i < half1.size(); i++)
                    {
                        list.add(half1.get(i).index);
                    }
                    for (int i = 0; i < half2.size(); i++)
                    {
                        list.add(half2.get(i).index);
                    }
                    break;

                default:
                    break;
            }
        }
        List<Integer> result = new ArrayList<>();
        result.addAll(DivideAndConquer(half1));
        result.addAll(DivideAndConquer(half2));
        return result;
    }

    public static void main(String[] args){
        List<City> cities = Helper.loadFile2();
        long start = System.currentTimeMillis();
        System.out.println(DivideAndConquer(cities));

        System.out.println("########################################################################");
        System.out.println("The tour's length using Divide and Conquer method is " + distance);
        System.out.println("########################################################################");
        long end = System.currentTimeMillis();
        System.out.println("Elapsed Time in milli seconds: "+ (end-start));
    }
}
