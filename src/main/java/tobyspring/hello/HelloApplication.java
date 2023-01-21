package tobyspring.hello;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloApplication {
	public static void main(String[] args) {
		GenericApplicationContext applicationContext = new GenericApplicationContext(); // Spring Container
		applicationContext.registerBean(HelloController.class); // 빈 등록
		applicationContext.refresh();

		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					// 인증, 보안, 다국어, 공통 기능

					// ====== 요청 ======
					// GET으로 오는 /hello의 URL만 가능
					if (request.getRequestURI().equals("/hello") && request.getMethod().equals(HttpMethod.GET.name())) {
						String name = request.getParameter("name");

						// 스프링 컨테이너가 가지는 helloController 가져오기
						HelloController helloController = applicationContext.getBean(HelloController.class);
						String rst = helloController.hello(name);

						// ====== 응답 ======
						// 상태 라인, 헤더, HTTP Body
//						response.setStatus(HttpStatus.OK.value()); // DEFAULT
//						response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						response.setContentType(MediaType.TEXT_PLAIN_VALUE);
						response.getWriter().println(rst);
					}
					 else {
						response.setStatus(HttpStatus.NOT_FOUND.value());
					}

				}
			}).addMapping("/*"); // /hello의 요청이오면 위 service() 메서드가 실행
			// 모든 요청을 front controller 가 받도록 변경
		});
		webServer.start(); // 톱캣 웹 서버를 실행
	}
}
