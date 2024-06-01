package me.p829911.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[] {
				"me.p829911.config.autoconfig.DispatcherServletConfig",
				"me.p829911.config.autoconfig.TomcatWebServerConfig"
		};
	}
}
