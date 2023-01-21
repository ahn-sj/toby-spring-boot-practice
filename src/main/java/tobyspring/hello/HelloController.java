package tobyspring.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequestMapping
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(String name) {
//        SimpleHelloService helloService = new SimpleHelloService();

        // Null 인 경우 NPE 발생
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
