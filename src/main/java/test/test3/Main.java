package test.test3;

import java.util.*;

/**
 * 1.枚举出9个点两两连成的线段
 * 2.枚举出两两线段的所有交点
 * 3.找出交点中的所有任意3个交点，判断每三个交点是否组成三角形
 * 4.三个点中任取两点连成的线段，其延长线和9个点有两个交点，则证明该三角形三边有连线，三点组成三角形
 *
 * @author ybj
 */
public class Main {



    public static void main(String[] args) {
        //初始9个点的坐标
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(1,1));
        points.add(new Point(1,2));
        points.add(new Point(2,1));
        points.add(new Point(2,2));
        points.add(new Point(5,1));
        points.add(new Point(6,1));
        points.add(new Point(7,7));
        points.add(new Point(8,12));
        points.add(new Point(9,23));

        //9个点两两连成的线段
        List<Segment> segments = getSegment(points);

        //两两线段的所有交点
        List<Point> intersections = getIntersection(segments);

        //找出交点中的所有任意3个交点
        double count = getTriangleCount(intersections, points);

        System.out.println("------------------count="+count);
    }

    /**
     * 9个点两两连成的线段
     * @param points
     * @return
     */
    public static List<Segment> getSegment(List<Point> points){
        List<Segment> segments = new ArrayList<Segment>();
        int len = points.size();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++){

                segments.add(new Segment(points.get(i),points.get(j)));
            }
        }
        return segments;
    }

    /**
     * 获取所有两两线段的交点，并生成邻接链表
     * @param segments
     * @return
     */
    public static Map<Point, List<Point>> getIntersection1(List<Segment> segments){
        List<Point> intersections = new ArrayList<Point>();
        int len = segments.size();
/*        int i, j, k,count=0;
        for (i = 0; i < len; i++) {
            for (j = i+1; j < len; j++) {
                for (k = j+1; k < len; k++) {
                    if (isTriangle(segments.get(i), segments.get(j), segments.get(k)))
                        count++;
                }
            }
        }*/
        Map<Point,List<Point>> map = new HashMap<Point, List<Point>>();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++){
                Segment ab = segments.get(i);
                Segment cd = segments.get(j);

                Point point = getOneIntersection(ab, cd);
                if (point != null){
                    if(map.containsKey(point)){
                        List<Point> ps = map.get(point);
                        if(ps.contains(ab.getA())){
                            ps.add(point);
                        }
                        if(ps.contains(ab.getB())){
                            ps.add(point);
                        }
                        if(ps.contains(cd.getA())){
                            ps.add(point);
                        }
                        if(ps.contains(cd.getB())){
                            ps.add(point);
                        }
                    }
                    if(!intersections.contains(point)){
                        intersections.add(point);
                    }

                }
            }
        }
        List<Point> intersectionsUnique = removeRepeat(intersections);
        return map;
    }


    /**
     * 获取所有两两线段的交点，并生成邻接链表
     * @param segments
     * @return
     */
    public static List<Point> getIntersection(List<Segment> segments){
        List<Point> intersections = new ArrayList<Point>();
        int len = segments.size();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++){
                Segment ab = segments.get(i);
                Segment cd = segments.get(j);

                Point point = getOneIntersection(ab, cd);
                if (point != null){
                    if(!intersections.contains(point)){
                        intersections.add(point);
                    }

                }
            }
        }
        List<Point> intersectionsUnique = removeRepeat(intersections);
        return intersectionsUnique;
    }

    public static List<Point> removeRepeat(List<Point> points){
        List<Point> intersections = new ArrayList<Point>();
        int len = points.size();
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < len; i++) {
            String p = points.get(i).toString();
            if(!set.contains(p)){
                set.add(p);
                System.out.println(p);
                intersections.add(points.get(i));
            }
        }
        //List<Point> intersectionsUnique = removeRepeat(intersections);
        return intersections;
    }



    /**
     * 获取两条线段的交点
     * @param ab
     * @param cd
     * @return
     */
    public static Point getOneIntersection(Segment ab, Segment cd){
        double x =0;
        double y = 0;
        double x1=ab.getA().getX();
        double y1=ab.getA().getY();
        double x2=ab.getB().getX();
        double y2=ab.getB().getY();
        double x3=cd.getA().getX();
        double y3=cd.getA().getY();
        double x4=cd.getB().getX();
        double y4=cd.getB().getY();
        double k1=Double.MAX_VALUE;
        double k2=Double.MAX_VALUE;
        boolean flag1=false;
        boolean flag2=false;

        if((x1-x2)==0)
            flag1=true;
        if((x3-x4)==0)
            flag2=true;

        if(!flag1)
            k1=(y1-y2)/(x1-x2);
        if(!flag2)
            k2=(y3-y4)/(x3-x4);

        if(k1==k2)
            return null;

        if(flag1){
            if(flag2)
                return null;
            x=x1;
            if(k2==0){
                y=y3;
            }else{
                y=k2*(x-x4)+y4;
            }
        }else if(flag2){
            x=x3;
            if(k1==0){
                y=y1;
            }else{
                y=k1*(x-x2)+y2;
            }
        }else{
            if(k1==0){
                y=y1;
                x=(y-y4)/k2+x4;
            }else if(k2==0){
                y=y3;
                x=(y-y2)/k1+x2;
            }else{
                x=(k1*x2-k2*x4+y4-y2)/(k1-k2);
                y=k1*(x-x2)+y2;
            }
        }
        if(between(x1,x2,x)&&between(y1,y2,y)&&between(x3,x4,x)&&between(y3,y4,y)){
            Point point=new Point();
            point.setX(x);
            point.setY(y);
            if(point.equals(ab.getA())||point.equals(ab.getB()))
                return null;
            return point;
        }else{
            return null;
        }

    }

    public static boolean between(double a,double b,double target){
        if(target>=a-0.01&&target<=b+0.01||target<=a+0.01&&target>=b-0.01)
            return true;
        else
            return false;
    }

    /**
     * 找出交点中的所有任意3个交点，判断每三个交点是否组成三角形
     * @param points
     * @return
     */
    public static double getTriangleCount(List<Point> intersections, List<Point> points){
        double trianglesCount = 0;
        List<List<Point>> trangles =  getSubset(intersections);
        int len = trangles.size();
        for (int i = 0; i < len; i++) {
            List<Point> trangle = trangles.get(i);
            boolean res = judgeTriangle(trangle.get(0), trangle.get(1), trangle.get(2), points);
            //System.out.println("["+res+"]"+trangle.get(0).toString() + trangle.get(1).toString() + trangle.get(2).toString());
            if(res){
                trianglesCount++;
            }
        }
        return trianglesCount;
    }

    /**
     * 求三元子集
     * @param points
     * @return
     */
    public static List<List<Point>> getSubset(List<Point> points) {
        if (points.size() > 0) {
            ArrayList<List<Point>> result = new ArrayList<List<Point>>();
            for (int i = 0; i < Math.pow(2, points.size()); i++) {
                List<Point> subList = new ArrayList<Point>();
                int index = i;
                for (int j = 0; j < points.size(); j++) {
                    if ((index & 1) == 1) {
                        subList.add(points.get(j));
                        if(subList.size() >= 3){
                            break;
                        }
                    }
                    index >>= 1;
                }
                if(subList.size() == 3){
                    result.add(subList);
                }
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     * 判断三点连线是否组成三角形，以及三点之间是否存在边
     * @param a
     * @param b
     * @param c
     * @param points
     * @return
     */
    public static boolean judgeTriangle(Point a, Point b, Point c, List<Point> points){
        boolean isTriangle_ab = false,isTriangle_ac = false,isTriangle_bc = false;
        double kab = (a.getX() - b.getX()) == 0 ? 1 : (a.getY() - b.getY())/(a.getX() - b.getX());
        double kac = (a.getX() - c.getX()) == 0 ? 1 : (a.getY() - c.getY())/(a.getX() - c.getX());
        double kbc = (b.getX() - c.getX()) == 0 ? 1 : (b.getY() - c.getY())/(b.getX() - c.getX());
        System.out.println(kab+","+kac+","+kbc);
        for (int i = 0; i < points.size(); i++){
            Point p = points.get(i);
            double kap = (a.getX() - p.getX()) == 0 ? 1 : (a.getY() - p.getY())/(a.getX() - p.getX());
            if(Math.abs(kab) == Math.abs(kap)){
                isTriangle_ab = true;
            }
        }
        for (int i = 0; i < points.size(); i++){
            Point p = points.get(i);
            double kap = (a.getX() - p.getX()) == 0 ? 1 : (a.getY() - p.getY())/(a.getX() - p.getX());
            if(Math.abs(kac) == Math.abs(kap)){
                isTriangle_ac = true;
            }
        }
        for (int i = 0; i < points.size(); i++){
            Point p = points.get(i);
            double kbp = (b.getX() - p.getX()) == 0 ? 1 : (b.getY() - p.getY())/(b.getX() - p.getX());
            if(Math.abs(kbc) == Math.abs(kbp)){
                isTriangle_bc = true;
            }
        }

        if(isTriangle_ab && isTriangle_ac && isTriangle_bc && (kab != kac || kab != kbc || kbc != kac)){
            return true;
        }else {
            return false;
        }

    }


    /**
     * 判断三点连线是否组成三角形，以及三点之间是否存在边
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static boolean isTriangle(Point a, Point b, Point c){
        boolean isTriangle_ab = false,isTriangle_ac = false,isTriangle_bc = false;
        double kab = (a.getX() - b.getX()) == 0 ? 1 : (a.getY() - b.getY())/(a.getX() - b.getX());
        double kac = (a.getX() - c.getX()) == 0 ? 1 : (a.getY() - c.getY())/(a.getX() - c.getX());
        double kbc = (b.getX() - c.getX()) == 0 ? 1 : (b.getY() - c.getY())/(b.getX() - c.getX());
        System.out.println(kab+","+kac+","+kbc);

        if((kab != kac || kab != kbc || kbc != kac)){
            return true;
        }else {
            return false;
        }

    }
}
