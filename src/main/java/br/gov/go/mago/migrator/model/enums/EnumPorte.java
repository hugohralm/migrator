package br.gov.go.mago.migrator.model.enums;

public enum EnumPorte {
    D("Dispensa"), MI("Micro"), P("Pequeno"), M("Médio"), G("Grande");

    private String descricao;

    private EnumPorte(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
