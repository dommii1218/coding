import java.util.*;

public class MatchAllFilters implements Filter {
    private ArrayList<Filter> filters = new ArrayList<Filter>();
    
    public MatchAllFilters(){
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for (Filter f: filters){
            if (!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
    public String getName(){
        String ans = "";
        for (Filter f: filters){
            ans += (f.getName() + " ");
        }
        return ans;
    }
}
