package br.gov.go.mago.migrator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.go.mago.migrator.model.GrupoModeloCondicionante;

@Repository
public interface GrupoModeloCondicionanteRepository extends JpaRepository<GrupoModeloCondicionante, Integer> {
    Optional<GrupoModeloCondicionante> findFirstByNomeOrderById(String descricao);
}
