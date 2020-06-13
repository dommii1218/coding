import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames(){
        FileResource fr = new FileResource();
        for (CSVRecord rec: fr.getCSVParser(false)){//not have a header row
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100){
                System.out.println("Name " + rec.get(0) + 
                                   " Gender " + rec.get(1) +
                                   " Num Born " + rec.get(2));
            }
        }
    }
    
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalNames = 0;
        int totalBoys = 0;
        int totalBoyNames = 0;
        int totalGirls = 0;
        int totalGirlNames = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames ++;
            if (rec.get(1).equals("M")){
                totalBoys += numBorn;
                totalBoyNames ++;
            }
            else{
                totalGirls += numBorn;
                totalGirlNames ++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total number of names = " + totalNames);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total number of girl names = " + totalGirlNames);
        System.out.println("total boys = " + totalBoys); 
        System.out.println("total number of boy names = " + totalBoyNames);
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + Integer.toString(year) + ".csv");
        int rank = 1;
        for (CSVRecord rec: fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                if (rec.get(0).equals(name)){
                    return rank;
                }
                else {rank ++;}
            }
        }
        return -1;
    }
    
    public void testGetRank(){
        System.out.println(getRank(1960, "Emily", "F"));
        System.out.println(getRank(1971, "Frank", "M"));
    }
    
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + Integer.toString(year) + ".csv");
        int count = 1;
        for (CSVRecord rec: fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                if (count == rank){
                    return rec.get(0);
                }
                else {count ++;}
            }
        }
        return "NO NAME";
    }
    
    public void testGetName(){
        System.out.println(getName(1980, 350, "F"));
        System.out.println(getName(1982, 450, "M"));
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String sheHe= null;
        if (gender.equals("M")) {sheHe = "he";}
        else {sheHe = "she";}
        System.out.println(name + " born in " + year + " would be " + 
            getName(newYear, rank, gender) +  " if " + sheHe + " was born in " +
            newYear + ".");
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Susan", 1972, 2014, "F");
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        Integer yearOfHighestRank = null;
        Integer highestRank = null;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int currRank = 1;
            int yearOfFile = Integer.parseInt(f.getName().substring(3,7));
            for (CSVRecord rec: parser){
                if (rec.get(1).equals(gender)){
                    if (rec.get(0).equals(name) && 
                      (highestRank == null || currRank < highestRank)){
                        highestRank = currRank;
                        yearOfHighestRank = yearOfFile;
                        break;
                    }
                    else {currRank ++;}
                }
            }
        }
        if (highestRank == null) {return -1;}
        else {return yearOfHighestRank;}
    }
    
    public void testYearOfHighestRank(){
        System.out.println(yearOfHighestRank("Genevieve", "F"));
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int sum = 0;
        int count = 0;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int currRank = 1;
            for (CSVRecord rec: parser){
                if (rec.get(1).equals(gender)){
                    if (rec.get(0).equals(name)){
                        sum += currRank;
                        count ++;
                        break;
                    }
                    else {currRank ++;}
                }
            }
        }
        if (sum == 0){return -1;}
        else {return (double)sum/count;}
    }
    
    public void testGetAverageRank(){
        System.out.println(getAverageRank("Susan", "F"));
        System.out.println(getAverageRank("Robert", "M"));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + Integer.toString(year) + ".csv");
        int totalBirthsRankedHigher = 0;
        for (CSVRecord rec: fr.getCSVParser()){
            if (rec.get(1).equals(gender)){
                if (rec.get(0).equals(name)){break;}
                else {totalBirthsRankedHigher += Integer.parseInt(rec.get(2));}
            }
        }
        return totalBirthsRankedHigher;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        System.out.println(getTotalBirthsRankedHigher(1990, "Emily", "F"));
        System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}
