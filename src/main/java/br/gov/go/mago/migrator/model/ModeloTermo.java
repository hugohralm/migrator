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
@Table(name = "modelotermo")
public class ModeloTermo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "modelo_termo_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "modelo_termo_seq", sequenceName = "modelo_termo_seq", allocationSize = 1)
    private Integer id;

    private String descricao;

    @Column(name = "datacadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Column(name = "dataexclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataExclusao;

    public ModeloTermo(ModeloTermo modeloTermo) {
        this.id = null;
        this.descricao = modeloTermo.getDescricao().trim();
        this.dataCadastro = new Date();
        this.dataExclusao = null;
    }
}
