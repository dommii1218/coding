import edu.duke.*;

/**
 * Write a description of MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovRunner {
    public void runMarkovZero() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovModel markov = new MarkovModel(6);
        markov.setTraining(st);
        markov.setRandom(38);
        System.out.println(markov.getRandomText(500));
        //for(int k=0; k < 3; k++){
        //    String text = markov.getRandomText(500);
        //    printOut(text);
        //}
    }
    
    public void testGetFollows(){
        //String st = "this is a test yes this is a test.";
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        System.out.println("t: "+markov.getFollows("t").size());
        //System.out.println("e: "+markov.getFollows("e"));
        //System.out.println("es: "+markov.getFollows("es"));
        //System.out.println(".: "+markov.getFollows("."));
        //System.out.println("t.: "+markov.getFollows("t."));
    }
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
    public void runModel(IMarkovModel markov, String text, int size){
        markov.setTraining(text);
        System.out.println("running with "+markov);
        printOut(markov.getRandomText(size));
    }
    
    public void runModel(IMarkovModel markov, String text, int size, int seed){
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with "+markov+", seed "+seed);
        printOut(markov.getRandomText(size));
    }
    
    public void runMarkov(){
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        runModel(new EfficientMarkovWord(3), st, 120, 42);
    }
    
    public void testPrintHashMapInfo(){
        FileResource fr = new FileResource("data/hawthorne.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "this is a test yes this is really a test";
        EfficientMarkovWord markov = new EfficientMarkovWord(2);
        markov.setTraining(st);
        markov.setRandom(42);
        System.out.println(markov.getRandomText(100));
        markov.printHashMapInfo();
    }
    
    public void quiz1(){
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        markov.setTraining(st);
        markov.setRandom(88);
        System.out.println("Q2: "+markov.getRandomText(100));
        
        fr = new FileResource("data/melville.txt");
        st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov1 = new MarkovOne();
        markov1.setTraining(st);
        System.out.println("Q3: o: "+markov1.getFollows("o").size());
        System.out.println("Q4: th: "+markov1.getFollows("th").size());
        
        fr = new FileResource("data/confucius.txt");
        st = fr.asString();
        st = st.replace('\n', ' ');
        markov1.setTraining(st);
        markov1.setRandom(273);
        System.out.println("Q5: "+markov1.getRandomText(100));
        
        MarkovFour markov4 = new MarkovFour();
        markov4.setTraining(st);
        markov4.setRandom(371);
        System.out.println("Q6: "+markov4.getRandomText(100));
        
        MarkovModel markov8 = new MarkovModel(8);
        markov8.setTraining(st);
        markov8.setRandom(365);
        System.out.println("Q7: "+markov8.getRandomText(500));
    }
    
    public void quiz3(){
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        runModel(new MarkovWordOne(), st, 120, 139);
        runModel(new MarkovWordTwo(), st, 120, 832);
    }
    
    public void quiz4(){
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        runModel(new EfficientMarkovWord(3), st, 120, 621);
        runModel(new EfficientMarkovWord(5), st, 120, 844);
        
        EfficientMarkovWord markov = new EfficientMarkovWord(3);
        markov.setTraining(st);
        markov.setRandom(371);
        markov.getRandomText(100);
        markov.printHashMapInfo();
        
        markov = new EfficientMarkovWord(2);
        markov.setTraining(st);
        markov.setRandom(65);
        markov.getRandomText(100);
        markov.printHashMapInfo();
    }
    
    public void quiz5(){
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        runModel(new MarkovZero(), st, 120, 1024);
        
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        System.out.println("o: "+markov.getFollows("o").size());
        System.out.println("he: "+markov.getFollows("he").size());
        
        fr = new FileResource("data/romeo.txt");
        st = fr.asString();
        st = st.replace('\n', ' ');
        runModel(new MarkovOne(), st, 120, 365);
        runModel(new MarkovFour(), st, 120, 715);
        runModel(new MarkovModel(7), st, 120, 953);
    }
}
