package br.gov.go.mago.migrator.service;

import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import br.gov.go.mago.migrator.repository.QuestionarioTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionarioTemplateService {

    private final QuestionarioTemplateRepository repository;

    @Autowired
    public QuestionarioTemplateService(QuestionarioTemplateRepository repository) {
        this.repository = repository;
    }

    public QuestionarioTemplate getById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Não foi possível encontrar o questionario template solicitado."));
    }

    @Transactional(rollbackFor = Throwable.class)
    public QuestionarioTemplate migrarQuestionario(QuestionarioTemplate questionarioTemplate) {
        return repository.save(new QuestionarioTemplate(questionarioTemplate));
    }
}
