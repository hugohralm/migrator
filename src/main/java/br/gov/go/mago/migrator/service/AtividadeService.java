package br.gov.go.mago.migrator.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.mago.migrator.model.Atividade;
import br.gov.go.mago.migrator.repository.AtividadeRepository;

@Service
public class AtividadeService {

    private final AtividadeRepository repository;

    @Autowired
    public AtividadeService(AtividadeRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public Atividade getByCodigoDescricao(Atividade atividade) {
        if (atividade != null) {
            Optional<Atividade> ativ = repository.findFirstByCodigoAndDescricaoOrderById(atividade.getCodigo(),
                    atividade.getDescricao());
            return ativ.orElseThrow(() -> new IllegalStateException(
                    "Atividade não cadastrada. Cadastrar atividade " + atividade.getDescricao() + "."));
        } else {
            return null;
        }
    }
}
