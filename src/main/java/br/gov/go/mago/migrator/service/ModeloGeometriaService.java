package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.ModeloGeometria;
import br.gov.go.mago.migrator.repository.ModeloGeometriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ModeloGeometriaService {

    private final ModeloGeometriaRepository repository;

    @Autowired
    public ModeloGeometriaService(ModeloGeometriaRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public ModeloGeometria getOrCreateByDescricao(ModeloGeometria modeloGeometria) {
        if (modeloGeometria != null) {
            Optional<ModeloGeometria> geometria = repository.findFirstByNomeAndTipoOrderById(modeloGeometria.getTipo(), modeloGeometria.getNome());
            return geometria.orElseGet(() -> repository.save(new ModeloGeometria(modeloGeometria)));
        } else {
            return null;
        }
    }
}
