import java.util.*;

/**
 * Write a description of MarkovZero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovZero implements IMarkovModel {
    private Random myRandom;
    private String myText;
    
    public MarkovZero(){
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null) return "";
        
        StringBuilder sb = new StringBuilder();
        for (int k=0; k<numChars; k++){
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }
        return sb.toString();
    }

}
