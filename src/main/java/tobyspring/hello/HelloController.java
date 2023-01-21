package tobyspring.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController {
    private final HelloService helloService;
    private final ApplicationContext applicationContext;

    public HelloController(HelloService helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;
        this.applicationContext = applicationContext;

        System.out.println("applicationContext = " + applicationContext);
    }

    @GetMapping("/hello")
    public String hello(String name) {
//        SimpleHelloService helloService = new SimpleHelloService();

        // Null 인 경우 NPE 발생
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
