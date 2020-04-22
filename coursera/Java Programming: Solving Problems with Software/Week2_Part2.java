public class Part2 {
    public String findGeneSimple(String dna, String startCodon, String stopCodon){
        String result = "";
        String DNA = dna.toUpperCase();
        int startIndex = DNA.indexOf(startCodon);
        if (startIndex == -1){return "";} 
        int stopIndex = DNA.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1){return "";} 
        result = dna.substring(startIndex, stopIndex + 3);
        if (result.length() % 3 == 0){return result;}
        else {return "";}
    }
    
    public void testFindGeneSimple(){
        String dna = "gatgctataat";
        System.out.println("DNA strand is " + dna);
        String gene = findGeneSimple(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
    }

}
