package edu.fra.uas.notendurchschnitt.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service; // Oder @Component
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Import der erstellten Modell-Klasse
import edu.fra.uas.notendurchschnitt.model.Note;

@Service // Markiert die Klasse als Spring Bean
public class NotenRechnerService {

    // <<<<<< HIER IST DER RICHTIGE PLATZ (Klassenattribut) >>>>>>
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

    public void clearNotes() {
        notenListe.clear();
        log.info("Notes cleared");
    }
}