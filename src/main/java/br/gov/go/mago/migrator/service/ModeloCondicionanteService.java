package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.ModeloCondicionante;
import br.gov.go.mago.migrator.repository.ModeloCondicionanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ModeloCondicionanteService {

    private final ModeloCondicionanteRepository repository;

    @Autowired
    public ModeloCondicionanteService(ModeloCondicionanteRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public ModeloCondicionante getOrCreateByDescricao(ModeloCondicionante modeloCondicionante) {
        if (modeloCondicionante != null) {
            Optional<ModeloCondicionante> condicionante = repository.findFirstByDescricaoAndDataExclusaoIsNullOrderById(modeloCondicionante.getDescricao());
            return condicionante.orElseGet(() -> repository.save(new ModeloCondicionante(modeloCondicionante)));
        } else {
            return null;
        }
    }
}
