package br.gov.go.mago.migrator.repository;

import br.gov.go.mago.migrator.model.ModeloGeometria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeloGeometriaRepository extends JpaRepository<ModeloGeometria, Integer> {
    Optional<ModeloGeometria> findFirstByNomeAndTipoOrderById(String nome, String tipo);
}
