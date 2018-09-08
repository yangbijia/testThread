package test.test1;

public class Widget {

    public synchronized void doSomething(int args){
        System.out.println("owner :" + args);
    }
}
