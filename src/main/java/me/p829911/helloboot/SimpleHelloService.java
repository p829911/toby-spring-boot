package me.p829911.helloboot;

public class SimpleHelloService implements HelloService {

  @Override
  public String sayHello(String name) {
    return "Hello " + name;
  }
}
