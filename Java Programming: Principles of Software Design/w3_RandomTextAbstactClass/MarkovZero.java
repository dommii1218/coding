import java.util.*;

/**
 * Write a description of MarkovZero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovZero extends AbstractMarkovModel {
    
    public MarkovZero(){
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
