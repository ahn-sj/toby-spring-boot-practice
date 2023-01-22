package tobyspring.hello;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloControllerTest {
    @Test
    void helloController() throws Exception {
        HelloController helloController = new HelloController(name -> name);
        String rst = helloController.hello("Test");

        assertThat(rst).isEqualTo("Test");
    }

    @Test
    void failsHelloController() throws Exception {
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
