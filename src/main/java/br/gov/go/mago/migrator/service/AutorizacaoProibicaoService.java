package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.AutorizacaoProibicao;
import br.gov.go.mago.migrator.repository.AutorizacaoProibicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AutorizacaoProibicaoService {

    private final AutorizacaoProibicaoRepository repository;

    @Autowired
    public AutorizacaoProibicaoService(AutorizacaoProibicaoRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public AutorizacaoProibicao getOrCreateByDescricao(AutorizacaoProibicao autorizacaoProibicao) {
        if (autorizacaoProibicao != null) {
            Optional<AutorizacaoProibicao> autProib = repository.findFirstByTipoAndDescricaoAndDataExclusaoIsNullOrderById(autorizacaoProibicao.getTipo(), autorizacaoProibicao.getDescricao());
            return autProib.orElseGet(() -> repository.save(new AutorizacaoProibicao(autorizacaoProibicao)));
        } else {
            return null;
        }
    }
}
