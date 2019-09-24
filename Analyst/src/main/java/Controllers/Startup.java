package Controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class Startup {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Startup.class, args);
	}
}