import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    private FileResource fr;
    
    public VigenereBreaker(){
        fr = new FileResource();
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String ans = "";
        for (int i=whichSlice; i<message.length(); i+=totalSlices){
            ans += message.charAt(i);
        }
        return ans;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cracker = new CaesarCracker(mostCommon);
        for (int i=0; i<klength; i++){
            String sliceString = sliceString(encrypted, i, klength);
            key[i] = cracker.getKey(sliceString);
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> set = new HashSet<String>();
        for (String s: fr.lines()){
            set.add(s.trim().toLowerCase());
        }
        return set;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int countWords = 0;
        for (String s: message.split("\\W+")){
            if (dictionary.contains(s.toLowerCase())){
                countWords ++;
            }
        }
        return countWords;
    }
    
    public int breakForKeyLength(String encrypted, HashSet<String> dictionary){
        int maxRealWords = 0;
        int keyLength = 0;
        char mostCommonChar = mostCommonCharIn(dictionary);
        for (int i=1; i<101; i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int countWords = countWords(decrypted, dictionary);
            if (countWords > maxRealWords){
                maxRealWords = countWords;
                keyLength = i;
            }
        }
        return keyLength;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int maxRealWords = 0;
        int keyLength = 0;
        char mostCommonChar = mostCommonCharIn(dictionary);
        for (int i=1; i<101; i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int countWords = countWords(decrypted, dictionary);
            if (countWords > maxRealWords){
                maxRealWords = countWords;
                keyLength = i;
            }
        }
        int[] keys = tryKeyLength(encrypted, keyLength, mostCommonChar);
        VigenereCipher vc = new VigenereCipher(keys);
        String decrypted = vc.decrypt(encrypted);
        return decrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (String s: dictionary){
            for (char ch: s.toCharArray()){
                ch = Character.toLowerCase(ch);
                int idx = alphabet.indexOf(ch);
                if (idx != -1){
                    counts[idx] ++;
                }
            }
        }
        
        Character mostCommonChar = null;
        int charCount = 0;
        for (int i=0; i<counts.length; i++){
            if (counts[i] > charCount){
                charCount = counts[i];
                mostCommonChar = alphabet.charAt(i);
            }
        }
        return mostCommonChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        Map<String,Integer> map = new HashMap();
        String ans = "";
        int maxCount = 0;
        for (String lang: languages.keySet()){
            HashSet<String> langSet = languages.get(lang);
            String decrypted = breakForLanguage(encrypted, langSet);
            int countWords = countWords(decrypted, langSet);
            if (countWords > maxCount){
                maxCount = countWords;
                ans = decrypted;
            }
        }
        System.out.println("decrypted for all languages is:"+"\n"+ans);
    }
   
    public void breakVigenere() {
        String input = fr.asString();
        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", 
                "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> languagesMap = new HashMap();
        for (String s: languages){
            languagesMap.put(s, readDictionary(new FileResource("dictionaries/"+s)));
        }
        breakForAllLangs(input,languagesMap);
        
        //int countWords = countWords(decrypted, readDictionary(dictionaryFr));
        //System.out.println("number of valid words is "+countWords);
    }
    
    
    
    
    
    public void quiz1(){
        String input = fr.asString();
        FileResource dictionaryFr = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictionaryFr);
        int keyLength = breakForKeyLength(input, dictionary);
        System.out.println("key length is "+keyLength);
        
        String decrypted = breakForLanguage(input, dictionary);
        System.out.println("first 50 words are "+"\n"+decrypted.substring(0,50));
        
        int countWords = countWords(decrypted, dictionary);
        System.out.println("number of valid words is "+countWords);
        
        int[] keys38 = tryKeyLength(input, 38, 'e');
        VigenereCipher vc38 = new VigenereCipher(keys38);
        String decrypted38 = vc38.decrypt(input);
        int countWords38 = countWords(decrypted38, dictionary);
        System.out.println("number of valid words for key length 38 is "+countWords38);
    }
    
    public void testSliceString(){
        System.out.println(sliceString("abcdefghijklm", 0, 3));
        System.out.println(sliceString("abcdefghijklm", 1, 3));
        System.out.println(sliceString("abcdefghijklm", 2, 3));
        System.out.println(sliceString("abcdefghijklm", 0, 4));
        System.out.println(sliceString("abcdefghijklm", 1, 4));
    }
    
    public void testTryKeyLength(){
        int[] keys = tryKeyLength(fr.asString(),4,'e');
        for (int i=0; i<keys.length; i++){
            System.out.print(keys[i]+"\t");
        }
    }
    
    public void testMostCommonCharIn(){
        String input = fr.asString();
        FileResource dictionaryFr = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictionaryFr);
        System.out.println(mostCommonCharIn(dictionary));
    }
}
