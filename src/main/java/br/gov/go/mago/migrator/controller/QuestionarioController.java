package br.gov.go.mago.migrator.controller;

import br.gov.go.mago.migrator.model.Consequencia;
import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import br.gov.go.mago.migrator.model.RespostaTemplate;
import br.gov.go.mago.migrator.service.ConsequenciaService;
import br.gov.go.mago.migrator.service.MigracaoService;
import br.gov.go.mago.migrator.service.QuestionarioTemplateService;
import br.gov.go.mago.migrator.service.RespostaTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questionarios")
public class QuestionarioController {

    private final MigracaoService migracaoService;

    private final RespostaTemplateService respostaTemplateService;

    private final ConsequenciaService consequenciaService;

    private final QuestionarioTemplateService questionarioTemplateService;

    @Autowired
    public QuestionarioController(MigracaoService migracaoService,
                                  QuestionarioTemplateService questionarioTemplateService,
                                  RespostaTemplateService respostaTemplateService,
                                  ConsequenciaService consequenciaService) {
        this.migracaoService = migracaoService;
        this.questionarioTemplateService = questionarioTemplateService;
        this.respostaTemplateService = respostaTemplateService;
        this.consequenciaService = consequenciaService;
    }

    @PostMapping(path = "/migrar-json-questionario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> migrarQuestionario(@RequestBody List<RespostaTemplate> respostas) {
        return ResponseEntity.status(HttpStatus.CREATED).body(migracaoService.migrarQuestionario(respostas));
    }

    @PostMapping(path = "/{id}/migrar-json-consequencias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> migrarConsequencias(@PathVariable Integer id, @RequestBody List<Consequencia> consequencias) {
        QuestionarioTemplate questionario = questionarioTemplateService.getById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(migracaoService.migrarConsequencias(questionario, consequencias));
    }

    @GetMapping(path = "/{id}/respostas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRespostas(@PathVariable Integer id) {
        QuestionarioTemplate questionario = questionarioTemplateService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(respostaTemplateService.getAllByQuestionario(questionario));
    }

    @GetMapping(path = "/{id}/consequencias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getConsequencias(@PathVariable Integer id) {
        QuestionarioTemplate questionario = questionarioTemplateService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(consequenciaService.getByQuestionarioTemplate(questionario));
    }

    @GetMapping(path = "/{id}/valida", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validaQuestionario(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(migracaoService.validaQuestionario(id));
    }
}
