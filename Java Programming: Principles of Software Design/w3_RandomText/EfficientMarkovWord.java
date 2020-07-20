import java.util.*;

/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovWord implements IMarkovModel {
    private int order;
    private String[] myText;
    private Random myRandom;
    private Map<WordGram, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order){
        this.order = order;
        myRandom = new Random();
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        map = new HashMap();
        buildMap();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    private int indexOf(String[] myText, WordGram keys, int pos){
        for (int k=pos; k<myText.length-order; k++){
            WordGram newGram = new WordGram(myText, k, order);
            if (newGram.equals(keys)){
                return k;
            }
        }
        return -1;
    }
    
    public ArrayList<String> getFollows(WordGram keys){
        return map.get(keys);
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-order);
        WordGram keys = new WordGram(myText, index, order);
        sb.append(keys.toString());
        sb.append(" ");
        
        for (int k=2; k<numWords; k++){
            ArrayList<String> follows = getFollows(keys);
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            keys = keys.shiftAdd(next);
        }
        return sb.toString().trim();
    }
    
    public void buildMap(){
        for (int k=0; k<myText.length-order+1; k++){
            WordGram wg = new WordGram(myText, k, order);
            if (!map.containsKey(wg)){
                map.put(wg, new ArrayList<String>());
            }
            if (k<myText.length-order){
                map.get(wg).add(myText[k+order]);
            }
        }
    }
    
    public void printHashMapInfo(){
        System.out.println("It has "+map.size()+" keys in the HashMap");
        
        int maxSize = 0;
        for(WordGram key: map.keySet()){
            int size = map.get(key).size();
            if (size > maxSize){
                maxSize = size;
            }
        }
        System.out.println("The maximum number of keys following a key is "+maxSize);
        
        System.out.println("Keys that have the largest ArrayList are");
        for(WordGram key: map.keySet()){
            int size = map.get(key).size();
            if (size == maxSize){
                System.out.print(key+"\t");
            }
        }
        System.out.println("\n");
    }
    

}
