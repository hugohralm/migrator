package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.PerguntaTemplate;
import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import br.gov.go.mago.migrator.model.RespostaTemplate;
import br.gov.go.mago.migrator.repository.RespostaTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RespostaTemplateService {

    private final RespostaTemplateRepository repository;

    private final PerguntaTemplateService perguntaTemplateService;

    @Autowired
    public RespostaTemplateService(RespostaTemplateRepository repository,
                                   PerguntaTemplateService perguntaTemplateService) {
        this.repository = repository;
        this.perguntaTemplateService = perguntaTemplateService;
    }

    public RespostaTemplate getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Não foi possivel encontrar a resposta template ID.%d.", id)));
    }

    public List<RespostaTemplate> getAllByQuestionario(QuestionarioTemplate questionario) {
        return repository.findAllByPerguntaTemplateQuestionarioTemplateOrderByPerguntaTemplate(questionario);
    }

    public boolean existsByPerguntaTemplateQuestionarioTemplate(QuestionarioTemplate questionario) {
        return repository.existsByPerguntaTemplateQuestionarioTemplate(questionario);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void migrarRespostasTemplate(QuestionarioTemplate questionario, QuestionarioTemplate novoQuestionario) {
        Optional<PerguntaTemplate> proximaPergunta;
        List<RespostaTemplate> respostas = getAllByQuestionario(questionario);
        List<PerguntaTemplate> novasPerguntas = perguntaTemplateService.getAllByQuestionario(novoQuestionario);
        for (RespostaTemplate resposta : respostas) {
            Optional<PerguntaTemplate> perguntaTemplate = novasPerguntas.stream().filter(pergunta -> pergunta.equalsNovaPergunta(resposta.getPerguntaTemplate())).findFirst();
            proximaPergunta = Optional.empty();
            if (perguntaTemplate.isPresent()) {
                PerguntaTemplate proximaPerguntaResposta = resposta.getProximaPergunta();
                if (proximaPerguntaResposta != null) {
                    proximaPergunta = novasPerguntas.stream().filter(pergunta -> pergunta.equalsNovaPergunta(resposta.getProximaPergunta())).findFirst();
                }
                repository.saveAndFlush(new RespostaTemplate(resposta, perguntaTemplate.get(), proximaPergunta.orElse(null)));
            }
        }
    }
}
