import java.util.*;

/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovModel extends AbstractMarkovModel {
    private int order;
    
    public MarkovModel(int order){
        this.order = order;
    }
    
    public String getRandomText(int numChars){
        if (myText == null) return "";
        
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-order);  //last two is invalid
        String key = myText.substring(index,index+order);
        sb.append(key);
        
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
}
