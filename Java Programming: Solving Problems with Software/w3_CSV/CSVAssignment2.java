import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVAssignment2 {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for (CSVRecord currRow: parser){
            coldestSoFar = getSmallestOfTwo(currRow, coldestSoFar);
        }
        return coldestSoFar;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Hottest temperature was " + coldest.get("TemperatureF")
         + " at " +  coldest.get("TimeEDT"));
    }
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        File smallestFile = null;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currRow = coldestHourInFile(fr.getCSVParser());
            if (smallestSoFar == null){
                smallestSoFar = currRow;
                smallestFile = f;
            }
            else{
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if (currTemp < largestTemp) {
                    smallestSoFar = currRow;
                    smallestFile = f;
                }
            }
        }
        return smallestFile.getName();
    }
    
    public void testFileWithColdestTemperature(){
        String coldestFileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + coldestFileName);
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord record: fr.getCSVParser()){
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
    }
    
    public CSVRecord getSmallestOfTwo(CSVRecord currRow, CSVRecord smallestSoFar){
        if (smallestSoFar == null){
            smallestSoFar = currRow;
        }
        else{
            if (!currRow.get("TemperatureF").equals("-9999")){
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if (currTemp < largestTemp) {
                    smallestSoFar = currRow;
                }
            }
        }
        return smallestSoFar;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for (CSVRecord currRow: parser){
            if (smallestSoFar == null){
                smallestSoFar = currRow;
            }
            else{
                if (!currRow.get("Humidity").equals("N/A")){
                    double currTemp = Double.parseDouble(currRow.get("Humidity"));
                    double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
                    if (currTemp < smallestTemp) {
                        smallestSoFar = currRow;
                    }
                }
                
            }
        }
        return smallestSoFar;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity")
         + " at " +  csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currRow = lowestHumidityInFile(fr.getCSVParser());
            if (smallestSoFar == null){
                smallestSoFar = currRow;
            }
            else{
                double currTemp = Double.parseDouble(currRow.get("Humidity"));
                double largestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
                if (currTemp < largestTemp) {
                    smallestSoFar = currRow;
                }
            }
        }
        return smallestSoFar;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity")
         + " at " +  csv.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sum = 0;
        int count = 0;
        for (CSVRecord record: parser){
            sum += Double.parseDouble(record.get("TemperatureF"));
            count ++;
        }
        return sum/count;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(fr.getCSVParser()));
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0;
        int count = 0;
        for (CSVRecord record: parser){
            if (Double.parseDouble(record.get("Humidity")) >= value){
                sum += Double.parseDouble(record.get("TemperatureF"));
                count ++;
            }
        }
        return sum/count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        double avg = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (Double.isNaN(avg)){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average Temp when high Humidity is " + avg);
        }
    }
}
