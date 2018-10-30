package test;

import java.util.HashMap;
import java.util.Map;

public class Main2 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("a","1");
        String a1 = "1";
        String a2 = new String("1");
        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());
    }
}
