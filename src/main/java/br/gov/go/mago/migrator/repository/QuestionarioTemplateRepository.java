package br.gov.go.mago.migrator.repository;

import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionarioTemplateRepository extends JpaRepository<QuestionarioTemplate, Integer> {
    Optional<QuestionarioTemplate> findById(Integer id);

    boolean existsByDescricao(String descricao);
}
