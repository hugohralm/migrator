package br.gov.go.mago.migrator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "autorizacaoproibicao")
public class AutorizacaoProibicao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "autorizacao_proibicao_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "autorizacao_proibicao_seq", sequenceName = "autorizacao_proibicao_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    private String descricao;

    private String tipo;

    @Column(name = "datacadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Column(name = "dataexclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataExclusao;
}
