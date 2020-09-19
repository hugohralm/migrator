package br.gov.go.mago.migrator.repository;

import br.gov.go.mago.migrator.model.ParametroTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParametroTemplateRepository extends JpaRepository<ParametroTemplate, Integer> {
    Optional<ParametroTemplate> findFirstByTipoAndDescricaoAndDataExclusaoIsNullOrderById(String tipo, String descricao);
}
