package test.test3;

public class Point {
    private double x;
    private double y;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public boolean equals(Object obj){

        if(this == obj)//判断是否是本类的一个引用

            return true;

        if(obj == null)//

            return false;

        Point point = (Point)obj;

        if(this.x != point.x)

            return false;

        if(this.y != point.y)

            return false;

        return true;

    }


    public int hashCode(){

        int result = 17;

        result = (int)Math.round(result * 31 + x);

        result = (int)Math.round(result * 31 + y);

        return result;

    }
}
