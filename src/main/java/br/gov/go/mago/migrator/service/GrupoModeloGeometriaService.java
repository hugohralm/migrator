package br.gov.go.mago.migrator.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.gov.go.mago.migrator.model.GrupoModeloGeometria;
import br.gov.go.mago.migrator.model.ItemGrupoGeometria;
import br.gov.go.mago.migrator.repository.GrupoModeloGeometriaRepository;

@Service
public class GrupoModeloGeometriaService {

    private final GrupoModeloGeometriaRepository repository;

    private final ModeloGeometriaService modeloGeometriaService;

    @Autowired
    public GrupoModeloGeometriaService(GrupoModeloGeometriaRepository repository,
            ModeloGeometriaService modeloGeometriaService) {
        this.repository = repository;
        this.modeloGeometriaService = modeloGeometriaService;
    }

    @Transactional(rollbackFor = Throwable.class)
    public GrupoModeloGeometria getOrCreateByDescricao(GrupoModeloGeometria grupoModeloGeoParam) {
        if (grupoModeloGeoParam != null) {
            Optional<GrupoModeloGeometria> grupoModelo = repository
                    .findFirstByNomeOrderById(grupoModeloGeoParam.getNome());
            GrupoModeloGeometria grupoModeloGeometria = grupoModelo
                    .orElseGet(() -> repository.save(new GrupoModeloGeometria(grupoModeloGeoParam)));
            if (CollectionUtils.isEmpty(grupoModeloGeometria.getItensGrupoGeo())) {
                grupoModeloGeometria = preencherGrupoModeloGeometria(grupoModeloGeometria,
                        grupoModeloGeoParam.getItensGrupoGeo());
            }
            return grupoModeloGeometria;
        } else {
            return null;
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    private GrupoModeloGeometria preencherGrupoModeloGeometria(GrupoModeloGeometria grupoModeloGeometriaParam,
            Set<ItemGrupoGeometria> itensGrupoGeoParam) {
        Set<ItemGrupoGeometria> itensGrupoGeo = new HashSet<>();
        for (ItemGrupoGeometria itemGeo : itensGrupoGeoParam) {
            itensGrupoGeo.add(
                    new ItemGrupoGeometria(this.modeloGeometriaService.getOrCreateByDescricao(itemGeo.getModeloGeo()),
                            itemGeo.isOpcional(), itemGeo.isExibirNaLicenca()));
        }
        grupoModeloGeometriaParam.setItensGrupoGeo(itensGrupoGeo);
        return repository.saveAndFlush(grupoModeloGeometriaParam);
    }
}
