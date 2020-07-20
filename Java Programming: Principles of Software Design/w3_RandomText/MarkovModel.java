import java.util.*;

/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovModel implements IMarkovModel {
    private Random myRandom;
    private String myText;
    private int numToPred;
    
    public MarkovModel(int num){
        myRandom = new Random();
        numToPred = num;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList getFollows(String key){
        ArrayList<String> ans = new ArrayList<String>();
        int pos = 0;
        while (true) {
            int idx = myText.indexOf(key, pos);
            if (idx == -1 || idx >= myText.length() - key.length()) break;
            ans.add(myText.substring(idx+key.length(),idx+key.length()+1));
            pos = idx + 1;
        }
        return ans;
    }
    
    public String getRandomText(int numChars){
        if (myText == null) return "";
        
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-numToPred);  //last two is invalid
        String key = myText.substring(index,index+numToPred);
        sb.append(key);
        
        for (int k=numToPred; k<numChars; k++){
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
