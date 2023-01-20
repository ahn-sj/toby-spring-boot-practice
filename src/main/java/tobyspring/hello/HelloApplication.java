package tobyspring.hello;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
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
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("frontcontroller", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					// ====== 요청 ======
					// GET으로 오는 /hello의 URL만 가능
					if (request.getRequestURI().equals("/hello") && request.getMethod().equals(HttpMethod.GET.name())) {
						String name = request.getParameter("name");

						// ====== 응답 ======
						// 상태 라인, 헤더, HTTP Body
						response.setStatus(HttpStatus.OK.value()); // OK -> 200
						response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						response.getWriter().println("Hello " + name);
					} else if (request.getRequestURI().equals("/user")) {
						//
					} else {
						response.setStatus(HttpStatus.NOT_FOUND.value());
					}

				}
			}).addMapping("/*"); // /hello의 요청이오면 위 service() 메서드가 실행
			// 모든 요청을 front controller 가 받도록 변경
		});
		webServer.start(); // 톱캣 웹 서버를 실행
	}
}
