public class Part2 {
    public int howMany(String a, String b){
        if (b.indexOf(a) == -1) {return 0;}
        int count = 0;
        int startIndex = 0;
        while (b.indexOf(a, startIndex) != -1) {
            count ++;
            startIndex = b.indexOf(a, startIndex) + a.length();
        }
        return count;
    }
    
    public void testHowMany(){
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
    }
}
