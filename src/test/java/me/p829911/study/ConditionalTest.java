package me.p829911.study;

import static org.assertj.core.api.Assertions.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

class ConditionalTest {

  @Test
  @DisplayName("conditional test")
  void conditional_test() {
    // true
    ApplicationContextRunner contextRunner1 = new ApplicationContextRunner();
    contextRunner1
        .withUserConfiguration(Config1.class)
        .run(
            context -> {
              assertThat(context).hasSingleBean(MyBean.class);
              assertThat(context).hasSingleBean(Config1.class);
            });

    // false
    ApplicationContextRunner contextRunner2 = new ApplicationContextRunner();
    contextRunner2
        .withUserConfiguration(Config2.class)
        .run(
            context -> {
              assertThat(context).doesNotHaveBean(MyBean.class);
              assertThat(context).doesNotHaveBean(Config2.class);

            });
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @Conditional(BooleanCondition.class)
  @interface BooleanConditional {
    boolean value();
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @Conditional(TrueCondition.class)
  @interface TrueConditional {}

  @Configuration
  @BooleanConditional(true)
  static class Config1 {
    @Bean
    MyBean myBean() {
      return new MyBean();
    }
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @Conditional(FalseCondition.class)
  @interface FalseConditional {}

  @Configuration
  @BooleanConditional(false)
  static class Config2 {
    @Bean
    MyBean myBean() {
      return new MyBean();
    }
  }

  static class MyBean {}

  static class TrueCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
      return true;
    }
  }

  static class FalseCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
      return false;
    }
  }

  static class BooleanCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
      Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(
          BooleanConditional.class.getName());
	    assert annotationAttributes != null;
	    return (Boolean) annotationAttributes.get("value");
    }
  }
}
