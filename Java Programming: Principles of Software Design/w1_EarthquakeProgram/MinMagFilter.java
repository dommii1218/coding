public class MinMagFilter implements Filter {
    private double min;
    public MinMagFilter (double min){
        this.min = min;
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude() >= min;
    }
    public String getName(){
        return "MinMagFilter";
    }
}
