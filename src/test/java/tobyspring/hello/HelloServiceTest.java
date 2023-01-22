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
}
