import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        for (int i=0; i < input.length(); i++){
            char currChar = encrypted.charAt(i);
            char currUpperChar = Character.toUpperCase(currChar);
            int idx = alphabet.indexOf(currUpperChar);
            if (idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                if (currChar == currUpperChar) {encrypted.setCharAt(i,newChar);}
                else {encrypted.setCharAt(i,Character.toLowerCase(newChar));}
            }
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        for (int i=0; i < input.length(); i++){
            char currChar = encrypted.charAt(i);
            char currUpperChar = Character.toUpperCase(currChar);
            int idx = alphabet.indexOf(currUpperChar);
            if (idx != -1){
                if (i % 2 == 0){
                    char newChar = shiftedAlphabet1.charAt(idx);
                    if (currChar == currUpperChar) {encrypted.setCharAt(i,newChar);}
                    else {encrypted.setCharAt(i,Character.toLowerCase(newChar));}
                }
                else {
                    char newChar = shiftedAlphabet2.charAt(idx);
                    if (currChar == currUpperChar) {encrypted.setCharAt(i,newChar);}
                    else {encrypted.setCharAt(i,Character.toLowerCase(newChar));}
                }
            }
        }
        return encrypted.toString();
    }
    
    public void testCaesar(){
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public void test(){
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        System.out.println("--------------------------------");
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }
}
