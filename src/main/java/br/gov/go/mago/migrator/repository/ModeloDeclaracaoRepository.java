package br.gov.go.mago.migrator.repository;

import br.gov.go.mago.migrator.model.ModeloDeclaracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeloDeclaracaoRepository extends JpaRepository<ModeloDeclaracao, Integer> {
    Optional<ModeloDeclaracao> findFirstByNomeAndDataExclusaoIsNullOrderById(String descricao);
}
