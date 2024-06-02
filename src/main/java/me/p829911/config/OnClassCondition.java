package me.p829911.config;

import java.util.Map;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

public class OnClassCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(
				ConditionalMyOnClass.class.getName());
		if (annotationAttributes == null) {
			return false;
		}

		Object value = annotationAttributes.getOrDefault("value", null);
		if (value == null) {
			return false;
		}

		return ClassUtils.isPresent((String) value, context.getClassLoader());
	}
}
