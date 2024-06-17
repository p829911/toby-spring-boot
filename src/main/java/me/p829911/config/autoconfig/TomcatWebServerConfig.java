package me.p829911.config.autoconfig;

import me.p829911.config.ConditionalMyOnClass;
import me.p829911.config.EnableMyConfigurationProperties;
import me.p829911.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
@EnableMyConfigurationProperties(ServerProperties.class)
public class TomcatWebServerConfig {

  @Bean("tomcatWebServerFactory")
  @ConditionalOnMissingBean
  public ServletWebServerFactory servletWebServerFactory(ServerProperties properties) {
    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();

    factory.setContextPath(properties.getContextPath());
    factory.setPort(properties.getPort());
    return factory;
  }
}
