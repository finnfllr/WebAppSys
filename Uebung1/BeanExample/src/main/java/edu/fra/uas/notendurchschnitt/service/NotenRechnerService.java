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



@Service
public class NotenRechnerService {

    public static List<Note> notenListe = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(NotenRechnerService.class);

    public synchronized void addNote(double wert, int gewichtung) {
        Note neueNote = new Note(wert, gewichtung);
        notenListe.add(neueNote);
        log.debug("Note added");
    }

    public static synchronized List<Note> getNotenListe() {
        return new ArrayList<>(notenListe); // Kopie zurückgeben
    }

    public double berechneDurchschnitt() {
        synchronized (notenListe) {
            if (notenListe.isEmpty()) {
                log.warn("Es konnten keine Noten gefunden werden.");
                return 0.0;
            }
            double total = 0.0;
            int gewichtung = 0;
            for (Note note : notenListe) {
                total += note.wert() * note.gewichtung();
                gewichtung += note.gewichtung();
            }
            double durchschnitt = total / gewichtung;
            log.debug("Total gewichtung: {}", gewichtung);
            return durchschnitt;
        }
    }

    @PreDestroy
    public void clearNotes() {
        synchronized (notenListe) {
            notenListe.clear();
        }
        log.info("Notes cleared, Anwendung beendet.");
    }

    @PostConstruct
    public void initializeGrades() {
        log.debug("1. Füge Noten hinzu (Controller delegiert an Service)...");
        this.addNote(1.0, 6);
        this.addNote(2.3, 4);
        this.addNote(1.7, 5);
        log.info("Noten wurden eingetragen.");
    }


}


