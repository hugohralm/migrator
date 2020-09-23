package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.Consequencia;
import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import br.gov.go.mago.migrator.model.RespostaTemplate;
import br.gov.go.mago.migrator.model.contract.ValidaQuestionarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MigracaoQuestionarioService {

    private final QuestionarioTemplateService questionarioTemplateService;

    private final PerguntaTemplateService perguntaTemplateService;

    private final RespostaTemplateService respostaTemplateService;

    private final ConsequenciaService consequenciaService;

    @Autowired
    public MigracaoQuestionarioService(QuestionarioTemplateService questionarioTemplateService,
                           PerguntaTemplateService perguntaTemplateService,
                           RespostaTemplateService respostaTemplateService,
                           ConsequenciaService consequenciaService) {
        this.questionarioTemplateService = questionarioTemplateService;
        this.perguntaTemplateService = perguntaTemplateService;
        this.respostaTemplateService = respostaTemplateService;
        this.consequenciaService = consequenciaService;
    }

    public QuestionarioTemplate migrarQuestionario(List<RespostaTemplate> respostas) {
        return createQuestionario(respostas);
    }

    private QuestionarioTemplate createQuestionario(List<RespostaTemplate> respostas) {
        QuestionarioTemplate novoQuestionario = null;
        Optional<QuestionarioTemplate> questionarioOpt = respostas.stream().map(rt -> rt.getPerguntaTemplate().getQuestionarioTemplate()).findFirst();
        if (questionarioOpt.isPresent()) {
            novoQuestionario = questionarioTemplateService.migrarQuestionario(questionarioOpt.get());
            perguntaTemplateService.migrarNovasPerguntasTemplate(respostas, novoQuestionario);
            respostaTemplateService.migrarNovasRespostasTemplate(respostas, novoQuestionario);
        }
        return novoQuestionario;
    }

    public ValidaQuestionarioResponse validaQuestionario(Integer questionarioId) {
        QuestionarioTemplate questionario = questionarioTemplateService.getById(questionarioId);
        ValidaQuestionarioResponse validaResponse = new ValidaQuestionarioResponse();
        validaResponse.setQuestionarioTemplate(questionario);
        validaResponse.setPossuiPergunta(perguntaTemplateService.existsByQuestionarioTemplate(questionario));
        validaResponse.setPossuiResposta(respostaTemplateService.existsByPerguntaTemplateQuestionarioTemplate(questionario));
        validaResponse.setPossuiConsequencia(consequenciaService.existsByRespostaTemplatePerguntaTemplateQuestionarioTemplate(questionario));
        return validaResponse;
    }

    public QuestionarioTemplate migrarConsequencias(QuestionarioTemplate questionario, List<Consequencia> consequencias) {
        consequenciaService.migrarConsequenciaResposta(consequencias, questionario);
        return questionario;
    }
}
