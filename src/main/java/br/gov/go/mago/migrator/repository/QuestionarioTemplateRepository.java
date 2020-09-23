package br.gov.go.mago.migrator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.go.mago.migrator.model.QuestionarioTemplate;

@Repository
public interface QuestionarioTemplateRepository extends JpaRepository<QuestionarioTemplate, Integer> {
    Optional<QuestionarioTemplate> findById(Integer id);

    Optional<QuestionarioTemplate> findFirstByDescricaoAndTipoQuestionarioAndTipoLicenciamentoAndRegimeLicenciamentoOrderById(
            String descricao, String tipoquestionario, String tipolicenciamento, String regimeLicenciamento);

    boolean existsByDescricao(String descricao);
}
