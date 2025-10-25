package edu.fra.uas.v3autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties.UiService.LOGGER;

@Component
@Qualifier("takeBreak")
public class Resting implements Work {
    private static final Logger LOGGER = LoggerFactory.getLogger(Resting.class);

    @Override
    public void doWork() {
        LOGGER.info(" --> taking a break");
    }
}
