import java.util.*;

public class DistanceSorter {
    public void sortByDistance(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/earthQuakeDataDec6sample1.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for (QuakeEntry qe: list){
            System.out.println(qe);
        }
    }

}
