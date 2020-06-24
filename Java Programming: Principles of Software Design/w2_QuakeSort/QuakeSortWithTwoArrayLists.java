import java.util.*;
import edu.duke.*;

public class QuakeSortWithTwoArrayLists {
    public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes){
        QuakeEntry min = quakes.get(0);
        for (QuakeEntry qe: quakes){
            if (qe.getMagnitude() < min.getMagnitude()){
                min = qe;
            }
        }
        return min;
    }
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in){
        ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
        while (!in.isEmpty()){
            QuakeEntry min = getSmallestMagnitude(in);
            out.add(min);
            in.remove(min);
        }
        return out;
    }
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        list = sortByMagnitude(list);
        for (QuakeEntry qe: list){
            System.out.println(qe);
        }
    }
}
