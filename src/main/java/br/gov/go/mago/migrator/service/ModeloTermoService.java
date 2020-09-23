package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.ModeloTermo;
import br.gov.go.mago.migrator.repository.ModeloTermoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ModeloTermoService {

    private final ModeloTermoRepository repository;

    @Autowired
    public ModeloTermoService(ModeloTermoRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public ModeloTermo getOrCreateByDescricao(ModeloTermo modeloTermo) {
        if (modeloTermo != null) {
            Optional<ModeloTermo> termo = repository.findFirstByDescricaoAndDataExclusaoIsNullOrderById(modeloTermo.getDescricao());
            return termo.orElseGet(() -> repository.save(new ModeloTermo(modeloTermo)));
        } else {
            return null;
        }
    }
}
