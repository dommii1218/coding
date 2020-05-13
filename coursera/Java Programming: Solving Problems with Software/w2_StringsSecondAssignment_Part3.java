public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1){
            if ((currIndex - startIndex) % 3 == 0){
                return currIndex;
            }
            else {currIndex = dna.indexOf(stopCodon, currIndex + 1);}
        }
        return -1;
    }
    
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }
        else{
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void printAllGenes(String dna){
        while (true) {
            String currGene = findGene(dna);
            if (currGene.isEmpty()){break;}
            System.out.println(currGene);
            int startIndex = dna.indexOf(currGene) + currGene.length();
            dna = dna.substring(startIndex);
        }
    }
    
    public int countGenes(String dna){
        int count = 0;
        while (true) {
            String currGene = findGene(dna);
            if (currGene.isEmpty()){break;}
            else {count ++;}
            int startIndex = dna.indexOf(currGene) + currGene.length();
            dna = dna.substring(startIndex);
        }
        return count;
    }
    
    public void testCountGenes(){
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    }

}
