package br.gov.go.mago.migrator.controller;

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

    @Autowired
    public QuestionarioController(MigracaoService migracaoService) {
        this.migracaoService = migracaoService;
    }

    @PostMapping(path = "/{id}/migrar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getQuestionario(@PathVariable Integer id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(migracaoService.migrarQuestionario(id));
    }

    @GetMapping(path = "/{id}/valida", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validaQuestionario(@PathVariable Integer id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(migracaoService.validaQuestionario(id));
    }
}
