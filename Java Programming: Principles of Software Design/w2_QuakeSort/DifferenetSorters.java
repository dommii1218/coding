import java.util.*;

public class DifferenetSorters {
    public void sortWithCompareTo(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list);
        int quakeNumber = 600;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }
    
    public void sortByTitleAndDepth(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new TitleAndDepthComparator());
        int quakeNumber = 500;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }
    
    public void sortByLastWordInTitleThenByMagnitude(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        int quakeNumber = 500;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }
}
