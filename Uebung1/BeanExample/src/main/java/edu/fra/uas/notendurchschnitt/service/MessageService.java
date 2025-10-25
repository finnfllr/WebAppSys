package edu.fra.uas.notendurchschnitt.service;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MessageService {
    private static  final Logger log = LoggerFactory.getLogger(MessageService.class);

    private String message;

    public String getMessage() {
        log.debug("getMessage");
        return message;
    }

    public void setMessage(String message) {
    log.debug("setMessage");
        this.message = message;
    }
}