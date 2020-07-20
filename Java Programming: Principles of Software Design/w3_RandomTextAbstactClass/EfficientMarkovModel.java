import java.util.*;

/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovModel extends AbstractMarkovModel {
    private int order;
    private Map<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int order){
        this.order = order;
        map = new HashMap();
    }
    
    public String getRandomText(int numChars){
        if (myText == null) return "";
        
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-order);  //last two is invalid
        String key = myText.substring(index,index+order);
        sb.append(key);
        buildMap();
        
        for (int k=order; k<numChars; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public String toString(){
        return "the order of EfficientMarkovModel class is "+order;
    }
    
    protected ArrayList getFollows(String key){
        return map.get(key);
    }
    
    public void buildMap(){
        for (int i=0; i<myText.length()-order+1; i++){
            String key = myText.substring(i, i+order);
            if (!map.containsKey(key)){
                map.put(key, new ArrayList<String>());
            }
            
            if (i<myText.length()-order){
                map.get(key).add(myText.substring(i+order,i+order+1));
            }
            //else, the last one key has an empty arraylist.
        }
    }
    
    public void printHashMapInfo(){
        System.out.println("It has "+map.size()+" keys in the HashMap");
        
        int maxSize = 0;
        for(String key: map.keySet()){
            int size = map.get(key).size();
            if (size > maxSize){
                maxSize = size;
            }
        }
        System.out.println("The maximum number of keys following a key is "+maxSize);
        
        System.out.println("Keys that have the largest ArrayList are");
        for(String key: map.keySet()){
            int size = map.get(key).size();
            if (size == maxSize){
                System.out.print(key+"\t");
            }
        }
        System.out.println("\n");
    }
}