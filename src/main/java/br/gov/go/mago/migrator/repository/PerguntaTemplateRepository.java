package br.gov.go.mago.migrator.repository;

import br.gov.go.mago.migrator.model.PerguntaTemplate;
import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerguntaTemplateRepository extends JpaRepository<PerguntaTemplate, Integer> {
    Optional<PerguntaTemplate> findById(Integer id);

    PerguntaTemplate findByCodigoAndDescricaoAndQuestionarioTemplate(String codigo, String descricao, QuestionarioTemplate questionarioTemplate);

    List<PerguntaTemplate> findAllByQuestionarioTemplateOrderByIndice(QuestionarioTemplate questionarioTemplate);

    boolean existsByQuestionarioTemplate(QuestionarioTemplate questionarioTemplate);
}
