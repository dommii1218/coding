import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        for (String word: resource.words()){
            int wordLength = 0;
            StringBuilder sb = new StringBuilder(word);
            int k = 0;
            while (k<sb.length()){
                char ch = sb.charAt(k);
                if (k == 0 && !Character.isLetter(ch)){
                    sb.deleteCharAt(k);
                }
                else if (k == sb.length()-1 && !Character.isLetter(ch)){
                    sb.deleteCharAt(k);
                    k--;
                }
                else {
                    k++;
                }
            }
            counts[sb.length()]++;
        }
    }
    
    public int indexOfMax(int[] values){
        int maxCount = 0;
        int maxIndex = 0;
        for (int k=0; k<values.length; k++){
            if (values[k] > maxCount){
                maxIndex = k;
                maxCount = values[k];
            }
        }
        return maxIndex;
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int k=0; k<counts.length; k++){
            System.out.println(counts[k] + " words of length " + k);
        }
        System.out.println("========================================");
        System.out.println("Max Index of word length " + indexOfMax(counts));
    }

}
