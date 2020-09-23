package br.gov.go.mago.migrator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.go.mago.migrator.model.GrupoModeloTermo;

@Repository
public interface GrupoModeloTermoRepository extends JpaRepository<GrupoModeloTermo, Integer> {
    Optional<GrupoModeloTermo> findFirstByDescricaoOrderById(String descricao);
}
