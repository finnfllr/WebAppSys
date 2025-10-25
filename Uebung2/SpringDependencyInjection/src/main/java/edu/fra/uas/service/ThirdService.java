package edu.fra.uas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdService {

    private static final Logger log = LoggerFactory.getLogger(ThirdService.class);

    @Autowired private CounterService counterService;

    public ThirdService() {

    }

    public void doSomething() {

        log.info("thirdService --> doSomething()");
    }
    public void performCount() {
        int currentCount = counterService.count(); // HIER wird count() aufgerufen
        System.out.println("FirstService: Zählerstand ist " + currentCount);
    }

}
