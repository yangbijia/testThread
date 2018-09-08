package test.test1;

public class LoggingWidget extends Widget {
    @Override
    public synchronized void doSomething(int args) {
        super.doSomething(args);
        System.out.println(toString() + ": calling doSomething -- " + args);

    }
}
