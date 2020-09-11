package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.PerguntaTemplate;
import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import br.gov.go.mago.migrator.repository.PerguntaTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PerguntaTemplateService {

    private final PerguntaTemplateRepository repository;

    @Autowired
    public PerguntaTemplateService(PerguntaTemplateRepository repository) {
        this.repository = repository;
    }

    public PerguntaTemplate getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Não foi possivel encontrar a pergunta template ID.%d.", id)));
    }

    public PerguntaTemplate getByCodigoAndDescricaoAndQuestionarioTemplate(String codigo, String descricao, QuestionarioTemplate questionarioTemplate) {
        return repository.findByCodigoAndDescricaoAndQuestionarioTemplate(codigo, descricao, questionarioTemplate);
    }

    public List<PerguntaTemplate> getAllByQuestionario(QuestionarioTemplate questionarioTemplate) {
        return repository.findAllByQuestionarioTemplateOrderByIndice(questionarioTemplate);
    }

    public boolean existsByQuestionarioTemplate(QuestionarioTemplate questionarioTemplate) {
        return repository.existsByQuestionarioTemplate(questionarioTemplate);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void migrarPerguntasTemplate(QuestionarioTemplate questionario, QuestionarioTemplate novoQuestionario) {
        List<PerguntaTemplate> perguntasTemplate = getAllByQuestionario(questionario);
        for (PerguntaTemplate pergunta : perguntasTemplate) {
            repository.saveAndFlush(new PerguntaTemplate(pergunta, novoQuestionario));
        }
    }
}
