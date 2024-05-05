package me.p829911.helloboot;

import org.springframework.web.bind.annotation.RequestParam;

public class HelloController {
	public String hello(@RequestParam String name) {
		return "Hello " + name;
	}
}
