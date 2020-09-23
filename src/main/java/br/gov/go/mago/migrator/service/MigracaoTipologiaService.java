package br.gov.go.mago.migrator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.gov.go.mago.migrator.model.Tipologia;
import br.gov.go.mago.migrator.model.contract.ValidaTipologiaResponse;

@Service
public class MigracaoTipologiaService {

    private final TipologiaService tipologiaService;

    @Autowired
    public MigracaoTipologiaService(TipologiaService tipologiaService) {
        this.tipologiaService = tipologiaService;
    }

    public ValidaTipologiaResponse validaTipologia(Integer questionarioId) {
        Tipologia tipologia = this.tipologiaService.getById(questionarioId);
        ValidaTipologiaResponse validaResponse = new ValidaTipologiaResponse();
        validaResponse.setTipologia(tipologia);
        validaResponse.setPossuiAtividade(tipologia.getAtividade() != null);
        validaResponse
                .setPossuiGruposModeloCondicionante(!CollectionUtils.isEmpty(tipologia.getGruposModeloCondicionante()));
        validaResponse.setPossuiGruposModeloGeometria(!CollectionUtils.isEmpty(tipologia.getGruposModeloGeometria()));
        validaResponse.setPossuimodeloRequerimento(tipologia.getModeloRequerimento() != null);
        validaResponse.setPossuiModelosDeclaracao(!CollectionUtils.isEmpty(tipologia.getModelosDeclaracao()));
        validaResponse.setPossuiModelosLaudos(!CollectionUtils.isEmpty(tipologia.getModelosLaudos()));
        validaResponse.setPossuiParametrosIniciais(!CollectionUtils.isEmpty(tipologia.getParametrosIniciais()));
        validaResponse.setPossuiQuestionarioRequerimento(tipologia.getQuestionarioRequerimento() != null);
        validaResponse.setPossuiQuestionariosTemplate(!CollectionUtils.isEmpty(tipologia.getQuestionariosTemplate()));
        validaResponse.setPossuiTipoLicencaAtividade(tipologia.getTipoLicenca() != null);

        return validaResponse;
    }

    public Tipologia migrarTipologia(Tipologia tipologia) {
        return tipologiaService.create(tipologia);
    }

    public Tipologia migrarItensTipologia(Tipologia tipologiaSalva, Tipologia tipologia) {
        return tipologiaService.migrarItensTipologia(tipologiaSalva, tipologia);
    }
}
