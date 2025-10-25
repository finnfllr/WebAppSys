package edu.fra.uas.notendurchschnitt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import edu.fra.uas.notendurchschnitt.service.NotenRechnerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class NotenController {
    @Autowired
    private NotenRechnerService notenRechnerService;
    private static final Logger log = LoggerFactory.getLogger(NotenController.class);

    public void addNote(double wert, int gewichtung) {
        notenRechnerService.addNote(wert, gewichtung);
        // KORREKTUR: Logging-Platzhalter verwenden
        log.trace("addNote: Wert {} mit Gewichtung {}", wert, gewichtung);
    }

    public double calculateAverage() {
        double durchschnitt = notenRechnerService.berechneDurchschnitt();
        // INFO ist besser für Ergebnisse als DEBUG
        log.info("Durchschnittsnote berechnet: {}", durchschnitt);
        return durchschnitt;
    }

    public void clear() {
        notenRechnerService.clearNotes();
        log.info("Noten werden zurückgesetzt.");
    }
}