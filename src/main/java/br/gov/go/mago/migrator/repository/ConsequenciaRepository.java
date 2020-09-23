package br.gov.go.mago.migrator.repository;

import br.gov.go.mago.migrator.model.Consequencia;
import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsequenciaRepository extends JpaRepository<Consequencia, Integer> {
    Optional<Consequencia> findById(Integer id);

    List<Consequencia> findAllByRespostaTemplatePerguntaTemplateQuestionarioTemplateOrderById(QuestionarioTemplate questionario);

    boolean existsByRespostaTemplatePerguntaTemplateQuestionarioTemplate(QuestionarioTemplate perguntaTemplate_questionarioTemplate);
}
