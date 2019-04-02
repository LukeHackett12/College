import java.util.Comparator;

public class Path {
    int v;
    double weight;

    Path(int v, double weight){
        this.v = v;
        this.weight = weight;
    }

    public boolean equals(Path path){
        return (path.v==this.v);
    }

    @Override
    public int hashCode(){
        return v;
    }
}

class MyComparator implements Comparator<Path>{
    @Override
    public int compare(Path path, Path pathAlt) {
        return Double.compare(path.weight, pathAlt.weight);
    }
}
