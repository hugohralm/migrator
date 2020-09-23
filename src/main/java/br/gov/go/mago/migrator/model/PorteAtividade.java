package br.gov.go.mago.migrator.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.gov.go.mago.migrator.model.enums.EnumOperadoresComparacao;
import br.gov.go.mago.migrator.model.enums.EnumPorte;
import br.gov.go.mago.migrator.util.UtilMigrator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "porteatividade")
public class PorteAtividade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue(generator = "porte_atividade_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "porte_atividade_seq", sequenceName = "porte_atividade_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Informe o porte.")
    private EnumPorte porte;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name="operadorinicio")
    private EnumOperadoresComparacao operadorInicio;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name="operadorfim")
    private EnumOperadoresComparacao operadorFim;

    @Getter
    @Setter
    @Column(precision = 19, scale = 2, name="valorinicio")
    private BigDecimal valorInicio;

    @Getter
    @Setter
    @Column(precision = 19, scale = 2, name="valorfim")
    private BigDecimal valorFim;

    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="datacadastro")
    private Date dataCadastro;

    @PrePersist
    private void atualizarData() {
        this.dataCadastro = new Date();
    }

    public boolean isEditavel() {
        return this.getId() != null;
    }

    public String getValorInicioFormatado() {
        return (this.valorInicio == null) ? ""
                : String.format("%s %s", operadorInicio.getDescricao(),
                        UtilMigrator.getBigDecimalFormatado(this.valorInicio));
    }

    public String getValorFimFormatado() {
        return (this.valorInicio == null) ? ""
                : String.format("%s %s", operadorFim.getDescricao(),
                        UtilMigrator.getBigDecimalFormatado(this.valorFim));
    }
}
