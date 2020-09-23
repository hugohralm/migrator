package br.gov.go.mago.migrator.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import br.gov.go.mago.migrator.repository.QuestionarioTemplateRepository;

@Service
public class QuestionarioTemplateService {

    private final QuestionarioTemplateRepository repository;

    @Autowired
    public QuestionarioTemplateService(QuestionarioTemplateRepository repository) {
        this.repository = repository;
    }

    public QuestionarioTemplate getById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Não foi possível encontrar o questionário template solicitado."));
    }

    @Transactional(rollbackFor = Throwable.class)
    public QuestionarioTemplate migrarQuestionario(QuestionarioTemplate questionarioTemplate) {
        return repository.save(new QuestionarioTemplate(questionarioTemplate));
    }

    @Transactional(rollbackFor = Throwable.class)
    public QuestionarioTemplate getByDescricaoTipoQuestionarioTipoLicenciamentoRegimeLicenciamento(
            QuestionarioTemplate questionarioTemplate) {
        if (questionarioTemplate != null) {
            Optional<QuestionarioTemplate> questionario = repository
                    .findFirstByDescricaoAndTipoQuestionarioAndTipoLicenciamentoAndRegimeLicenciamentoOrderById(
                            questionarioTemplate.getDescricao(), questionarioTemplate.getTipoQuestionario(),
                            questionarioTemplate.getTipoLicenciamento(), questionarioTemplate.getRegimeLicenciamento());
            return questionario
                    .orElseThrow(() -> new IllegalStateException("Questionário não cadastrado. Cadastrar questionário "
                            + questionarioTemplate.getDescricao() + "."));
        } else {
            return null;
        }
    }
}
