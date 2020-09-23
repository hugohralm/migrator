package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.ModeloDocumento;
import br.gov.go.mago.migrator.repository.ModeloDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ModeloDocumentoService {

    private final ModeloDocumentoRepository repository;

    @Autowired
    public ModeloDocumentoService(ModeloDocumentoRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public ModeloDocumento getOrCreateByDescricao(ModeloDocumento modeloDocumento) {
        if (modeloDocumento != null) {
            Optional<ModeloDocumento> documento = repository.findFirstByDescricaoOrderById(modeloDocumento.getDescricao());
            return documento.orElseGet(() -> repository.save(new ModeloDocumento(modeloDocumento)));
        } else {
            return null;
        }
    }
}
