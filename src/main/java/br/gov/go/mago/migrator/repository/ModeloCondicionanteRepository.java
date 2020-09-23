package br.gov.go.mago.migrator.repository;

import br.gov.go.mago.migrator.model.ModeloCondicionante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeloCondicionanteRepository extends JpaRepository<ModeloCondicionante, Integer> {
    Optional<ModeloCondicionante> findFirstByDescricaoAndDataExclusaoIsNullOrderById(String descricao);
}
