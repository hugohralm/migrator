package br.gov.go.mago.migrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.go.mago.migrator.model.Tipologia;
import br.gov.go.mago.migrator.service.MigracaoTipologiaService;
import br.gov.go.mago.migrator.service.TipologiaService;

@RestController
@RequestMapping("/api/tipologias")
public class TipologiaController {

    private final TipologiaService tipologiaService;

    private final MigracaoTipologiaService migracaoService;

    @Autowired
    public TipologiaController(TipologiaService tipologiaService, MigracaoTipologiaService migracaoService) {
        this.tipologiaService = tipologiaService;
        this.migracaoService = migracaoService;
    }
    
    @PostMapping(path = "/migrar-json-tipologia", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> migrarTipologia(@RequestBody Tipologia tipologia) {
        Tipologia tipologiaSalva = this.migracaoService.migrarTipologia(tipologia);
        return ResponseEntity.status(HttpStatus.CREATED).body(migracaoService.migrarItensTipologia(tipologiaSalva, tipologia));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTipologia(@PathVariable Integer id) {
        Tipologia tipologia = tipologiaService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(tipologia);
    }

    @GetMapping(path = "/{id}/valida", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validaTipologia(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(migracaoService.validaTipologia(id));
    }
}
