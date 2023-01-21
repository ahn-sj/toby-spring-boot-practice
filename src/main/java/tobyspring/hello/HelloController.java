package tobyspring.hello;

import java.util.Objects;

public class HelloController {
    public String hello(String name) {
        SimpleHelloService helloService = new SimpleHelloService();

        // Null 인 경우 NPE 발생
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
