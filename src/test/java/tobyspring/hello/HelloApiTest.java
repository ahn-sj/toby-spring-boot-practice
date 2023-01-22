package tobyspring.hello;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import static org.assertj.core.api.Assertions.assertThat;

public class HelloApiTest {

    @Test
    void helloApi() {
        // http localhost:8080/hello?name=Spring
        TestRestTemplate rest = new TestRestTemplate();
        ResponseEntity<String> rst =
                rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

//        assertThat(rst.getHeaders().getContentType()).isEqualTo(MediaType.TEXT_PLAIN_VALUE); // text/plain;charset=ISO-8859-1

        // status code >> 200
        // header(Content-Type) >> text/plain
        // body >> Hello Spring
        assertThat(rst.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(rst.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        assertThat(rst.getBody()).isEqualTo("Hello Spring");
    }

    @Test
    void failsHelloApi() {
        // http localhost:8080/hello?name=
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> rst =
                rest.getForEntity("http://localhost:8080/hello?name=", String.class);

        assertThat(rst.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
