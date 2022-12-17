package ch7.n10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Roads {
    static Map<City, Set<Neighbour>> roads = new HashMap<>();

    public static void main(String[] args) {
        City a = new City("A");
        City b = new City("B");
        City c = new City("C");
        City d = new City("D");
        Set<Neighbour> aNeighbours = new HashSet<>();
        Set<Neighbour> bNeighbours = new HashSet<>();
        Set<Neighbour> cNeighbours = new HashSet<>();
        Set<Neighbour> dNeighbours = new HashSet<>();
        Neighbour a_b = new Neighbour(b,10);
        Neighbour a_c = new Neighbour(c,11);
        Neighbour b_d = new Neighbour(d,9);
        Neighbour c_d = new Neighbour(d,11);
        aNeighbours.add(a_b);
        aNeighbours.add(a_c);
        bNeighbours.add(b_d);
        cNeighbours.add(c_d);
        roads.put(a,aNeighbours);
        roads.put(b,bNeighbours);
        roads.put(c,cNeighbours);
        roads.put(d,dNeighbours);

    }
}
