import edu.duke.*;
import java.util.*;

public class WordsFrequenciesMap {
    public void countWords(){
        FileResource resource = new FileResource();
        HashMap<String,Integer> map = new HashMap<String,Integer>();   //key, value
        
        for (String w: resource.words()){
            w = w.toLowerCase();
            if (!map.containsKey(w)){
                map.put(w,1);
            }
            else {
                map.put(w,map.get(w)+1);
            }
        }
        
        for (String w: map.keySet()){
            int freq = map.get(w);
            if (freq > 500){
                System.out.println(freq + "\t" + w);
            }
        }
    }
}
