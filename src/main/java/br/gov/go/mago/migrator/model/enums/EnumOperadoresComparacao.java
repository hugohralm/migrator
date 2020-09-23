package br.gov.go.mago.migrator.model.enums;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumOperadoresComparacao {

    MAIOR(">") {
        @Override
        public boolean execute(BigDecimal num1, BigDecimal num2) {
            return num1.compareTo(num2) > 0;
        }

    },
    MENOR("<") {
        @Override
        public boolean execute(BigDecimal num1, BigDecimal num2) {
            return num1.compareTo(num2) < 0;
        }

    },
    MAIORIGUAL(">=") {
        @Override
        public boolean execute(BigDecimal num1, BigDecimal num2) {
            return num1.compareTo(num2) >= 0;
        }

    },
    MENORIGUAL("<=") {
        @Override
        public boolean execute(BigDecimal num1, BigDecimal num2) {
            return num1.compareTo(num2) <= 0;
        }

    };

    public abstract boolean execute(BigDecimal num1, BigDecimal num2);

    private String descricao;
}