package me.p829911.config.autoconfig;

import me.p829911.config.MyAutoConfiguration;
import me.p829911.config.autoconfig.JettyWebServerConfig.JettyCondition;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

@MyAutoConfiguration
@Conditional(JettyCondition.class)
public class JettyWebServerConfig {

	@Bean("jettyWebServerFactory")
	public ServletWebServerFactory servletWebServerFactory() {
    return new JettyServletWebServerFactory();
	}

	static class JettyCondition implements Condition {

		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return true;
		}
	}
}
