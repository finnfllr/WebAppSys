package edu.fra.uas.notendurchschnitt.graphql;

import edu.fra.uas.notendurchschnitt.model.Note;
import edu.fra.uas.notendurchschnitt.model.NotenUebersichtDTO;
import edu.fra.uas.notendurchschnitt.service.NotenRechnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NotesGraphqlController {

    @Autowired
    private NotenRechnerService notenRechnerService;

    @QueryMapping
    public List<Note> notes() {
        return notenRechnerService.getNotenListe();
    }

    @QueryMapping
    public NotenUebersichtDTO overview() {
        double durchschnitt = notenRechnerService.berechneDurchschnitt();
        return new NotenUebersichtDTO(durchschnitt, notenRechnerService.getNotenListe());
    }

    @MutationMapping
    public Note addNote(@Argument double wert, @Argument int gewichtung) {
        notenRechnerService.addGrade(wert, gewichtung);
        return new Note(wert, gewichtung);
    }

    @MutationMapping
    public boolean clearNotes() {
        notenRechnerService.clearGrades();
        return true;
    }
}

/*
# Liste der Noten
curl -s -X POST -H "Content-Type: application/json" \
  -d '{"query":"{ notes { wert gewichtung } }"}' \
  http://localhost:8080/graphql

# Übersicht: Noten + Durchschnitt
curl -s -X POST -H "Content-Type: application/json" \
  -d '{"query":"{ overview { durchschnitt noten { wert gewichtung } } }"}' \
  http://localhost:8080/graphql

# Mutation: einzelne Note einfügen (direkt inlined)
curl -s -X POST -H "Content-Type: application/json" \
  -d '{"query":"mutation { addNote(wert:2.0, gewichtung:4) { wert gewichtung } }"}' \
  http://localhost:8080/graphql

# Mutation mit Variablen (sicherer bei dynamischen Werten)
curl -s -X POST -H "Content-Type: application/json" \
  -d '{"query":"mutation AddNote($wert: Float!, $gewichtung: Int!) { addNote(wert: $wert, gewichtung: $gewichtung) { wert gewichtung } }","variables":{"wert":2.0,"gewichtung":4}}' \
  http://localhost:8080/graphql

# Alle Noten löschen
curl -s -X POST -H "Content-Type: application/json" \
  -d '{"query":"mutation { clearNotes }"}' \
  http://localhost:8080/graphql

 */
