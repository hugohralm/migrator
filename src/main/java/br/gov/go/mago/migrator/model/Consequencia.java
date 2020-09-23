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
@Table(name = "consequencia")
public class Consequencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "consequencia_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "consequencia_seq", sequenceName = "consequencia_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "respostatemplate_id")
    private RespostaTemplate respostaTemplate;

    @Column(name = "validacaogeometricatipologia_id")
    private Integer validacaoGeometricaTipologia;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datacadastro")
    private Date dataCadastro;

    @Column(name = "tipoconsequencia")
    private String tipoConsequencia;

    @ManyToOne()
    @JoinColumn(name = "parametrotemplate_id")
    private ParametroTemplate parametroTemplate;

    @ManyToOne()
    @JoinColumn(name = "modelodocumento_id")
    private ModeloDocumento modeloDocumento;

    @ManyToOne()
    @JoinColumn(name = "modelogeometria_id")
    private ModeloGeometria modeloGeometria;

    @ManyToOne()
    @JoinColumn(name = "modelocondicionante_id")
    private ModeloCondicionante modeloCondicionante;

    @ManyToOne()
    @JoinColumn(name = "modelotermo_id")
    private ModeloTermo modeloTermo;

    @Column(name = "responsaveltecnico")
    private String responsavelTecnico;

    @ManyToOne()
    @JoinColumn(name = "modelodeclaracao_id")
    private ModeloDeclaracao modeloDeclaracao;

    @ManyToOne()
    @JoinColumn(name = "autorizacao_id")
    private AutorizacaoProibicao autorizacao;

    @ManyToOne()
    @JoinColumn(name = "proibicao_id")
    private AutorizacaoProibicao proibicao;

    @ManyToOne()
    @JoinColumn(name = "modelolaudo_id")
    private ModeloDeclaracao modeloLaudo;

    @ManyToOne()
    @JoinColumn(name = "modeloautorizacao_id")
    private ModeloDeclaracao modeloAutorizacao;

    public Consequencia(String tipoConsequencia, String responsavelTecnico) {
        this.tipoConsequencia = tipoConsequencia;
        this.responsavelTecnico = responsavelTecnico;
        this.dataCadastro = new Date();
    }
}
