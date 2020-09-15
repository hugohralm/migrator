package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.Consequencia;
import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import br.gov.go.mago.migrator.model.RespostaTemplate;
import br.gov.go.mago.migrator.repository.ConsequenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ConsequenciaService {

    private final ConsequenciaRepository repository;

    private final RespostaTemplateService respostaTemplateService;

    private final ModeloCondicionanteService modeloCondicionanteService;

    private final ModeloTermoService modeloTermoService;

    private final ModeloDeclaracaoService modeloDeclaracaoService;

    @Autowired
    public ConsequenciaService(ConsequenciaRepository repository,
                               RespostaTemplateService respostaTemplateService,
                               ModeloCondicionanteService modeloCondicionanteService,
                               ModeloTermoService modeloTermoService,
                               ModeloDeclaracaoService modeloDeclaracaoService) {
        this.repository = repository;
        this.respostaTemplateService = respostaTemplateService;
        this.modeloCondicionanteService = modeloCondicionanteService;
        this.modeloTermoService = modeloTermoService;
        this.modeloDeclaracaoService = modeloDeclaracaoService;
    }

    public Consequencia getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar o consequencia solicitado."));
    }

    public List<Consequencia> getByRespostaTemplateId(Integer respostaTemplateId) {
        return repository.findAllByRespostaTemplateId(respostaTemplateId);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void migrarConsequenciaResposta(QuestionarioTemplate novoQuestionario) {
        List<RespostaTemplate> novasRespostas = respostaTemplateService.getAllByQuestionario(novoQuestionario);
        for (RespostaTemplate novaResposta : novasRespostas) {
            List<Consequencia> consequencias = repository.findAllByRespostaTemplateId(novaResposta.getRespostaTemplateMigrada());
            for (Consequencia consequencia : consequencias) {
                repository.save(novaConsequencia(consequencia, novaResposta));
            }
        }
    }

    private Consequencia novaConsequencia(Consequencia consequencia, RespostaTemplate novaResposta) {
        Consequencia novaConsequencia = new Consequencia(consequencia.getTipoConsequencia());
        novaConsequencia.setRespostaTemplate(novaResposta);
        novaConsequencia.setModeloCondicionante(modeloCondicionanteService.getOrCreateByDescricao(consequencia.getModeloCondicionante()));
        novaConsequencia.setModeloTermo(modeloTermoService.getOrCreateByDescricao(consequencia.getModeloTermo()));
        novaConsequencia.setModeloDeclaracao(modeloDeclaracaoService.getOrCreateByNome(consequencia.getModeloDeclaracao()));
        return novaConsequencia;
    }
}
