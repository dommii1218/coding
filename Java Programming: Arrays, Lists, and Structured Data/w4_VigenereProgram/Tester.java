import edu.duke.*;

public class Tester {
    public void testCaesarCipher(){
        FileResource fr = new FileResource("data/titus-small.txt");
        String message1 = fr.asString();
        String message2 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println("message is " + message2);
        int key = 15;
        CaesarCipher cc = new CaesarCipher(key);
        String encrypted = cc.encrypt(message2);
        System.out.println("key is " + key + "\n" + "encrypted message is " + encrypted);
        System.out.println("decrypted message is " + cc.decrypt(encrypted));
        System.out.println("==================================");
    }
    
    public void testCaesarCracker(){
        FileResource fr = new FileResource("data/titus-small_key5.txt");
        String message1 = fr.asString();
        CaesarCracker cracker = new CaesarCracker();
        System.out.println("decrypted message is " + cracker.decrypt(message1));
        System.out.println("==================================");
        
        FileResource fr2 = new FileResource("data/oslusiadas_key17.txt");
        String message2 = fr2.asString();
        CaesarCracker cracker2 = new CaesarCracker('a');
        System.out.println("decrypted message is " + cracker2.decrypt(message2));
    }
    
    public void testVigenereCipher(){
        FileResource fr = new FileResource("data/titus-small.txt");
        String message = fr.asString();
        VigenereCipher vc = new VigenereCipher(new int[]{17, 14, 12, 4}) ;
        System.out.println("encrypted message is " + vc.encrypt(message));
    }

}
