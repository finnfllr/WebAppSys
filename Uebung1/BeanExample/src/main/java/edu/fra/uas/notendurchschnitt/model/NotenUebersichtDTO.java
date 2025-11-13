package edu.fra.uas.notendurchschnitt.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NotenUebersichtDTO {
    private List<Note> noten;
    private double durchschnitt;
    public NotenUebersichtDTO() {
    }
    public NotenUebersichtDTO(double durchschnitt, List<Note> noten) {
        this.durchschnitt = durchschnitt;
        this.noten = noten;
    }

    public List<Note> getNoten() {
        return noten;
    }

    public double getDurchschnitt() {
        return durchschnitt;
    }
}
