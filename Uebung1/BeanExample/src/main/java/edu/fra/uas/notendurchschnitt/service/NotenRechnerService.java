package edu.fra.uas.notendurchschnitt.service;
import edu.fra.uas.notendurchschnitt.controller.NotenController;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service; // Oder @Component
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Import der erstellten Modell-Klasse
import edu.fra.uas.notendurchschnitt.model.Note;

@Service // Markiert die Klasse als Spring Bean
public class NotenRechnerService {


    private List<Note> notenListe = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(NotenRechnerService.class);

    // Methode zum Hinzufügen einer Note
    public void addNote(double wert, int gewichtung) {
        Note neueNote = new Note(wert, gewichtung);
        notenListe.add(neueNote);
        log.debug("Note added");
    }

    // Methode zur Berechnung des Durchschnitts
    public double berechneDurchschnitt() {
        if(notenListe.isEmpty()) {
            log.warn("Es konnten keine Noten gefunden werden.");
            return 0.0;
        }
        double total = 0.0;
        for(Note note : notenListe) {
            total = total + note.wert() * note.gewichtung();
        }
        int gewichtung = 0;
        for(Note note : notenListe) {
            gewichtung = gewichtung + note.gewichtung();
        }
        total = total / gewichtung;
        log.debug("Total gewichtung: " + gewichtung);
        return total;
    }
    @PreDestroy
    public void clearNotes() {
        notenListe.clear();
        log.info("Notes cleared, Anwendung beendet.");

    }

    @PostConstruct
    public void initializeGrades() {
        // 2. Szenario-Aufrufe über den Controller
        log.debug("1. Füge Noten hinzu (Controller delegiert an Service)...");

        // Note 1: Wert 1.0, Gewichtung 6 ECTS
        this.addNote(1.0, 6);

        // Note 2: Wert 2.3, Gewichtung 4 ECTS
        this.addNote(2.3, 4);

        // Note 3: Wert 1.7, Gewichtung 5 ECTS
        this.addNote(1.7, 5);

        log.info("Noten wurden eingetragen.");
    }
}