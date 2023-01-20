package tobyspring.hello;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloApplication {
	public static void main(String[] args) {
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
					// 상태 라인, 헤더, HTTP Body
					response.setStatus(200);
					response.setHeader("Content-Type", "text/plain");
					response.getWriter().println("Hello Servlet");
				}
			}).addMapping("/hello"); // /hello의 요청이오면 위 service() 메서드가 실행된다
		});
		webServer.start(); // 톱캣 웹 서버를 실행
	}
}
