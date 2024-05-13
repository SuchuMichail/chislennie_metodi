public class MyTuple {
    private double[] first;
    private double second;

    public MyTuple(double[] first, double second) {
        this.first = first;
        this.second = second;
    }

    public double[] getFirst() {
        return first;
    }

    public void setFirst(double[] first) {
        this.first = first;
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
    }
}
