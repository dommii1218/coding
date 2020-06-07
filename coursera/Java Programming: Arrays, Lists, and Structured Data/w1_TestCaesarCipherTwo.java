import edu.duke.*;

public class TestCaesarCipherTwo {
    private int[] countLetters(String input){
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
    
    private int maxIndex(int[] values){
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
    
    private String halfOfString(String message, int start){
        String answer = "";
        for (int k=start; k<message.length(); k+=2){
            answer += message.charAt(k);
        }
        return answer;
    }
    
    public void simpleTests(){
       String message1 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
       CaesarCipherTwo ccTwo1 = new CaesarCipherTwo(21, 8);
       System.out.println("Q2: " + ccTwo1.encrypt(message1));
       System.out.println("==================================");
       
       String encrypted1 = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
       CaesarCipherTwo ccTwo2 = new CaesarCipherTwo(14, 24);
       System.out.println("Q6: " + ccTwo2.decrypt(encrypted1));
       System.out.println("==================================");
       
       String encrypted2 = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
       System.out.println("Q7: " + breakCaesarCipher(encrypted2));
       System.out.println("==================================");
       
       FileResource fr = new FileResource();
       String message2 = fr.asString();
       System.out.println("Q8: " + breakCaesarCipher(message2));
       System.out.println("==================================");
       
       int[] dkeys = getDecryptedTwoKeys(message2);
       System.out.println("Q9: two keys are " + dkeys[0] + " and " + dkeys[1]);
       System.out.println("==================================");
    }
    
    private int getDecryptedKey(String encrypted){
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;    //4 is the index of e
        if (maxDex < 4){dkey = 26 - (4-maxDex);}
        return dkey;
    }
    
    public String breakCaesarCipher(String encrypted){
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        
        int[] dkeys = getDecryptedTwoKeys(encrypted);
        CaesarCipher cc1 = new CaesarCipher(dkeys[0]);
        String ds1 = cc1.decrypt(s1);
        CaesarCipher cc2 = new CaesarCipher(dkeys[1]);
        String ds2 = cc2.decrypt(s2);
        
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
    
    private int[] getDecryptedTwoKeys(String encrypted){
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        int[] dkeys = new int[2];
        dkeys[0] = getDecryptedKey(s1);
        dkeys[1] = getDecryptedKey(s2);
        return dkeys;
    }
}
