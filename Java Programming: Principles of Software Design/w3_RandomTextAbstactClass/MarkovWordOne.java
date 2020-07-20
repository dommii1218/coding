import java.util.*;

/**
 * Write a description of MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne(){
        myRandom = new Random();
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");     //one or more appears of white space
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public int indexOf(String[] myText, String key, int pos){
        for (int k=pos; k<myText.length; k++){
            if (myText[k].equals(key)){
                return k;
            }
        }
        return -1;
    }
    
    public ArrayList getFollows(String key){
        ArrayList<String> ans = new ArrayList<String>();
        int pos = 0;
        while (true) {
            int idx = indexOf(myText,key,pos);
            if (idx == -1 || idx >= myText.length - 1) break;
            ans.add(myText[idx+1]);
            pos = idx + 1;
        }
        return ans;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for (int k=1; k<numWords; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        return sb.toString().trim();
    }

}
