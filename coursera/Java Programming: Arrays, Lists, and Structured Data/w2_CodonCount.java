import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String,Integer> map;
    
    public CodonCount(){
        map = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start, String data){
        map.clear();
        for (int k=start; k<data.length()-2; k+=3){
            String codon = data.substring(k, k+3);
                if (!map.containsKey(codon)){
                    map.put(codon,1);
                }
                else {
                    map.put(codon, map.get(codon)+1);
                }
            
        }
    }
    
    public String getMostCommonCodon(){
        int maxCount = 0;
        String mostCommonCodon = "";
        for (String codon: map.keySet()){
            int count = map.get(codon);
            if (count > maxCount){
                maxCount = count;
                mostCommonCodon = codon;
            }
        }
        return mostCommonCodon;
    }
    
    public void printCodonCounts(int start, int end){
        for (String codon: map.keySet()){
            int count = map.get(codon);
            if (count >= start && count <= end){
                System.out.println(codon + "\t" + count);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String data = fr.asString().trim().toUpperCase();
        for (int i=0; i<3; i++){
            buildCodonMap(i, data);
            System.out.println("Reading frame starting with " + i + 
                           " results in " + map.size() +" unique codons");
            String mostCommonString = getMostCommonCodon();
            System.out.println("and most common codon is " + mostCommonString + 
                           " with count " + map.get(mostCommonString));
            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            printCodonCounts(7, 7);
            System.out.println("==========================================");
        }
    }
}
