package br.gov.go.mago.migrator.repository;

import br.gov.go.mago.migrator.model.ModeloTermo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeloTermoRepository extends JpaRepository<ModeloTermo, Integer> {
    Optional<ModeloTermo> findFirstByDescricaoAndDataExclusaoIsNullOrderById(String descricao);
}
