package br.gov.go.mago.migrator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.go.mago.migrator.model.GrupoModeloGeometria;

@Repository
public interface GrupoModeloGeometriaRepository extends JpaRepository<GrupoModeloGeometria, Integer> {
    Optional<GrupoModeloGeometria> findFirstByNomeOrderById(String descricao);
}
