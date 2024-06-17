package me.p829911.config.autoconfig;

import me.p829911.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@MyAutoConfiguration
public class ServerPropertiesConfig {

	@Bean
	public ServerProperties serverProperties(Environment environment) {
		ServerProperties serverProperties = new ServerProperties();
		serverProperties.setContextPath(environment.getProperty("contextPath"));
		serverProperties.setPort(Integer.parseInt(environment.getProperty("port")));
		return serverProperties;
	}
}
