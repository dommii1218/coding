import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
        System.out.println("Found "+listBig.size()+" quakes that match that criteria");
    }
   
    
    
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
                      double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        ArrayList<QuakeEntry> listBig = filterByDepth(list, -10000.0, -8000.0);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
        System.out.println("Found "+listBig.size()+" quakes that match that criteria");
    }
    
    
    
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
                   System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                   qe.getLocation().getLatitude(),
                   qe.getLocation().getLongitude(),
                   qe.getMagnitude(),
                   qe.getInfo());
        }
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    
    
    
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        //Location city = new Location(35.988, -78.907);    //Durham, NC
        Location city = new Location(38.17, -118.82);    //Bridgeport, CA
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
        System.out.println("Found "+close.size()+" quakes that match that criteria");

    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
                                                String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if (where.equals("start")){
            for (QuakeEntry qe : quakeData) {
                String[] info = qe.getInfo().split(" ");
                if (info[0].equals(phrase)){
                    answer.add(qe);
                }
            }
        }
        else if (where.equals("any")){
            for (QuakeEntry qe : quakeData) {
                int idx = qe.getInfo().indexOf(phrase);
                if (idx != -1){
                    answer.add(qe);
                }
            }
        }
        else if (where.equals("end")){
            for (QuakeEntry qe : quakeData) {
                String[] info = qe.getInfo().split(" ");
                if (info[info.length-1].equals(phrase)){
                    answer.add(qe);
                }
            }
        }
        
        return answer;     
    }
    
    public void testFilterByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        //ArrayList<QuakeEntry> listPhrase = filterByPhrase(list, "start", "Can");
        //for (int k=0; k< listPhrase.size(); k++) {
        //    System.out.println(listPhrase.get(k));
        //}
        System.out.println("Found "+filterByPhrase(list, "start", "Explosion").size()+" quakes that match Explosion at start");
        System.out.println("Found "+filterByPhrase(list, "end", "California").size()+" quakes that match California at end");
        System.out.println("Found "+filterByPhrase(list, "any", "Creek").size()+" quakes that match Creek at any");
    }
}
