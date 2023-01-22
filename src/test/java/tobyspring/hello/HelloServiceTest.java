package tobyspring.hello;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.METHOD)
//@UnitTest
//@interface FastUnitTest {}
//
//@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
//@Test
//@interface UnitTest {
//}

public class HelloServiceTest {

//    @FastUnitTest
    @Test
    void simpleHelloService() throws Exception {
        SimpleHelloService helloService = new SimpleHelloService();

        String rst = helloService.sayHello("Test");
        Assertions.assertThat(rst).isEqualTo("Hello Test");
    }

//    @UnitTest
    @Test
    void helloDecorator() throws Exception {
        HelloDecorator decorator = new HelloDecorator(name -> name);
        String rst = decorator.sayHello("Test");

        Assertions.assertThat(rst).isEqualTo("*Test*");
    }
}
