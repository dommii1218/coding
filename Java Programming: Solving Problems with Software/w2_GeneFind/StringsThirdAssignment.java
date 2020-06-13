import edu.duke.*;

public class Part1 {
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
    
    public void testFindStopCodon(){
        //            01234567890123456789012345
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna,0,"TAA");
        if (dex!= 9) System.out.println("error on 9");
        dex = findStopCodon(dna,9,"TAA");
        if (dex!= 21) System.out.println("error on 21");
        dex = findStopCodon(dna,1,"TAA");
        if (dex!= -1) System.out.println("error on 26");
        dex = findStopCodon(dna,0,"TAG");
        if (dex!= -1) System.out.println("error on 26 TAG");
        System.out.println("tests finished");
        
    }
    
    public void testFindGene(){
        String dna = "ATGCCCGGGAAATAACCC";
        String gene = findGene(dna);
        if (! gene.equals("ATGCCCGGGAAATAA")){
            System.out.println("error");
        }
        System.out.println("test finished");
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        while (true) {
            String currGene = findGene(dna);
            if (currGene.isEmpty()){break;}
            geneList.add(currGene);
            int startIndex = dna.indexOf(currGene) + currGene.length();
            dna = dna.substring(startIndex);
        }
        return geneList;
    }
    
    public float cgRatio(String dna){
        dna = dna.toLowerCase();
        int cCount = 0;
        int gCount = 0;
        int startIndex = 0;
        while (dna.indexOf("c", startIndex) != -1){
            cCount ++;
            startIndex = dna.indexOf("c", startIndex) + 1; 
        }
        startIndex = 0;
        while (dna.indexOf("g", startIndex) != -1){
            gCount ++;
            startIndex = dna.indexOf("g", startIndex) + 1; 
        }
        return (float)(cCount + gCount) / dna.length();
    }
    
    public void testProcessGenes(StorageResource sr){
        
        StorageResource geneList1 = new StorageResource();
        StorageResource geneList2 = new StorageResource();
        int longestLen = 0;
        for (String g:sr.data()){
            if (g.length() > 9){
                geneList1.add(g);
            }
            if (cgRatio(g) > 0.35){
                geneList2.add(g);
            }
            if (g.length() > longestLen){
                longestLen = g.length();
            }
        }
        System.out.println("Strings longer than 9 characters:");
        for (String g:geneList1.data()){
            System.out.println(g);
        }
        System.out.println("number of Strings longer than 9 characters:" + geneList1.size());
        System.out.println("Strings cgRatio less than 0.35:");
        for (String g:geneList2.data()){
            System.out.println(g);
        }
        System.out.println("number of Strings cgRatio less than 0.35: " + geneList2.size());
        System.out.println("Longest length: " + longestLen);
    }
    
    public void test(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        StorageResource sr = getAllGenes(dna);
        testProcessGenes(sr);
    }

}
