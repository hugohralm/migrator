package br.gov.go.mago.migrator.model.enums;

public enum EnumPotencialPoluidor {
    P("Pequeno"), M("Médio"), A("Alto");

    private String descricao;

    private EnumPotencialPoluidor(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
