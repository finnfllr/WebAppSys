package edu.fra.uas.beanexample;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.fra.uas.notendurchschnitt.BeanBeispielApplication;
import edu.fra.uas.notendurchschnitt.controller.NotenController; // NEU: Korrekter Controller

// Hinweis: Alle Verweise auf BeanController wurden entfernt

@SpringBootTest(classes = BeanBeispielApplication.class)
public class NotenControllerTest { // Tipp: Klasse für Klarheit umbenannt

    @Autowired
    private NotenController notenController; // KORREKT: Injiizierte Instanz

    @Test
    void berechneKorrektenDurchschnitt() {
        // Test muss auf der injizierten Instanz notenController arbeiten!

        notenController.clear(); // KORREKT

        // Füge Noten hinzu
        notenController.addNote(1.0, 5); // KORREKT
        notenController.addNote(2.0, 5); // KORREKT

        // Prüfe, ob das Ergebnis 1.5 ist
        assertThat(notenController.calculateAverage()) // KORREKT
                .isEqualTo(1.5);
    }
}