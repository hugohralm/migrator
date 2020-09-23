package br.gov.go.mago.migrator.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumClasse {

    CI("Inexigibilidade", 0), CR("Registro", 1), C1("Classe 1", 2), C2("Classe 2", 3), C3("Classe 3", 4),
    C4("Classe 4", 5), C5("Classe 5", 6), C6("Classe 6", 7);

    private final String descricao;
    private final int peso;
}
