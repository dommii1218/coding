import edu.duke.*;

public class Part4 {
    public static void main(String args[]){
        URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String s: url.words()){
            int i = s.indexOf("youtube.com");
            if (i != -1){
                String sLower = s.toLowerCase();
                int startIndex = sLower.lastIndexOf("\"", i);
                int stopIndex = sLower.indexOf("\"",startIndex + 1);
                System.out.println(s.substring(startIndex + 1, stopIndex));
            }
        }
    }

}
