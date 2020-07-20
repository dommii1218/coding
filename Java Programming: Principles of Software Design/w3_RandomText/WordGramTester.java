import java.util.*;

/**
 * Write a description of WordGramTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordGramTester {
    public void testWordGram(){
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        int size = 4;
        for (int k=0; k<words.length-size; k++){
            WordGram wg = new WordGram(words, k, size);
            System.out.println(k+"\t"+wg.length()+"\t"+wg);
        }
    }
    
    public void testWordGramEquals(){
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        int size = 4;
        for (int k=0; k<words.length-size; k++){
            WordGram wg = new WordGram(words, k, size);
            list.add(wg);
        }
        WordGram first = list.get(0);
        System.out.println("checking "+first);
        for (int k=0; k<words.length-size; k++){
            if (first.equals(list.get(k))){
                System.out.println("matched at "+k+" "+list.get(k));
            }
        }
    }
    

}
