import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2){
        if (qe1.getInfo().equals(qe2.getInfo())){
            return Double.compare(qe1.getDepth(), qe2.getDepth());
        }
        return qe1.getInfo().compareTo(qe2.getInfo());
    }
}
