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
@Table(name = "modelodeclaracao")
public class ModeloDeclaracao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "modelo_declaracao_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "modelo_declaracao_seq", sequenceName = "modelo_declaracao_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    private String nome;

    @Column(name = "nometemplate")
    private String nomeTemplate;

    @ManyToOne()
    @JoinColumn(name = "termo_id")
    private ModeloTermo termo;

    private String sigla;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arquivotermoreferencia_id")
    private Arquivo arquivoTermoReferencia;

    @Column(name = "exigert")
    private Boolean exigeRT;

    @Column(name = "tiporesponsaveltecnico")
    private String tipoResponsavelTecnico;

    @Column(name = "exigeanalista")
    private Boolean exigeAnalista;

    @Column(name = "exigeempreendedor")
    private Boolean exigeEmpreendedor;

    @Column(name = "exigearquivopdf")
    private Boolean exigeArquivoPdf;

    @Column(name = "exigetermo")
    private Boolean exigeTermo;

    @Column(name = "exigedescricaolaudo")
    private Boolean exigeDescricaoLaudo;

    @Column(name = "tipodeclaracao")
    private String tipoDeclaracao;

    @Column(name = "datacadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Column(name = "dataexclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataExclusao;

    public ModeloDeclaracao(ModeloDeclaracao modeloDeclaracao) {
        this.id = null;
        this.nome = modeloDeclaracao.getNome().trim();
        this.nomeTemplate = modeloDeclaracao.getNomeTemplate();
        this.termo = null;
        this.sigla = modeloDeclaracao.getSigla().trim();
        this.arquivoTermoReferencia = null;
        this.exigeRT = modeloDeclaracao.getExigeRT();
        this.tipoResponsavelTecnico = modeloDeclaracao.getTipoResponsavelTecnico();
        this.exigeAnalista = modeloDeclaracao.getExigeAnalista();
        this.exigeEmpreendedor = modeloDeclaracao.getExigeEmpreendedor();
        this.exigeArquivoPdf = modeloDeclaracao.getExigeArquivoPdf();
        this.exigeTermo = modeloDeclaracao.getExigeTermo();
        this.exigeDescricaoLaudo = modeloDeclaracao.getExigeDescricaoLaudo();
        this.tipoDeclaracao = modeloDeclaracao.getTipoDeclaracao();
        this.dataCadastro = new Date();
        this.dataExclusao = null;
    }
}
