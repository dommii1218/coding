import edu.duke.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> charactersArrayList;
    private ArrayList<Integer> freqsArrayList;
    
    public CharactersInPlay(){
        charactersArrayList = new ArrayList<String>();
        freqsArrayList = new ArrayList<Integer>();
    }
    
    public void update(String person){
        int index = charactersArrayList.indexOf(person);
        if (index == -1){
            charactersArrayList.add(person);
            freqsArrayList.add(1);
        }
        else {
            freqsArrayList.set(index, freqsArrayList.get(index)+1);
        }
    }
    
    public void findAllCharacters(){
        FileResource fr = new FileResource();
        for (String line: fr.lines()){
            int index = line.indexOf(".");
            if (index != -1 && !line.contains("SCENE")){
                String character = line.substring(0, index).trim();
                update(character);
            }
        }
    }
    
    public void tester(){
        findAllCharacters();
        charactersWithNumParts(10,15);
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for (int k=0; k<charactersArrayList.size(); k++){
            int freq = freqsArrayList.get(k);
            if (freq >= num1 && freq <= num2){
                System.out.println(charactersArrayList.get(k) + " has "
                     + freq + " parts to speak. ");
            }
        }
    }

}
