package br.gov.go.mago.migrator.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumTipoLicenciamento {

    LICENCIAMENTO_NOVO("Licenciamento Novo"), LICENCIAMENTO_CORRETIVO("Licenciamento Corretivo"),
    LICENCIAMENTO_ALTERACAO_AMPLIACAO("Licenciamento Alteração/Ampliação");

    private final String descricao;

}
