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
@Table(name = "modelocondicionante")
public class ModeloCondicionante implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "modelo_condicionante_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "modelo_condicionante_seq", sequenceName = "modelo_condicionante_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(name = "prazovalidadecondicionante")
    private int prazoValidadeCondicionante;

    private String periodicidade;

    @Column(name = "datacadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Column(name = "dataexclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataExclusao;

    public ModeloCondicionante(ModeloCondicionante modeloCondicionante) {
        this.id = null;
        this.descricao = modeloCondicionante.getDescricao();
        this.prazoValidadeCondicionante = modeloCondicionante.getPrazoValidadeCondicionante();
        this.periodicidade = modeloCondicionante.getPeriodicidade();
        this.dataCadastro = new Date();
        this.dataExclusao = null;
    }
}
