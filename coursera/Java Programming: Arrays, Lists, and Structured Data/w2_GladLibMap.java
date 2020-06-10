import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    //private HashMap<String, String> myLabelSource;
    private Random myRandom;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    private ArrayList<String> wordsUsed;
    private ArrayList<String> categoriesUsed;
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] categoryArray = {"adjective", "noun", "color", "country", "name", "animal",
                        "timeframe", "verb", "fruit"};
        myMap = new HashMap<String, ArrayList<String>>();
        ArrayList<String> wordsUsed = new ArrayList<String>();
        ArrayList<String> categoriesUsed = new ArrayList<String>();
        for (String s: categoryArray){
            myMap.put(s, readIt(source+"/"+s+".txt"));
        }
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (myMap.containsKey(label)){
            if (label.equals("number")){
                return ""+myRandom.nextInt(50)+5;
            }
            else {
                return randomFrom(myMap.get(label));
            }
        }
        else {
            return "**UNKNOWN**";
        }
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String label = w.substring(first+1,last);
        if (!categoriesUsed.contains(label)){
            categoriesUsed.add(label);
        }
        String sub = getSubstitute(label);
        while (true) {
            if (!wordsUsed.contains(sub)){
                wordsUsed.add(sub);
                break;
            }
            sub = getSubstitute(w.substring(first+1,last));
        }
        return prefix+sub+suffix;
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
    }
    
    public int totalWordsInMap(){
        int totalWords = 0;
        for (String s: myMap.keySet()){
            totalWords += myMap.get(s).size();
        }
        return totalWords;
    }
    
    public int totalWordsConsidered(){
        int totalWords = 0;
        for (String s: categoriesUsed){
            totalWords += myMap.get(s).size();
        }
        return totalWords;
    }
}
