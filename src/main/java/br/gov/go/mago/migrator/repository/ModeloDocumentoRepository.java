package br.gov.go.mago.migrator.repository;

import br.gov.go.mago.migrator.model.ModeloDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeloDocumentoRepository extends JpaRepository<ModeloDocumento, Integer> {
    Optional<ModeloDocumento> findFirstByDescricaoOrderById(String descricao);
}
