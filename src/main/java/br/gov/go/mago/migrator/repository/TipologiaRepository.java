package br.gov.go.mago.migrator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.go.mago.migrator.model.Tipologia;

@Repository
public interface TipologiaRepository extends JpaRepository<Tipologia, Integer> {
    Optional<Tipologia> findById(Integer id);
}
