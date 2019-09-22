package Controllers;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Data.OptionsChainDownloader;
import Entities.OptionsChain;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class HelloWorld {

    @RequestMapping("/")
    OptionsChain hello() throws IOException, InterruptedException {
        //return "Hello World! JavaInterviewPoint";
    	return OptionsChainDownloader.getOptionsChain();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloWorld.class, args);
    }
}