import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String,ArrayList<String>> map;
    
    public WordsInFiles(){
        map = new HashMap<String,ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for (String w: fr.words()){
            if (!map.containsKey(w)){
                map.put(w,new ArrayList<String>());
                map.get(w).add(f.getName());
            }
            else{
                if (!map.get(w).contains(f.getName())){
                    map.get(w).add(f.getName());
                }
            }
        }
    }
    
    
    public void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int maxNumber = 0;
        for (String w: map.keySet()){
            int currNumber = map.get(w).size();
            if (currNumber > maxNumber){
                maxNumber = currNumber;
            }
        }
        return maxNumber;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> list = new ArrayList<String>();
        for (String w: map.keySet()){
            if (map.get(w).size() == number){
                list.add(w);
            }
        }
        return list;
    }
    
    public int numOfWordsInNumFiles(int number){
        ArrayList<String> list = wordsInNumFiles(number);
        return list.size();
    }
    
    public void printFilesIn(String word){
        ArrayList<String> list = new ArrayList<String>(map.get(word));
        for (int k=0; k<list.size(); k++){
            System.out.println(list.get(k));
        }
    }
    
    public void tester(){
        buildWordFileMap();
        int maxNumber = maxNumber();
        ArrayList<String> list = wordsInNumFiles(maxNumber);
        for (int k=0; k<list.size(); k++){
            System.out.println(list.get(k));
            printFilesIn(list.get(k));
            System.out.println("=================");
        }
    }
    
    public void printFileAppears(String word){
        System.out.println(word);
        ArrayList list = map.get(word);
        for (int k=0; k<list.size(); k++){
            System.out.print(list.get(k) + "\t");
        }
    }
    
    public void quiz(){
        buildWordFileMap();
        System.out.println("Q12: "+numOfWordsInNumFiles(7)+" words appear in 7 files.");
        System.out.println("Q13: "+numOfWordsInNumFiles(4)+" words appear in 4 files.");
        printFileAppears("sea");
        System.out.println(" ");
        printFileAppears("tree");
    }

}
