public class Part3 {
    public static boolean twoOccurrences(String a, String b){
        int count = 0;
        int i = 0;
        while(i != -1){
            i = b.indexOf(a, i);
            if (i != -1){
                count ++;
                i ++;;
            }
        }
        if (count >= 2){return true;}
        else {return false;}
    }
    
    public static String lastPart(String a, String b){
        int i = b.indexOf(a);
        if (i == -1){return b;}
        else{return b.substring(i + a.length());}
    }
    
    public static void main(String args[]){
        System.out.println(twoOccurrences("atg", "ctgtatgtatg"));
        System.out.println(twoOccurrences("a", "banana"));
        
        System.out.println(lastPart("an", "an"));
        System.out.println(lastPart("zoo", "forest")); 
    }

}
