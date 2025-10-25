package edu.fra.uas;

import edu.fra.uas.service.SecondService;
import edu.fra.uas.service.ThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.fra.uas.service.FirstService;

@SpringBootApplication
public class SpringDependencyInjectionApplication {


    @Autowired private FirstService firstService;
    @Autowired private SecondService secondService;
    @Autowired private ThirdService thirdService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDependencyInjectionApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init() {
		CommandLineRunner action = new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
			//	FirstService firstService = new FirstService();
				firstService.doSomething();
                firstService.performCount();  // Erwartet Zähler = 1
                secondService.performCount(); // Erwartet Zähler = 2
                thirdService.performCount();  // Erwartet Zähler = 3
			}
		};
		return action;
	}

}
