package br.gov.go.mago.migrator.controller;

import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import br.gov.go.mago.migrator.service.ConsequenciaService;
import br.gov.go.mago.migrator.service.MigracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teste")
public class QuestionarioController {

    private final MigracaoService migracaoService;

    private final ConsequenciaService consequenciaService;

    @Autowired
    public QuestionarioController(MigracaoService migracaoService, ConsequenciaService consequenciaService) {
        this.migracaoService = migracaoService;
        this.consequenciaService = consequenciaService;
    }

    @PostMapping(path = "/{id}/migrar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getQuestionario(@PathVariable Integer id) {
        QuestionarioTemplate questionario = migracaoService.migrarQuestionario(id);
        if (questionario != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(migracaoService.migrarQuestionario(id));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping(path = "/{id}/valida", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validaQuestionario(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(migracaoService.validaQuestionario(id));
    }

    @GetMapping(path = "/{id}/consequencia", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getConsequencia(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(consequenciaService.getById(id));
    }
}
