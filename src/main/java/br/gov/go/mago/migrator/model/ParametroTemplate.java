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
@Table(name = "parametrotemplate")
public class ParametroTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "parametro_template_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "parametro_template_seq", sequenceName = "parametro_template_seq", allocationSize = 1)
    private Integer id;

    private String tipo;

    private String descricao;

    private String detalhes;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datacadastro")
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataexclusao")
    private Date dataExclusao;

    public ParametroTemplate(ParametroTemplate parametroTemplate) {
        this.id = null;
        this.tipo = parametroTemplate.getTipo();
        this.descricao = parametroTemplate.getDescricao();
        this.detalhes = parametroTemplate.getDetalhes();
        this.dataCadastro = new Date();
        this.dataExclusao = null;
    }
}
