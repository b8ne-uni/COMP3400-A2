import localhost.axis.HelloWorld_jws.*;

public class HelloWorldClient {
    public static void main(String[] args) {
        try {
            HelloWorldService hws = new HelloWorldServiceLocator();
            HelloWorld hw = hws.getHelloWorld();
            System.out.println(hw.sayHello());
        }
        catch (Exception e) {
            e.printStackTrace(); }
    }
}