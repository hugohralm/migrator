package br.gov.go.mago.migrator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.go.mago.migrator.model.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {
    Optional<Atividade> findFirstByCodigoAndDescricaoOrderById(String codigo, String descricao);
}
