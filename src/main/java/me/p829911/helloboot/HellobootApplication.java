package me.p829911.helloboot;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class HellobootApplication {

  public static void main(String[] args) {
    ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
    WebServer webServer =
        serverFactory.getWebServer(
            servletContext ->
                servletContext
                    .addServlet(
                        "frontcontroller",
                        new HttpServlet() {
                          @Override
                          protected void service(HttpServletRequest req, HttpServletResponse resp)
                              throws IOException {
                            // 인증, 보안, 다국어, 공통 기능
                            if (req.getRequestURI().equals("/hello") && req.getMethod().equals(
                                HttpMethod.GET.name())) {
                              String name = req.getParameter("name");

                              resp.setStatus(HttpStatus.OK.value());
                              resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                              resp.getWriter().println("Hello " + name);
                            } else {
                              resp.setStatus(HttpStatus.NOT_FOUND.value());
                            }
                          }
                        })
                    .addMapping("/*"));
    webServer.start();
  }
}
