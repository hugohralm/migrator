package br.gov.go.mago.migrator.model.contract;

import br.gov.go.mago.migrator.model.Tipologia;
import lombok.Data;

@Data
public class ValidaTipologiaResponse {
    private Tipologia tipologia;
    private boolean possuiAtividade = false;
    private boolean possuiTipoLicencaAtividade = false;
    private boolean possuiQuestionarioRequerimento = false;
    private boolean possuimodeloRequerimento = false;
    private boolean possuiQuestionariosTemplate = false;
    private boolean possuiGruposModeloCondicionante = false;
    private boolean possuiGruposModeloGeometria = false;
    private boolean possuiModelosDeclaracao = false;
    private boolean possuiModelosLaudos = false;
    private boolean possuiParametrosIniciais = false;
}
