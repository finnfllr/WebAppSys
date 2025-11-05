package edu.fra.uas.notendurchschnitt.controller;

import edu.fra.uas.notendurchschnitt.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import edu.fra.uas.notendurchschnitt.service.NotenRechnerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NotenController {
    @Autowired
    private NotenRechnerService notenRechnerService;
    private static final Logger log = LoggerFactory.getLogger(NotenController.class);

    // http://127.0.0.1/
    @GetMapping("/")
    public String get() {
        log.debug("get() is called");
        return "index.html";
    }

    @GetMapping("/list")
    public String list(Model model) {
        log.debug("list() is called");
        List<Note> notenListe = NotenRechnerService.getNotenListe();
        model.addAttribute("note", notenListe);

        double durchschnitt = notenRechnerService.berechneDurchschnitt();
        model.addAttribute("durchschnitt", durchschnitt);
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm() {
        log.debug("showAddForm() is called");
        return "add_note";
    }

    // NotenController.java

    @PostMapping("/addNote")
    public String addNoteFromForm(
            @RequestParam("wert") String wertString, // Als String, um Formatfehler abzufangen
            @RequestParam("gewichtung") int gewichtung,
            Model model)
    {
        try {
            // 1. Eingabe validieren und umwandeln
            double wert = Double.parseDouble(wertString);

            // 2. Daten verarbeiten (Service-Schicht)
            notenRechnerService.addNote(wert, gewichtung);

            // 3. Umleiten (POST/REDIRECT/GET Pattern)
            // Spring sendet 302 an den Browser, der dann GET /list abruft
            return "redirect:/list";

        } catch (NumberFormatException e) {
            // Bei Fehler (z.B. "abc" statt 1.3 eingegeben)
            String errorMessage = "Fehler: Die Note muss eine gültige Dezimalzahl sein.";
            model.addAttribute("error", errorMessage);

            // Formular mit Fehlermeldung erneut anzeigen
            return "add_note";
        }
    }

    @GetMapping("/delete")
    public String clearNotes() {
        notenRechnerService.clearNotes();
        log.info("Noten werden zurückgesetzt.");
        return "redirect:/list";
    }


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