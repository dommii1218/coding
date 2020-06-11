import edu.duke.*;
import java.util.*;

public class Tester {
    public void tester(){
        LogEntry le = new LogEntry("1.2.3.4", new Date(),
             "example request", 200, 500);
        System.out.println(le);
        
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(),
             "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log");
        la.printAll();
    }
    
    public void testUniqIp(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + "IPs");
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log");
        la.printAllHigherThanNum(100);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog-short_log");
        System.out.println("Sep 14");
        ArrayList<String> list = la.uniqueIPVisitsOnDay("Sep 14");
        for (String s: list){
             System.out.println(s);
        }
        System.out.println("=============================");
        
        System.out.println("Sep 30");
        ArrayList<String> list2 = la.uniqueIPVisitsOnDay("Sep 30");
        for (String s: list2){
             System.out.println(s);
        }
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log");
        int uniqueIPs = la.countUniqueIPsInRange(200, 299);
        System.out.println("There are " + uniqueIPs + "IPs in the range of 200 and 299.");
        int uniqueIPs2 = la.countUniqueIPsInRange(300, 399);
        System.out.println("There are " + uniqueIPs2 + "IPs in the range of 300 and 399.");
    }
    
    public void quiz1(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog1_log");
        la.printAllHigherThanNum(400);
        
        ArrayList<String> list = la.uniqueIPVisitsOnDay("Mar 24");
        System.out.println("There are " + list.size() + " unique IP visits on Mar 24");
        
        int uniqueIPs = la.countUniqueIPsInRange(300, 399);
        System.out.println("There are " + uniqueIPs + " IPs in the range.");
    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void quiz2(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog1_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int mostVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("Q1: "+mostVisits);
        
        System.out.println("Q2: ");
        ArrayList<String> ips = la.iPsMostVisits(counts);
        for (String s: ips){
            System.out.println(s);
        }
        
        HashMap<String,ArrayList<String>> iPsForDays = la.iPsForDays();
        String day = la.dayWithMostIPVisits(iPsForDays);
        System.out.println("Q3: "+day);
        
        System.out.println("Q4: ");
        ArrayList<String> list = la.iPsWithMostVisitsOnDay(iPsForDays, "Mar 17");
        for (String s: list){
            System.out.println(s);
        }
    }
    
    public void quiz3(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("Q4: There are " + uniqueIPs + " IPs");
        System.out.println("========================");
        
        ArrayList<String> list = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println("Q5: There are " + list.size() + " unique IP visits on Sep 24");
        System.out.println("========================");
        
        int uniqueIPsInRange = la.countUniqueIPsInRange(400, 499);
        System.out.println("Q6: There are " + uniqueIPsInRange + " IPs in the range.");
        System.out.println("========================");
        
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int mostVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("Q7: "+mostVisits);
        System.out.println("========================");
        
        System.out.print("Q8: ");
        ArrayList<String> ips = la.iPsMostVisits(counts);
        for (String s: ips){
            System.out.println(s);
        }
        System.out.println("========================");
        
        HashMap<String,ArrayList<String>> iPsForDays = la.iPsForDays();
        String day = la.dayWithMostIPVisits(iPsForDays);
        System.out.println("Q9: "+day);
        System.out.println("========================");
        
        System.out.print("Q10: ");
        ArrayList<String> list2 = la.iPsWithMostVisitsOnDay(iPsForDays, "Sep 30");
        for (String s: list2){
            System.out.println(s);
        }
    }
}
