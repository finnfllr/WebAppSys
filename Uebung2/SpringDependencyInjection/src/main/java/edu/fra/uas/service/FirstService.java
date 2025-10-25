package edu.fra.uas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstService {

	// Constructor Injection: im ersten Schritt auskommentieren

    @Autowired private SecondService secondService;

    @Autowired private CounterService counterService;


	public FirstService() {
	//	secondService = new SecondService();
	}

	// Constructor Injection
	@Autowired
	public FirstService(SecondService secondService) {
		this.secondService = secondService;
	}

	// Setter Injection
	@Autowired
	public void setSecondService(SecondService secondService) {
		this.secondService = secondService;
	}
	
	public void doSomething() {
		secondService.doSomething();

	}

    public void performCount() {
        int currentCount = counterService.count(); // HIER wird count() aufgerufen
        System.out.println("FirstService: Zählerstand ist " + currentCount);
    }
	
}
