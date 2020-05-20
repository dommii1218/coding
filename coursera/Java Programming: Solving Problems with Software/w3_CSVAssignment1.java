import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVAssignment1 {
    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord record: parser){
            String countryinfo = record.get("Country");
            if (countryinfo.contains(country)){
                String exportsInfo = record.get("Exports");
                String valueInfo = record.get("Value (dollars)");
                return countryinfo + ": " + exportsInfo + ": " + valueInfo;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, 
    String exportItem1, String exportItem2){
        for (CSVRecord record: parser){
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record: parser){
            String export = record.get("Exports");
            if (export.contains(exportItem)){
                count ++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record: parser){
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()){
                String country = record.get("Country");
                System.out.println(country + " " + value);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Country Info: " + countryInfo(parser, "Nauru"));
        
        parser = fr.getCSVParser();
        System.out.println("Two Products Exporters: ");
        listExportersTwoProducts(parser, "gold", "diamonds");
        
        parser = fr.getCSVParser();
        System.out.println("Number of Exporters： " + numberOfExporters(parser, "sugar"));
        
        parser = fr.getCSVParser();
        System.out.println("Big Exporters: ");
        bigExporters(parser, "$999,999,999,999");
    }

}
