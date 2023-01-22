package tobyspring.hello;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    @Test
    void simpleHelloService() throws Exception {
        SimpleHelloService helloService = new SimpleHelloService();

        String rst = helloService.sayHello("Test");
        Assertions.assertThat(rst).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator() throws Exception {
        HelloDecorator decorator = new HelloDecorator(name -> name);
        String rst = decorator.sayHello("Test");

        Assertions.assertThat(rst).isEqualTo("*Test*");
    }
}
