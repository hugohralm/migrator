package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import br.gov.go.mago.migrator.model.contract.ValidaQuestionarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MigracaoService {

    private final QuestionarioTemplateService questionarioTemplateService;

    private final PerguntaTemplateService perguntaTemplateService;

    private final RespostaTemplateService respostaTemplateService;

    @Autowired
    public MigracaoService(QuestionarioTemplateService questionarioTemplateService,
                           PerguntaTemplateService perguntaTemplateService,
                           RespostaTemplateService respostaTemplateService) {
        this.questionarioTemplateService = questionarioTemplateService;
        this.perguntaTemplateService = perguntaTemplateService;
        this.respostaTemplateService = respostaTemplateService;
    }

    public QuestionarioTemplate migrarQuestionario(Integer questionarioId) {
        QuestionarioTemplate questionario = questionarioTemplateService.getById(questionarioId);
        questionarioTemplateService.existsByDescricao(questionario.getDescricao());
        QuestionarioTemplate novoQuestionario = questionarioTemplateService.migrarQuestionario(questionario);
        perguntaTemplateService.migrarPerguntasTemplate(questionario, novoQuestionario);
        respostaTemplateService.migrarRespostasTemplate(questionario, novoQuestionario);
        return novoQuestionario;
    }

    public ValidaQuestionarioResponse validaQuestionario(Integer questionarioId) {
        QuestionarioTemplate questionario = questionarioTemplateService.getById(questionarioId);
        ValidaQuestionarioResponse validaResponse = new ValidaQuestionarioResponse();
        validaResponse.setQuestionarioTemplate(questionario);
        validaResponse.setPossuiPergunta(perguntaTemplateService.existsByQuestionarioTemplate(questionario));
        validaResponse.setPossuiResposta(respostaTemplateService.existsByPerguntaTemplateQuestionarioTemplate(questionario));
        return validaResponse;
    }
}
