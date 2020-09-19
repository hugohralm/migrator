package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.ParametroTemplate;
import br.gov.go.mago.migrator.repository.ParametroTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ParametroTemplateService {

    private final ParametroTemplateRepository repository;

    @Autowired
    public ParametroTemplateService(ParametroTemplateRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public ParametroTemplate getOrCreateByDescricao(ParametroTemplate parametroTemplate) {
        if (parametroTemplate != null) {
            Optional<ParametroTemplate> parametro = repository.findFirstByTipoAndDescricaoAndDataExclusaoIsNullOrderById(parametroTemplate.getTipo(), parametroTemplate.getDescricao());
            return parametro.orElseGet(() -> repository.save(new ParametroTemplate(parametroTemplate)));
        } else {
            return null;
        }
    }
}
