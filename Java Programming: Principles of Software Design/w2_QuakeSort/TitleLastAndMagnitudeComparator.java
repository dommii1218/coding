import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2){
        String lastWord1 = qe1.getInfo().substring(qe1.getInfo().lastIndexOf(" ")+1);
        String lastWord2 = qe2.getInfo().substring(qe2.getInfo().lastIndexOf(" ")+1);
        if (lastWord1.equals(lastWord2)){
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }
        return lastWord1.compareTo(lastWord2);
    }
}
