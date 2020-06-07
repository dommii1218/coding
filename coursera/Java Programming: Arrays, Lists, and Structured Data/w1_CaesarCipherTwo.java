public class CaesarCipherTwo {   
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private CaesarCipher cc1;
    private CaesarCipher cc2;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        cc1 = new CaesarCipher(key1);
        cc2 = new CaesarCipher(key2);
    }
    
    public String encrypt(String input){
        String s1 = halfOfString(input, 0);
        String s2 = halfOfString(input, 1);
        String ds1 = cc1.encrypt(s1);
        String ds2 = cc2.encrypt(s2);
        
        StringBuilder sb = new StringBuilder(input);
        for (int k=0; k<input.length(); k++){
            if (k % 2 == 0){
                sb.setCharAt(k, ds1.charAt(k/2));
            }
            else {
                sb.setCharAt(k, ds2.charAt(k/2));
            }
        }
        return sb.toString();
    }
    
    private String halfOfString(String message, int start){
        String answer = "";
        for (int k=start; k<message.length(); k+=2){
            answer += message.charAt(k);
        }
        return answer;
    }
    
    public String decrypt(String encrypted){
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        String ds1 = cc1.decrypt(s1);
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
}
