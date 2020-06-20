import java.util.*;

/**
 * Write a description of EarthQuakeClient2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EarthQuakeClient2 {
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry>quakeData,
                                        Filter f){
        ArrayList<QuakeEntry> ans = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe: quakeData){
            if (f.satisfies(qe)){
                ans.add(qe);
            }
        }
        return ans;
    }
    
    public void quakeWithFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        Location city = new Location(35.42, 139.43);
        Filter f1 = new DistanceFilter(city, 10000000);
        Filter f2 = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> largeQuakes = filter(list,f1);
        
        for (int k=0; k< largeQuakes.size(); k++) {
            System.out.println(largeQuakes.get(k));
        }
    }
    
    public void testMatchAllFilters(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        MatchAllFilters f = new MatchAllFilters();
        f.addFilter(new MagnitudeFilter(0.0,3.0));
        f.addFilter(new DistanceFilter(new Location(36.1314,-95.9372),10000000));
        f.addFilter(new PhraseFilter("any", "Ca"));
        
        ArrayList<QuakeEntry> ans = filter(list,f);
        for (int k=0; k< ans.size(); k++) {
            System.out.println(ans.get(k));
        }
        
        System.out.println("Filters used are: ");
        System.out.println(f.getName());
    }
    
    public void quiz(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        MatchAllFilters f = new MatchAllFilters();
        f.addFilter(new DistanceFilter(new Location(35.42, 139.43),10000000));
        f.addFilter(new PhraseFilter("end", "Japan"));
        ArrayList<QuakeEntry> ans = filter(list,f);
        System.out.println("Q1: Found "+ans.size()+" quakes that match that criteria");
        
        MatchAllFilters f2 = new MatchAllFilters();
        f2.addFilter(new MagnitudeFilter(4.0,5.0));
        f2.addFilter(new DepthFilter(-35000.0, -12000.0));
        ArrayList<QuakeEntry> ans2 = filter(list,f2);
        System.out.println("Q2: Found "+ans2.size()+" quakes that match that criteria");
        
        MatchAllFilters f3 = new MatchAllFilters();
        f3.addFilter(new MagnitudeFilter(0.0,2.0));
        f3.addFilter(new DepthFilter(-100000.0, -10000.0));
        f3.addFilter(new PhraseFilter("any", "a"));
        ArrayList<QuakeEntry> ans3 = filter(list,f3);
        System.out.println("Q3: Found "+ans3.size()+" quakes that match that criteria");
        
        MatchAllFilters f4 = new MatchAllFilters();
        f4.addFilter(new MagnitudeFilter(0.0,3.0));
        f4.addFilter(new DistanceFilter(new Location(36.1314, -95.9372),10000000));
        f4.addFilter(new PhraseFilter("any", "Ca"));
        ArrayList<QuakeEntry> ans4 = filter(list,f4);
        System.out.println("Q4: Found "+ans4.size()+" quakes that match that criteria");
        
    }
    
    public void quiz2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        Filter f = new DepthFilter(-12000,-10000);
        ArrayList<QuakeEntry> ans = filter(list,f);
        System.out.println("Q1: Found "+ans.size()+" quakes that match that criteria");
        
        Filter f2 = new DepthFilter(-4000,-2000);
        ArrayList<QuakeEntry> ans2 = filter(list,f2);
        System.out.println("Q2: Found "+ans2.size()+" quakes that match that criteria");
        
        Filter f3 = new PhraseFilter("start", "Quarry Blast");
        ArrayList<QuakeEntry> ans3 = filter(list,f3);
        System.out.println("Q3: Found "+ans3.size()+" quakes that match that criteria");
        
        Filter f4 = new PhraseFilter("end", "Alaska");
        ArrayList<QuakeEntry> ans4 = filter(list,f4);
        System.out.println("Q4: Found "+ans4.size()+" quakes that match that criteria");
        
        Filter f5 = new PhraseFilter("any", "Can");
        ArrayList<QuakeEntry> ans5 = filter(list,f5);
        System.out.println("Q5: Found "+ans5.size()+" quakes that match that criteria");
        
        MatchAllFilters f8 = new MatchAllFilters();
        f8.addFilter(new DistanceFilter(new Location(39.7392, -104.9903),1000000));
        f8.addFilter(new PhraseFilter("end", "a"));
        ArrayList<QuakeEntry> ans8 = filter(list,f8);
        System.out.println("Q8: Found "+ans8.size()+" quakes that match that criteria");
        
        MatchAllFilters f9 = new MatchAllFilters();
        f9.addFilter(new MagnitudeFilter(3.5,4.5));
        f9.addFilter(new DepthFilter(-55000.0, -20000.0));
        ArrayList<QuakeEntry> ans9 = filter(list,f9);
        System.out.println("Q9: Found "+ans9.size()+" quakes that match that criteria");
        
        MatchAllFilters f10 = new MatchAllFilters();
        f10.addFilter(new MagnitudeFilter(1.0,4.0));
        f10.addFilter(new DepthFilter(-180000.0, -30000.0));
        f10.addFilter(new PhraseFilter("any", "o"));
        ArrayList<QuakeEntry> ans10 = filter(list,f10);
        System.out.println("Q10: Found "+ans10.size()+" quakes that match that criteria");
        
        MatchAllFilters f11 = new MatchAllFilters();
        f11.addFilter(new MagnitudeFilter(0.0,5.0));
        f11.addFilter(new DistanceFilter(new Location(55.7308, 9.1153),3000000));
        f11.addFilter(new PhraseFilter("any", "e"));
        ArrayList<QuakeEntry> ans11 = filter(list,f11);
        System.out.println("Q11: Found "+ans11.size()+" quakes that match that criteria");
    }
}
