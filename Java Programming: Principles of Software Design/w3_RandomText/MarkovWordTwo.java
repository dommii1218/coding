import java.util.*;

/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo(){
        myRandom = new Random();
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");     //one or more appears of white space
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    private int indexOf(String[] myText, String key1, String key2, int pos){
        for (int k=pos; k<myText.length-1; k++){
            if (myText[k].equals(key1) && myText[k+1].equals(key2)){
                return k;
            }
        }
        return -1;
    }
    
    public ArrayList getFollows(String key1, String key2){
        ArrayList<String> ans = new ArrayList<String>();
        int pos = 0;
        while (true) {
            int idx = indexOf(myText,key1,key2,pos);
            if (idx == -1 || idx >= myText.length - 2) break;
            ans.add(myText[idx+2]);
            pos = idx + 1;
        }
        return ans;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        
        for (int k=2; k<numWords; k++){
            ArrayList<String> follows = getFollows(key1,key2);
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        return sb.toString().trim();
    }

}
