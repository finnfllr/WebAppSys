package edu.fra.uas.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CounterService {

    private static final Logger log = LoggerFactory.getLogger(CounterService.class);

    // Attribut, das den Zustand speichert
    private int zaehler = 0;

    // Die Aufgabe erfordert, dass der Zähler beim Aufruf einer Methode 'count' erhöht wird.
    public int count() {
        zaehler++;
        log.info("CounterService.count() aufgerufen. Aktueller Zählerstand: " + zaehler);
        return zaehler;
    }

    // Optional: Konstruktor zur Protokollierung der Instanziierung
    public CounterService() {
        log.info("CounterService Instanz erstellt.");
    }
}