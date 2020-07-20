import java.util.*;

/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovWord implements IMarkovModel {
    private int order;
    private String[] myText;
    private Random myRandom;
    
    public MarkovWord(int order){
        this.order = order;
        myRandom = new Random();
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
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
        ArrayList<String> ans = new ArrayList<String>();
        int pos = 0;
        while (true) {
            int idx = indexOf(myText,keys,pos);
            if (idx == -1 || idx >= myText.length - order) break;
            ans.add(myText[idx+order]);
            pos = idx + 1;
        }
        return ans;
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
}
