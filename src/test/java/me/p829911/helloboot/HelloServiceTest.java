package me.p829911.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloServiceTest {
	
	@Test
	void simpleHelloService() {
		SimpleHelloService helloService = new SimpleHelloService();

		String ret = helloService.sayHello("Test");

		Assertions.assertThat(ret).isEqualTo("Hello Test");
	}

	@Test
	void helloDecorator() {
		HelloDecorator helloDecorator = new HelloDecorator(name -> name);

		String ret = helloDecorator.sayHello("Test");

		Assertions.assertThat(ret).isEqualTo("*Test*");
	}
}
