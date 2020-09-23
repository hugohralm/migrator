package br.gov.go.mago.migrator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
