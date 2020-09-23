package br.gov.go.mago.migrator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.go.mago.migrator.model.TipoLicenca;

@Repository
public interface TipoLicencaRepository extends JpaRepository<TipoLicenca, Integer> {
    Optional<TipoLicenca> findFirstByNomeAndDescricaoOrderById(String nome, String descricao);
}
