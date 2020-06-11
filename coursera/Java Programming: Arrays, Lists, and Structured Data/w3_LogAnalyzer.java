import edu.duke.*;
import java.util.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }
    
    public void readFile(String filename){
        FileResource fr = new FileResource(filename);
        for (String s: fr.lines()){
            records.add(WebLogParser.parseEntry(s));
        }    
    }
    
    public int countUniqueIPs(){
        ArrayList<LogEntry> uniqueIPs = new ArrayList<LogEntry>();
        for (LogEntry le: records){
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)){   //check if two objects are exactly the same
                uniqueIPs.add(le);
            }
        }
        return uniqueIPs.size();
    }
    
    public void printAll(){
        for (LogEntry le: records){
            System.out.println(le);
        }
    }
    
    public void printAllHigherThanNum(int num){
        for (LogEntry le: records){
            if (le.getStatusCode() > num){
                System.out.println(le);
            }
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> list = new ArrayList<String>();
        for (LogEntry le: records){
            String date = le.getAccessTime().toString().substring(4,10);
            String ipAddress = le.getIpAddress();
            if (date.equals(someday) && (!list.contains(ipAddress))){
                list.add(ipAddress);
            }
        }
        return list;
    }
    
    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records){
            int statusCode = le.getStatusCode();
            String ipAddress = le.getIpAddress();
            if (low <= statusCode && statusCode <= high && !uniqueIPs.contains(ipAddress)){
                uniqueIPs.add(ipAddress);
            }
        }
        return uniqueIPs.size();
    }
    
    public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for (LogEntry le: records){
            String ipAddress = le.getIpAddress();
            if (!counts.containsKey(ipAddress)){
                counts.put(ipAddress,1);
            }
            else {
                counts.put(ipAddress,counts.get(ipAddress)+1);
            }
        }
        return counts;
    }
    
    public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
        int maxVisits = 0;
        for (String s: counts.keySet()){
            if (counts.get(s) > maxVisits){
                maxVisits = counts.get(s);
            }
        }
        return maxVisits;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
        int maxVisits = mostNumberVisitsByIP(counts);
        ArrayList<String> list = new ArrayList<String>();
        for (String s: counts.keySet()){
            if (counts.get(s) == maxVisits){
                list.add(s);
            }
        }
        return list;
    }
    
    public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
        for (LogEntry le: records){
            String date = le.getAccessTime().toString().substring(4,10);
            String ip = le.getIpAddress();
            if (!map.containsKey(date)){
                map.put(date,new ArrayList<String>());
            }
            map.get(date).add(ip);
        }
        return map;
    }
    
    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map){
        int maxVisits = 0;
        String maxVisitsDate = "";
        for (String date: map.keySet()){
            int visits = map.get(date).size();
            if (visits > maxVisits){
                maxVisits = visits;
                maxVisitsDate = date;
            }
        }
        return maxVisitsDate;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(
                 HashMap<String, ArrayList<String>> map, String date){
        ArrayList<String> iPsInDate = map.get(date);
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for (String s: iPsInDate){
            if (!counts.containsKey(s)){
                counts.put(s,1);
            }
            else {
                counts.put(s,counts.get(s)+1);
            }
        }
        return iPsMostVisits(counts);
    }
}
