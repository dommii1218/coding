import java.util.*;

/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        ArrayList<QuakeEntry> largestList = getLargest(list, 50);
        for (QuakeEntry qe : largestList) {
           System.out.println(qe); 
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        double maxMag = 0.0;
        int index = 0;
        for (int k=0; k<data.size(); k++){
            double mag = data.get(k).getMagnitude();
            if (mag > maxMag){
                maxMag = mag;
                index = k;
            }
        }
        return index;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> data, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(data);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for (int i=0; i<howMany; i++){
            int maxIndex = indexOfLargest(copy);
            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        return ret;
    }
}
