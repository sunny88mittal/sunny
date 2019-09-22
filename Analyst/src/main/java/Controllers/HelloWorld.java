package Controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class HelloWorld {

    @RequestMapping("/")
    String hello() {
        return "Hello World! JavaInterviewPoint";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloWorld.class, args);
    }
}