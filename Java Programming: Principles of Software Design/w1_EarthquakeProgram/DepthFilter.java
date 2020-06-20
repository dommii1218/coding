public class DepthFilter implements Filter {
    private double min;
    private double max;
    public DepthFilter(double min, double max){
        this.min = min;
        this.max = max;
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getDepth() >= min && qe.getDepth() <= max;
    }
    public String getName(){
        return "DepthFilter";
    }
}
