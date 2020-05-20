import edu.duke.*;

public class Part4 {
    public static void main(String args[]){
        URLResource url = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String s: url.words()){
            String sLower = s.toLowerCase();
            int i = sLower.indexOf("youtube.com");
            if (i != -1){
                int startIndex = sLower.lastIndexOf("\"", i);
                int stopIndex = sLower.indexOf("\"",startIndex + 1);
                System.out.println(s.substring(startIndex + 1, stopIndex));
            }
        }
    }

}
