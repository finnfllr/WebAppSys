package edu.fra.uas.notendurchschnitt.controller;

import edu.fra.uas.notendurchschnitt.model.NotenUebersichtDTO;
import edu.fra.uas.notendurchschnitt.model.*;
import edu.fra.uas.notendurchschnitt.service.NotenRechnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping("/api/notes")
public class NotenRestController {
    @Autowired
    private NotenRechnerService notenRechnerService;
    private static final Logger log = LoggerFactory.getLogger(NotenRestController.class);

    // http://127.0.0.1/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotenUebersichtDTO> getAllGrades() {
        log.debug("REST request to get all grades");
        List<Note> notenListe = notenRechnerService.getNotenListe();
        double durchschnitt = notenRechnerService.berechneDurchschnitt();
        if(notenListe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        NotenUebersichtDTO dto = new NotenUebersichtDTO(durchschnitt, notenListe);
        return new ResponseEntity<>(dto, HttpStatus.OK); // 200 OK
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> addGrade(@RequestBody Note note) {
        log.debug("REST request to save Note : {}", note);
        if(note.wert() <= 0 || note.gewichtung() <= 0) {
            log.error("Invalid Note: {}", note);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
        notenRechnerService.addGrade(note.wert(), note.gewichtung());
        return new ResponseEntity<>(note, HttpStatus.CREATED); // 201 Created
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllGrade() {
        log.debug("REST request to delete all grades");
        notenRechnerService.clearGrades();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content

    }
}
