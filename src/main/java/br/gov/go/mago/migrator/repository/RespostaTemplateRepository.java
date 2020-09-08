package br.gov.go.mago.migrator.repository;

import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import br.gov.go.mago.migrator.model.RespostaTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RespostaTemplateRepository extends JpaRepository<RespostaTemplate, Integer> {
    Optional<RespostaTemplate> findById(Integer id);

    List<RespostaTemplate> findAllByPerguntaTemplateQuestionarioTemplateOrderByPerguntaTemplate(QuestionarioTemplate questionarioTemplate);

    boolean existsByPerguntaTemplateQuestionarioTemplate(QuestionarioTemplate perguntaTemplate_questionarioTemplate);
}
