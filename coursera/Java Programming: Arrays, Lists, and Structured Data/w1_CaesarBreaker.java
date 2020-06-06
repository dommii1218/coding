import edu.duke.*;

public class CaesarBreaker {
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
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;    //4 is the index of e
        if (maxDex < 4){dkey = 26 - (4-maxDex);}
        return cc.encrypt(encrypted, 26-dkey);
    }
    
    public int getDecryptedKey(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;    //4 is the index of e
        if (maxDex < 4){dkey = 26 - (4-maxDex);}
        return dkey;
    }
    
    public void testDecrypt(){
        String message = "eeeeeeeeeeeeeeeees";
        System.out.println("message is " + message);
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt(message, 23);
        System.out.println("encrypted message is " + encrypted);
        String decrypted = decrypt(encrypted);
        System.out.println("decrypted message is " + decrypted);
    }
    
    public String halfOfString(String message, int start){
        String answer = "";
        for (int k=start; k<message.length(); k+=2){
            answer += message.charAt(k);
        }
        return answer;
    }
    
    public int getKey(String s){
        int[] counts = countLetters(s);
        return maxIndex(counts);
    }
    
    public String decryptUnkownTwoKeys(String encrypted){
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        int dkey1 = getDecryptedKey(s1);
        int dkey2 = getDecryptedKey(s2);
        CaesarCipher cc = new CaesarCipher();
        String ds1 = cc.encrypt(s1, 26-dkey1);
        String ds2 = cc.encrypt(s2, 26-dkey2);
        StringBuilder sb = new StringBuilder(encrypted);
        for (int k=0; k<encrypted.length(); k++){
            if (k % 2 == 0){
                sb.setCharAt(k, ds1.charAt(k/2));
            }
            else {
                sb.setCharAt(k, ds2.charAt(k/2));
            }
        }
        return sb.toString();
    }
    
    public String decryptTwoKeys(String encrypted, int key1, int key2){
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        
        CaesarCipher cc = new CaesarCipher();
        String ds1 = cc.encrypt(s1, 26-key1);
        String ds2 = cc.encrypt(s2, 26-key2);
        StringBuilder sb = new StringBuilder(encrypted);
        for (int k=0; k<encrypted.length(); k++){
            if (k % 2 == 0){
                sb.setCharAt(k, ds1.charAt(k/2));
            }
            else {
                sb.setCharAt(k, ds2.charAt(k/2));
            }
        }
        return sb.toString();
    }
    
    public int[] getDecryptedTwoKeys(String encrypted){
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        int[] dkeys = new int[2];
        dkeys[0] = getDecryptedKey(s1);
        dkeys[1] = getDecryptedKey(s2);
        return dkeys;
    }
    
    public void testDecryptTwoKeys(){
        CaesarCipher cc = new CaesarCipher();
        String encrypted = "Top ncmy qkff vi vguv vbg ycpx";
        System.out.println("encrypted message is " + encrypted);
        System.out.println("decrypted message is " + decryptTwoKeys(encrypted, 2, 20));
        System.out.println("========================================");
        
        //String message = "eeeeeeeeeeeeeeeees";
        //System.out.println("message is " + message);
        //String encrypted = cc.encryptTwoKeys(message, 23, 2);
        String encrypted2 = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        System.out.println("encrypted message is " + encrypted2);
        System.out.println("decrypted message is " + decryptUnkownTwoKeys(encrypted2));
        System.out.println("========================================");
        
        FileResource fr = new FileResource("data/mysteryTwoKeysPractice.txt");
        String frString = fr.asString();
        System.out.println("decrypted message is " + decryptUnkownTwoKeys(frString));
        int[] twoKeys = getDecryptedTwoKeys(frString);
        System.out.println("two keys are " + twoKeys[0] + " and " + twoKeys[1]);
    }
}
