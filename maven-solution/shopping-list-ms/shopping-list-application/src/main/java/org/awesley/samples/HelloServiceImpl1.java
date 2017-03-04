package org.awesley.samples;

import io.swagger.annotations.Api;

@Api("/sayHello")
public class HelloServiceImpl1 implements HelloService {

	@Override
    public String sayHello(String a) {
        return "Hello " + a + ", Welcome to CXF RS Spring Boot World!!!";
    }

}
