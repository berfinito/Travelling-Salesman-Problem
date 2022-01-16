public class City {
    public float x;
    public float y;
    public boolean isVisited = false;
    int index;

    public String toString(){
        return "[" + x + ", " + y + "]";// + "  " + isVisited + "  " + index;
    }
    public String toString2(){
        return x + " " + y + "  " + isVisited;
    }
}
