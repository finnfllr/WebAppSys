package edu.fra.uas.notendurchschnitt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Wichtig: Importiere deinen NotenController
import edu.fra.uas.notendurchschnitt.controller.NotenController;

@SpringBootApplication
public class BeanBeispielApplication {

    // Logger für die Starter-Klasse
    private static final Logger log = LoggerFactory.getLogger(BeanBeispielApplication.class);

    // 1. Injektion des NotenControllers (Schnittstelle zur Logik)
    @Autowired
    private NotenController notenController;

    public static void main(String[] args) {
        SpringApplication.run(BeanBeispielApplication.class, args);
    }


    @Bean
    CommandLineRunner init() {
        return args -> {
            log.info("--- START: Notendurchschnittsberechnung ---");

            // 2. Szenario-Aufrufe über den Controller
            log.debug("1. Füge Noten hinzu (Controller delegiert an Service)...");

            // Note 1: Wert 1.0, Gewichtung 6 ECTS
            notenController.addNote(1.0, 6);

            // Note 2: Wert 2.3, Gewichtung 4 ECTS
            notenController.addNote(2.3, 4);

            // Note 3: Wert 1.7, Gewichtung 5 ECTS
            notenController.addNote(1.7, 5);

            // 3. Durchschnitt berechnen
            double durchschnitt = notenController.calculateAverage();

            // 4. Endgültige Ausgabe des Ergebnisses
            log.info("Der finale Notendurchschnitt beträgt: {}", durchschnitt);

            // 5. Optional: Aufräumen (für den nächsten Start)
            notenController.clear();

            log.info("--- ENDE: Anwendung wird beendet. ---");
        };
    }
}