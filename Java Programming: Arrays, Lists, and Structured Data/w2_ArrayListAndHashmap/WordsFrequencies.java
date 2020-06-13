import edu.duke.*;
import java.util.ArrayList;

public class WordsFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordsFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        FileResource fr = new FileResource();
        
        for (String s: fr.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    public void tester(){
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        //for (int k=0; k<myWords.size(); k++){
        //    System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        //}
        int maxIndex = findIndexOfMax();
        System.out.println("The word occurs most is " + myWords.get(maxIndex) + 
        ", which occures " + myFreqs.get(maxIndex) + " times.");
    }
    
    public int findIndexOfMax(){
        if (myFreqs.size() == 0){
            return -1;
        }
        int maxIndex = 0;
        int maxFreq = 0;
        for (int k=0; k<myFreqs.size(); k++){
            if (myFreqs.get(k) > maxFreq){
                maxFreq = myFreqs.get(k);
                maxIndex = k;
            }
            
        }
        return maxIndex;
    }
}
