package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.PerguntaTemplate;
import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import br.gov.go.mago.migrator.model.RespostaTemplate;
import br.gov.go.mago.migrator.repository.PerguntaTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerguntaTemplateService {

    private final PerguntaTemplateRepository repository;

    @Autowired
    public PerguntaTemplateService(PerguntaTemplateRepository repository) {
        this.repository = repository;
    }

    public List<PerguntaTemplate> getAllByQuestionario(QuestionarioTemplate questionarioTemplate) {
        return repository.findAllByQuestionarioTemplateOrderByIndice(questionarioTemplate);
    }

    public boolean existsByQuestionarioTemplate(QuestionarioTemplate questionarioTemplate) {
        return repository.existsByQuestionarioTemplate(questionarioTemplate);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void migrarNovasPerguntasTemplate(List<RespostaTemplate> respostas, QuestionarioTemplate novoQuestionario) {
        List<PerguntaTemplate> perguntas = respostas
                .stream()
                .map(RespostaTemplate::getPerguntaTemplate)
                .distinct()
                .sorted(Comparator.comparingInt(PerguntaTemplate::getIndice))
                .collect(Collectors.toList());
        for (PerguntaTemplate pergunta : perguntas) {
            repository.saveAndFlush(new PerguntaTemplate(pergunta, novoQuestionario));
        }
    }
}
