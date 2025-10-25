package edu.fra.uas.notendurchschnitt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanController {
@Autowired
private MessageService messageService;
public String putMessage(String message) {
messageService.setMessage(" put messgae: " + message);
return messageService.getMessage();
}
}