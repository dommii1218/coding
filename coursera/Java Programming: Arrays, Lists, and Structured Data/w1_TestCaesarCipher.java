import edu.duke.*;

public class TestCaesarCipher {
    public int[] countLetters(String input){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k=0; k<input.length(); k++){
            char ch = Character.toLowerCase(input.charAt(k));
            int index = alpha.indexOf(ch);
            if (index != -1){
                counts[index] ++;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] values){
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
    
    public void simpleTests(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println("message is " + message);
        int key = 15;
        CaesarCipher cc = new CaesarCipher(key);
        String encrypted = cc.encrypt(message);
        System.out.println("key is " + key + "\n" + "encrypted message is " + encrypted);
        System.out.println("decrypted message is " + cc.decrypt(encrypted));
        System.out.println("==================================");
        breakCaesarCipher(encrypted);
    }
    
    public void breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;    //4 is the index of e
        if (maxDex < 4){dkey = 26 - (4-maxDex);}
        CaesarCipher cc = new CaesarCipher(dkey);
        System.out.println("automatically decrypted message is " + cc.decrypt(input));
    }
}
