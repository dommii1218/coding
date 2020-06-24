import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    
    //Selection Sort
    
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from){
        int minIndex = from;
        for (int i=from; i<quakes.size(); i++){
            if (quakes.get(i).getMagnitude() < quakes.get(minIndex).getMagnitude()){
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in){
        for (int i=0; i<in.size(); i++){
            int minIndex = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIndex);
            in.set(i,qmin);
            in.set(minIndex,qi);
        }
        return in;
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        for (int i=0; i<in.size(); i++){
            if (checkInSortedOrder(in)){
                System.out.println("#selection sort passes: "+i);
                break;
            }
            
            int minIndex = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIndex);
            in.set(i,qmin);
            in.set(minIndex,qi);
        }
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int maxIndex = from;
        for (int i=from; i<quakes.size(); i++){
            if (quakes.get(i).getDepth() > quakes.get(maxIndex).getDepth()){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public ArrayList<QuakeEntry> sortByLargestDepth(ArrayList<QuakeEntry> in){
        //for (int i=0; i<in.size(); i++){
        for (int i=0; i<70; i++){
            int maxIndex = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIndex);
            in.set(i,qmax);
            in.set(maxIndex,qi);
        }
        return in;
    }
    
    //Bubble Sort
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for (int i=0; i<quakeData.size()-numSorted-1; i++){
            QuakeEntry qi = quakeData.get(i);
            QuakeEntry qi2 = quakeData.get(i+1);
            if (qi.getMagnitude() > qi2.getMagnitude()){
                quakeData.set(i, qi2);
                quakeData.set(i+1, qi);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for (int i=0; i<in.size()-1; i++){
            onePassBubbleSort(in, i);
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for (int i=0; i< quakes.size()-1; i++) {
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        for (int i=0; i<in.size()-1; i++){
            onePassBubbleSort(in, i);
            if (checkInSortedOrder(in)){
                System.out.println("#bubble selection passes: "+(i+1));
                break;
            }
        }
    }
    
    //test
    
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthquakeDataSampleSix1.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        
        list = parser.read(source);
        sortByMagnitudeWithCheck(list);
        //sortByMagnitudeWithBubbleSort(list);
        //for (QuakeEntry qe: list){
        //    System.out.println(qe);
        //}
    }
    
    public void quiz(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataDec6sample1.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        ArrayList<QuakeEntry> list1 = sortByLargestDepth(list);
        System.out.println("Q1: "+list1.get(list1.size()-1).getDepth());
        
        System.out.print("Q2: ");
        sortByMagnitudeWithCheck(list);
        
        list = parser.read(source);
        System.out.print("Q2: ");
        sortByMagnitudeWithBubbleSortWithCheck(list);
    }
    
    public void quiz2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        ArrayList<QuakeEntry> list1 = sortByLargestDepth(list);
        System.out.println("Q1: "+list1.get(list1.size()-1).getDepth());
        
        source = "data/earthQuakeDataWeekDec6sample2.atom";
        list = parser.read(source);
        System.out.print("Q2: ");
        sortByMagnitudeWithCheck(list);
        
        source = "data/earthQuakeDataWeekDec6sample1.atom";
        list = parser.read(source);
        System.out.print("Q3: ");
        sortByMagnitudeWithBubbleSortWithCheck(list);
    }
}
