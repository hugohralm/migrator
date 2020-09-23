package br.gov.go.mago.migrator.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.gov.go.mago.migrator.model.GrupoModeloCondicionante;
import br.gov.go.mago.migrator.model.ModeloCondicionante;
import br.gov.go.mago.migrator.repository.GrupoModeloCondicionanteRepository;

@Service
public class GrupoModeloCondicionanteService {

    private final GrupoModeloCondicionanteRepository repository;

    private final ModeloCondicionanteService modeloCondicionanteService;

    @Autowired
    public GrupoModeloCondicionanteService(GrupoModeloCondicionanteRepository repository,
            ModeloCondicionanteService modeloCondicionanteService) {
        this.repository = repository;
        this.modeloCondicionanteService = modeloCondicionanteService;
    }

    @Transactional(rollbackFor = Throwable.class)
    public GrupoModeloCondicionante getOrCreateByDescricao(GrupoModeloCondicionante grupoModeloCondicionanteParam) {
        if (grupoModeloCondicionanteParam != null) {
            Optional<GrupoModeloCondicionante> grupoModelo = repository
                    .findFirstByNomeOrderById(grupoModeloCondicionanteParam.getNome());
            GrupoModeloCondicionante grupoModeloCondicionante = grupoModelo.orElseGet(
                    () -> repository.saveAndFlush(new GrupoModeloCondicionante(grupoModeloCondicionanteParam)));
            if (CollectionUtils.isEmpty(grupoModeloCondicionante.getModelosCondicionante())) {
                grupoModeloCondicionante = preencherGrupoCondicionante(grupoModeloCondicionante,
                        grupoModeloCondicionanteParam.getModelosCondicionante());
            }
            return grupoModeloCondicionante;
        } else {
            return null;
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    private GrupoModeloCondicionante preencherGrupoCondicionante(GrupoModeloCondicionante grupoModeloCondicionanteParam,
            Set<ModeloCondicionante> modelosCondicionanteParam) {
        for (ModeloCondicionante modelo : modelosCondicionanteParam) {
            grupoModeloCondicionanteParam
                    .adicionarCondicionante(this.modeloCondicionanteService.getOrCreateByDescricao(modelo));
        }
        return repository.saveAndFlush(grupoModeloCondicionanteParam);
    }
}
