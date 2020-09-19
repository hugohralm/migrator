package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.Consequencia;
import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import br.gov.go.mago.migrator.model.RespostaTemplate;
import br.gov.go.mago.migrator.repository.ConsequenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsequenciaService {

    private final ConsequenciaRepository repository;

    private final RespostaTemplateService respostaTemplateService;

    private final ModeloCondicionanteService modeloCondicionanteService;

    private final ModeloTermoService modeloTermoService;

    private final ModeloDeclaracaoService modeloDeclaracaoService;

    private final ParametroTemplateService parametroTemplateService;

    private final AutorizacaoProibicaoService autorizacaoProibicaoService;

    private final ModeloDocumentoService modeloDocumentoService;

    private final ModeloGeometriaService modeloGeometriaService;

    @Autowired
    public ConsequenciaService(ConsequenciaRepository repository,
                               RespostaTemplateService respostaTemplateService,
                               ModeloCondicionanteService modeloCondicionanteService,
                               ModeloTermoService modeloTermoService,
                               ModeloDeclaracaoService modeloDeclaracaoService,
                               ParametroTemplateService parametroTemplateService,
                               AutorizacaoProibicaoService autorizacaoProibicaoService,
                               ModeloDocumentoService modeloDocumentoService,
                               ModeloGeometriaService modeloGeometriaService) {
        this.repository = repository;
        this.respostaTemplateService = respostaTemplateService;
        this.modeloCondicionanteService = modeloCondicionanteService;
        this.modeloTermoService = modeloTermoService;
        this.modeloDeclaracaoService = modeloDeclaracaoService;
        this.parametroTemplateService = parametroTemplateService;
        this.autorizacaoProibicaoService = autorizacaoProibicaoService;
        this.modeloDocumentoService = modeloDocumentoService;
        this.modeloGeometriaService = modeloGeometriaService;
    }

    public boolean existsByRespostaTemplatePerguntaTemplateQuestionarioTemplate(QuestionarioTemplate questionario) {
        return repository.existsByRespostaTemplatePerguntaTemplateQuestionarioTemplate(questionario);
    }

    public List<Consequencia> getByQuestionarioTemplate(QuestionarioTemplate questionario) {
        return repository.findAllByRespostaTemplatePerguntaTemplateQuestionarioTemplateOrderById(questionario);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void migrarConsequenciaResposta(List<Consequencia> consequencias, QuestionarioTemplate novoQuestionario) {
        List<RespostaTemplate> novasRespostas = respostaTemplateService.getAllByQuestionario(novoQuestionario);
        for (RespostaTemplate novaResposta : novasRespostas) {
            List<Consequencia> consequenciasResposta = consequencias
                    .stream()
                    .filter(consequencia -> consequencia.getRespostaTemplate().getId().equals(novaResposta.getRespostaTemplateMigrada()))
                    .sorted(Comparator.comparingInt(Consequencia::getId))
                    .collect(Collectors.toList());
            for (Consequencia consequencia : consequenciasResposta) {
                repository.save(novaConsequencia(consequencia, novaResposta));
            }
        }
    }

    private Consequencia novaConsequencia(Consequencia consequencia, RespostaTemplate novaResposta) {
        Consequencia novaConsequencia = new Consequencia(consequencia.getTipoConsequencia(), consequencia.getResponsavelTecnico());
        novaConsequencia.setRespostaTemplate(novaResposta);
        novaConsequencia.setParametroTemplate(parametroTemplateService.getOrCreateByDescricao(consequencia.getParametroTemplate()));
        novaConsequencia.setModeloCondicionante(modeloCondicionanteService.getOrCreateByDescricao(consequencia.getModeloCondicionante()));
        novaConsequencia.setModeloTermo(modeloTermoService.getOrCreateByDescricao(consequencia.getModeloTermo()));
        novaConsequencia.setModeloDeclaracao(modeloDeclaracaoService.getOrCreateByNome(consequencia.getModeloDeclaracao()));
        novaConsequencia.setModeloLaudo(modeloDeclaracaoService.getOrCreateByNome(consequencia.getModeloLaudo()));
        novaConsequencia.setModeloAutorizacao(modeloDeclaracaoService.getOrCreateByNome(consequencia.getModeloAutorizacao()));
        novaConsequencia.setAutorizacao(autorizacaoProibicaoService.getOrCreateByDescricao(consequencia.getAutorizacao()));
        novaConsequencia.setProibicao(autorizacaoProibicaoService.getOrCreateByDescricao(consequencia.getProibicao()));
        novaConsequencia.setModeloDocumento(modeloDocumentoService.getOrCreateByDescricao(consequencia.getModeloDocumento()));
        novaConsequencia.setModeloGeometria(modeloGeometriaService.getOrCreateByDescricao(consequencia.getModeloGeometria()));
        return novaConsequencia;
    }
}
