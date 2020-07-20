import java.util.*;

/**
 * Write a description of WordGram here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordGram { 
    //similar to String Class (methods like wordAt, equals..)
    private String[] myWords;
    
    public WordGram(String[] source, int start, int size){
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
        //paras meaning: src, srcPos, dest, destPos, length
    }
    
    public String wordAt(int index){     //String.charAt()
        if (index < 0 || index >= myWords.length){
            throw new IndexOutOfBoundsException("bad index "+index);
        }
        return myWords[index];
    }
    
    public int length(){                 //String.length();
        return myWords.length;
    }
    
    public String toString(){
        String ret = "";
        for (String s: myWords){
            ret += s + " ";
        }
        return ret.trim();
    }
    
    public boolean equals(Object o){
        WordGram other = (WordGram) o;
        if (this.length() != other.length()) return false;
        for (int k=0; k<myWords.length; k++){
            if (! myWords[k].equals(other.wordAt(k))) return false;
        }
        return true;
    }
    
    public WordGram shiftAdd(String word){
        int size = myWords.length;
        String[] newWords = new String[size];
        for (int k=0; k<size-1; k++){
            newWords[k] = myWords[k+1];
        }
        newWords[size-1] = word;
        return new WordGram(newWords,0,size);
    }
    
    public int hashCode(){
        return toString().hashCode();
    }

}

