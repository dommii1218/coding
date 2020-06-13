import edu.duke.*;

public class CaesarCipher {
    //Declaration of fields or instance variables
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    //Declaration of construction
    public CaesarCipher(int key){  //parameter
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    
    //Declaration of methods
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
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
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(encrypted);
    }
}
