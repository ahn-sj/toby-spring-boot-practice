package tobyspring.hello;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // TYPE = (target) class, interface, enum
@Retention(RetentionPolicy.RUNTIME)
@Configuration
@ComponentScan
public @interface MySpringBootAnnotation {
}
