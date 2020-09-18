package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import br.gov.go.mago.migrator.repository.QuestionarioTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class QuestionarioTemplateService {

    private final QuestionarioTemplateRepository repository;

    @Autowired
    public QuestionarioTemplateService(QuestionarioTemplateRepository repository) {
        this.repository = repository;
    }

    public QuestionarioTemplate getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar o questionário template solicitado."));
    }

    @Transactional(rollbackFor = Throwable.class)
    public QuestionarioTemplate migrarQuestionario(QuestionarioTemplate questionarioTemplate) {
        return repository.save(new QuestionarioTemplate(questionarioTemplate));
    }
}
