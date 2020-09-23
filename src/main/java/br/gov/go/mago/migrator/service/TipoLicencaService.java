package br.gov.go.mago.migrator.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.mago.migrator.model.TipoLicenca;
import br.gov.go.mago.migrator.repository.TipoLicencaRepository;

@Service
public class TipoLicencaService {

    private final TipoLicencaRepository repository;

    @Autowired
    public TipoLicencaService(TipoLicencaRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public TipoLicenca getByNomeDescricao(TipoLicenca tipoLicenca) {
        if (tipoLicenca != null) {
            Optional<TipoLicenca> tipoLic = repository.findFirstByNomeAndDescricaoOrderById(tipoLicenca.getNome(),
                    tipoLicenca.getDescricao());
            return tipoLic.orElseThrow(() -> new IllegalStateException(
                    "TipoLicença não cadastrada. Cadastrar tipoLicença " + tipoLicenca.getDescricao() + "."));
        } else {
            return null;
        }
    }
}
