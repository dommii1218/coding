import java.util.*;

/**
 * Write a description of AbstractMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class AbstractMarkovModel implements IMarkovModel {
    protected Random myRandom;
    protected String myText;
    
    public AbstractMarkovModel(){
        myRandom = new Random();
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    protected ArrayList getFollows(String key){
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
    
    abstract public String getRandomText(int numChars);
}
