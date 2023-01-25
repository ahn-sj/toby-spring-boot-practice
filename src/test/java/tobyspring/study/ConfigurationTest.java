package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest {

    @Test
    void configurationCommon() throws Exception {
        Common common = new Common();

//        assertThat(new Common()).isSameAs(new Common()); // false
        assertThat(common).isSameAs(common); // true
    }

    @Test
    @DisplayName("스프링 컨테이너를 사용하지 않을 경우에 두 객체는 일치하지 않는다.")
    void configurationNotUsedSpringContainer() throws Exception {
        MyConfig myConfig = new MyConfig();
        Bean1 bean1 = myConfig.bean1();
        Bean2 bean2 = myConfig.bean2();

        assertThat(bean1.common).isNotSameAs(bean2.common);
    }

    @Test
    @DisplayName("스프링 컨테이너를 사용할 경우 두 Common 객체는 일치한다.")
    void configurationUsedSpringContainer() throws Exception {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        assertThat(bean1.common).isSameAs(bean2.common);
    }

    @Test
    void proxyCommonMethod() throws Exception {
        // Configuration 애노테이션이 붙은 클래스는 기본적으로 Proxy를 만들어서 빈을 확장
        // @Configuration(proxyBeanMethods = true) // default
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    static class MyConfigProxy extends MyConfig {

        private Common common;

        @Override
        public Common common() {
            if(this.common == null) {
                this.common = super.common();
            }
            return common;
        }
    }

    @Configuration(proxyBeanMethods = true)
    static class MyConfig {
        @Bean
        public Common common() {
            return new Common();
        }

        @Bean
        public Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        public Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }
    static class Common {
    }
}
