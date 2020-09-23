package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.ModeloDeclaracao;
import br.gov.go.mago.migrator.repository.ModeloDeclaracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ModeloDeclaracaoService {

    private final ModeloDeclaracaoRepository repository;

    private final ModeloTermoService modeloTermoService;

    @Autowired
    public ModeloDeclaracaoService(ModeloDeclaracaoRepository repository, ModeloTermoService modeloTermoService) {
        this.repository = repository;
        this.modeloTermoService = modeloTermoService;
    }

    @Transactional(rollbackFor = Throwable.class)
    public ModeloDeclaracao getOrCreateByNome(ModeloDeclaracao modeloDeclaracao) {
        if (modeloDeclaracao != null) {
            Optional<ModeloDeclaracao> declaracao = repository.findFirstByNomeAndDataExclusaoIsNullOrderById(modeloDeclaracao.getNome());
            if (declaracao.isPresent()) {
                return declaracao.get();
            } else {
                ModeloDeclaracao novaDeclaracao = new ModeloDeclaracao(modeloDeclaracao);
                novaDeclaracao.setTermo(modeloTermoService.getOrCreateByDescricao(modeloDeclaracao.getTermo()));
                return repository.save(novaDeclaracao);
            }
        } else {
            return null;
        }
    }
}
