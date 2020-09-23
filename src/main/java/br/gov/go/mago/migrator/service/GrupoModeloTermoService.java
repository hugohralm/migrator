package br.gov.go.mago.migrator.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.gov.go.mago.migrator.model.GrupoModeloTermo;
import br.gov.go.mago.migrator.model.ModeloTermo;
import br.gov.go.mago.migrator.repository.GrupoModeloTermoRepository;

@Service
public class GrupoModeloTermoService {

    private final GrupoModeloTermoRepository repository;

    private final ModeloTermoService modeloTermoService;

    @Autowired
    public GrupoModeloTermoService(GrupoModeloTermoRepository repository, ModeloTermoService modeloTermoService) {
        this.repository = repository;
        this.modeloTermoService = modeloTermoService;
    }

    @Transactional(rollbackFor = Throwable.class)
    public GrupoModeloTermo getOrCreateByDescricao(GrupoModeloTermo grupoModeloTermoParam) {
        if (grupoModeloTermoParam != null) {
            Optional<GrupoModeloTermo> grupoModelo = repository
                    .findFirstByDescricaoOrderById(grupoModeloTermoParam.getDescricao());
            GrupoModeloTermo grupoModeloTermo = grupoModelo
                    .orElseGet(() -> repository.saveAndFlush(new GrupoModeloTermo(grupoModeloTermoParam)));
            if (CollectionUtils.isEmpty(grupoModeloTermo.getModelos())) {
                grupoModeloTermo = preencherGrupoTermo(grupoModeloTermo, grupoModeloTermoParam.getModelos());
            }
            return grupoModeloTermo;
        } else {
            return null;
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    private GrupoModeloTermo preencherGrupoTermo(GrupoModeloTermo grupoModeloTermoParam, Set<ModeloTermo> modelos) {
        for (ModeloTermo modelo : modelos) {
            grupoModeloTermoParam.adicionarModeloTermo(this.modeloTermoService.getOrCreateByDescricao(modelo));
        }
        return repository.saveAndFlush(grupoModeloTermoParam);
    }
}
