package test.test3;

public class Segment {
    private Point a;
    private Point b;

    public Segment() {
    }

    public Segment(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }
}
