package br.gov.go.mago.migrator.repository;

import br.gov.go.mago.migrator.model.AutorizacaoProibicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorizacaoProibicaoRepository extends JpaRepository<AutorizacaoProibicao, Integer> {
    Optional<AutorizacaoProibicao> findFirstByTipoAndDescricaoAndDataExclusaoIsNullOrderById(String tipo, String descricao);
}
