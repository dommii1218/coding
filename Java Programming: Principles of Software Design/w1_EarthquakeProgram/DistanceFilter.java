public class DistanceFilter implements Filter {
    private Location loc;
    private double max;
    public DistanceFilter(Location loc, double max){
        this.loc = loc;
        this.max = max;
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(loc) < max;
    }
    public String getName(){
        return "DistanceFilter";
    }
}
