import edu.duke.*;

/**
 * Write a description of MarkovRunnerWithInterface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovRunnerWithInterface {
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWordOne m1 = new MarkovWordOne();
        m1.setTraining(st);
        //markov.setRandom(38);
        printOut(m1.getRandomText(500));
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
        for (int k=0; k<3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void printRunModel(){
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, 800);
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
    
    public void testPrintHashMapInfo(){
        FileResource fr = new FileResource("data/romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        EfficientMarkovModel markov = new EfficientMarkovModel(5);
        markov.setTraining(st);
        markov.setRandom(615);
        System.out.println(markov.getRandomText(50));
        markov.printHashMapInfo();
    }
    
    public void compareMethod() {
        FileResource fr = new FileResource("data/melville.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        double begin = System.nanoTime();
        EfficientMarkovModel markov = new EfficientMarkovModel(2);
        markov.setTraining(st);
        markov.setRandom(42);
        for (int k=0; k<3; k++){
            markov.getRandomText(1000);
        }
        double end = System.nanoTime();
        double stime = (end-begin)/1e9;
        begin = System.nanoTime();
        
        MarkovModel mm = new MarkovModel(2);
        mm.setTraining(st);
        mm.setRandom(42);
        for (int k=0; k<3; k++){
            mm.getRandomText(1000);
        }
        end = System.nanoTime();
        double ttime = (end-begin)/1e9;
        System.out.printf("Try %d times \t Efficient: %3.2f \t Normal: %3.2f \n ",3,stime,ttime);
    }
    
    public void quiz5(){
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovModel m2 = new EfficientMarkovModel(6);
        m2.setTraining(st);
        m2.setRandom(792);
        m2.getRandomText(100);
        m2.printHashMapInfo();
        
        m2 = new EfficientMarkovModel(5);
        m2.setTraining(st);
        m2.setRandom(531);
        m2.getRandomText(100);
        m2.printHashMapInfo();
    }
}
